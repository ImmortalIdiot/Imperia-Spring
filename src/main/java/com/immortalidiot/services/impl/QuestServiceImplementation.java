package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.GradesAndRanks;
import com.immortalidiot.entities.enums.QuestStatus;
import com.immortalidiot.entities.enums.QuestType;
import com.immortalidiot.repositories.DealRepository;
import com.immortalidiot.repositories.QuestRepository;
import com.immortalidiot.services.QuestService;
import com.immortalidiot.services.dtos.CultistDTO;
import com.immortalidiot.services.dtos.QuestDTO;
import com.immortalidiot.util.exceptions.QuestNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestServiceImplementation implements QuestService {

    private final DealRepository dealRepository;
    private final QuestRepository questRepository;
    private final ModelMapper modelMapper;

    public QuestServiceImplementation(DealRepository dealRepository,
                                      QuestRepository questRepository,
                                      ModelMapper modelMapper) {
        this.dealRepository = dealRepository;
        this.questRepository = questRepository;
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(CultistDTO.class, Cultist.class)
                .addMappings(mapper -> {
                    mapper.skip(Cultist::setFirstName);
                    mapper.skip(Cultist::setLastName);
                    mapper.skip(Cultist::setThanksGiving);
                    mapper.skip(Cultist::setQuests);
                    mapper.skip(Cultist::setCity);
                }
        );
    }

    @Override
    public void createQuest() {
        Deal latestDeal = dealRepository.findLatestDeal();

        String clientTerms = latestDeal.getClientTerms();
        String[] termParts = clientTerms.split("; ");
        String type = termParts[0];

        QuestType questType = QuestType.getQuestTypeByName(type);

        LocalDate registrationDate = LocalDate.parse(termParts[1]);
        LocalDate targetDate = LocalDate.parse(termParts[2]);

        long urgency = Duration.between(registrationDate, targetDate).toDays();

        String minRankAndGrade = setMinRankAndGrade(questType);
        validateRankAndGrade(minRankAndGrade);

        String minGrade = minRankAndGrade.split(" ")[0];
        String minRank = minRankAndGrade.split(" ")[1];

        int numCultists = needCultists(urgency);

        String reward = setReward(questType);
        String punishment = setPunishment(questType);

        validateReward(reward);
        validatePunishment(punishment);

        Quest quest = new Quest(questType, minGrade, minRank, numCultists,
                localDateToOffsetDateTime(registrationDate), localDateToOffsetDateTime(targetDate),
                reward, punishment, latestDeal);

        questRepository.save(quest);
    }

    @Override
    public int needCultists(long urgency) {
        if (urgency > 14) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public String setMinRankAndGrade(QuestType questType) {
        String rankAndGrade = "";

        switch (questType) {
            case CITIZEN_HELP -> rankAndGrade = "Private II";
            case BODYGUARDS -> rankAndGrade = "Apprentice III";
            case INTELLIGENCE -> rankAndGrade = "Master III";
            case FORGERY -> rankAndGrade = "Master I";
            case MURDER -> rankAndGrade = "Magister II";
        }

        return rankAndGrade;
    }

    @Override
    public String setReward(QuestType questType) {
        String reward = "";

        switch (questType) {
            case CITIZEN_HELP -> reward = "Attempt to take the exam";
            case BODYGUARDS -> reward = "Access to a special workout";
            case INTELLIGENCE -> reward = "Access to low-rank training";
            case FORGERY -> reward = "Access to complete a task with a cultist of a higher rank";
            case MURDER -> reward = "Access to the arena of the immortals";
        }

        return reward;
    }

    @Override
    public String setPunishment(QuestType questType) {
        String punishment = "";

        switch (questType) {
            case CITIZEN_HELP -> punishment = "10 additional workouts of increased difficulty";
            case BODYGUARDS -> punishment = "Denial of access to training for cultists with a lower rank";
            case INTELLIGENCE -> punishment = "Denial of access to INTELLIGENCE and higher quests";
            case FORGERY -> punishment = "Demotion in rank";
            case MURDER -> punishment = "Demotion in grade";
        }

        return punishment;
    }

    private OffsetDateTime localDateToOffsetDateTime(LocalDate localDate) {
        ZoneOffset offset = ZoneOffset.ofHours(3);
        return localDate.atStartOfDay().atOffset(offset);
    }

    private void validateRankAndGrade(String rankAndGrade) {
        if (rankAndGrade == null || rankAndGrade.isBlank()) {
            throw new IllegalArgumentException("Unknown rank or grade");
        }
    }

    private void validateReward(String reward) {
        if (reward == null || reward.isBlank()) {
            throw new IllegalArgumentException("Unknown reward");
        }
    }

    private void validatePunishment(String punishment) {
        if (punishment == null || punishment.isBlank()) {
            throw new IllegalArgumentException("Unknown punishment");
        }
    }

    @Override
    public List<Quest> getAllQuestsForCultist(CultistDTO cultistDTO) {
        Cultist currentCultist = mapCultistDTOToEntity(cultistDTO);

        List<Cultist> cultist = new ArrayList<>();
        List<QuestStatus> statuses = Arrays.asList(QuestStatus.COMPLETED, QuestStatus.FAILED);
        cultist.add(currentCultist);
        return questRepository.findQuestsByCultists(cultist, statuses);
    }

    private List<String> getLowerOrEqualGradesAndRanks(CultistDTO cultistDTO) {
        List<String> lowerOrEqualGradeAndRanks = new ArrayList<>();
        String cultistGradeAndRank = cultistDTO.getGrade() + " " + cultistDTO.getRank();

        for (String gradeAndRank : GradesAndRanks.getAllGradesAndRanks()) {
            lowerOrEqualGradeAndRanks.add(gradeAndRank);

            if (gradeAndRank.equalsIgnoreCase(cultistGradeAndRank)) {
                break;
            }
        }

        return lowerOrEqualGradeAndRanks;
    }

    public List<Quest> getAvailableQuestsForCultist(CultistDTO cultistDTO) {
        List<String> lowerOrEqualGradeRanks = getLowerOrEqualGradesAndRanks(cultistDTO);

        List<Quest> availableQuests = new ArrayList<>();
        List<Quest> quests;

        for (String gradeAndRank : lowerOrEqualGradeRanks) {
            String[] parts = gradeAndRank.split(" ");
            if (parts.length < 2) {
                quests = questRepository.findByMinGradeAndMinRankAndQuestStatus(
                        gradeAndRank.split(" ")[0],
                        "",
                        QuestStatus.AVAILABLE);
            } else {
                quests = questRepository.findByMinGradeAndMinRankAndQuestStatus(
                        gradeAndRank.split(" ")[0],
                        gradeAndRank.split(" ")[1],
                        QuestStatus.AVAILABLE);
            }
            availableQuests.addAll(quests);
        }

        return availableQuests;
    }

    protected Map<QuestType, Integer> getQuestTypeCount(List<Quest> quests, QuestStatus status) {
        Map<QuestType, Integer> typeCountMap = new HashMap<>();

        for (Quest quest : quests) {
            if (quest.getQuestStatus() == status) {
                typeCountMap.put(quest.getQuestType(),
                        typeCountMap.getOrDefault(quest.getQuestType(), 0) + 1);
            }
        }

        return typeCountMap;
    }

    protected QuestType getPriorityQuestType(List<Quest> availableQuests) {
        Map<QuestType, Integer> failedQuests = getQuestTypeCount(availableQuests, QuestStatus.FAILED);
        Map<QuestType, Integer> completedQuests = getQuestTypeCount(availableQuests, QuestStatus.COMPLETED);

        int maxDifference = 0;
        QuestType priorityType = null;

        for (QuestType type : completedQuests.keySet()) {
            int completed = completedQuests.getOrDefault(type, 0);
            int failed = failedQuests.getOrDefault(type, 0);
            int difference = completed - failed;

            if (difference > maxDifference) {
                maxDifference = difference;
                priorityType = type;
            }
        }

        return priorityType;
    }

    protected List<Quest> sortQuestsByPriority(List<Quest> quests, QuestType priorityQuestType) {
        List<Quest> sortedQuests = new ArrayList<>(quests);

        sortedQuests.sort(Comparator.comparing((Quest q) -> q.getQuestType() == priorityQuestType ? 0 : 1)
                .thenComparing(Quest::getDateFormed));

        return sortedQuests;
    }

    protected List<Quest> getSortedQuestsForCultist(CultistDTO cultistDTO) {
        List<Quest> completedOrFailedQuests = getAllQuestsForCultist(cultistDTO);
        QuestType priorityQuestType = getPriorityQuestType(completedOrFailedQuests);
        return sortQuestsByPriority(getAvailableQuestsForCultist(cultistDTO), priorityQuestType);
    }

    protected Quest getRandomQuest(List<Quest> availableQuest) {
        Random randomizer = new Random();
        return availableQuest.get(randomizer.nextInt(availableQuest.size()));
    }

    private void addCultistToQuest(CultistDTO cultistDTO, Quest quest) {
        Cultist cultist = mapCultistDTOToEntity(cultistDTO);
        cultist.getQuests().add(quest);
        quest.getCultists().add(cultist);
    }

    @Override
    public QuestDTO selectQuest(CultistDTO cultistDTO) {
        List<Quest> availableQuests = getSortedQuestsForCultist(cultistDTO);

        if (availableQuests.isEmpty()) {
            throw new QuestNotFoundException("There are no available quests");
        }

        Quest selectedQuest = getRandomQuest(availableQuests);

        addCultistToQuest(cultistDTO, selectedQuest);
        int numParticipants = selectedQuest.getCultists().size() + 1;

        int numCultists = selectedQuest.getNumCultists();
        int chance;

        if (selectedQuest.getQuestType() == getPriorityQuestType(availableQuests)) {
            if (numCultists == 1) {
                chance = 90;
            } else {
                chance = 80;
            }
        } else {
            if (numCultists == 1) {
                chance = 70;
            } else {
                chance = 60;
            }
        }

        selectedQuest.setChance(chance);

        if (chance >= 80 && numParticipants == numCultists) {
            selectedQuest.setQuestStatus(QuestStatus.ONGOING);
        }
        return mapQuestEntityToDTO(selectedQuest);
    }

    private Cultist mapCultistDTOToEntity(CultistDTO cultistDTO) {
        return modelMapper.map(cultistDTO, Cultist.class);
    }

    private QuestDTO mapQuestEntityToDTO(Quest quest) {
        Set<String> cultistsNicks = quest.getCultists()
                .stream()
                .map(Cultist::getNickname)
                .collect(Collectors.toSet());

        return new QuestDTO(quest.getId(),
                quest.getQuestType(),
                quest.getQuestStatus(),
                quest.getMinGrade(),
                quest.getMinRank(),
                quest.getNumCultists(),
                quest.getDateFormed(),
                quest.getDateCompleted(),
                quest.getReward(),
                quest.getPunishment(),
                quest.getChance(),
                cultistsNicks,
                quest.getDeal().getId());
    }
}

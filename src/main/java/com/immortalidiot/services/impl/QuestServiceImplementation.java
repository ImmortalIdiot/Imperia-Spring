package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.QuestStatus;
import com.immortalidiot.entities.enums.QuestType;
import com.immortalidiot.repositories.QuestRepository;
import com.immortalidiot.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestServiceImplementation implements QuestService {

    private DealServiceImplementation dealServiceImplementation;
    private QuestRepository questRepository;

    private final Deal LATEST_DEAL = dealServiceImplementation.getLatestCreatedDeal();

    private List<String> allGradesAndRanks = Arrays.asList(
            "Recruit", "Private II", "Private I",
            "Apprentice III", "Apprentice II", "Apprentice I",
            "Master III", "Master II", "Master I",
            "Magister III", "Magister II", "Magister I"
    );

    @Autowired
    public QuestServiceImplementation(DealServiceImplementation dealServiceImplementation,
                                      QuestRepository questRepository) {
        this.dealServiceImplementation = dealServiceImplementation;
        this.questRepository = questRepository;
    }

    @Override
    public void createQuest() {
        String clientTerms = LATEST_DEAL.getClientTerms();
        String[] termParts = clientTerms.split("; ");
        String type = termParts[0];
        QuestType questType = dealServiceImplementation.defineQuestType(type);

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

        Quest quest = new Quest(questType, minGrade, minRank, numCultists,
                localDateToOffsetDateTime(registrationDate), localDateToOffsetDateTime(targetDate),
                reward, punishment, LATEST_DEAL);

        questRepository.save(quest);
    }

    @Override
    public int needCultists(long urgency) {
        if (urgency > 14) { return 1; } else { return 2; }
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
    public List<Quest> getAllQuestsForCultist(Cultist currentCultist) {
        List<Cultist> cultist = new ArrayList<>();
        List<QuestStatus> statuses = Arrays.asList(QuestStatus.COMPLETED, QuestStatus.FAILED);
        cultist.add(currentCultist);
        return questRepository.findQuestsByCultists(cultist, statuses);
    }

    private List<String> getLowerOrEqualGradesAndRanks(Cultist cultist) {
        List<String> lowerOrEqualGradeAndRanks = new ArrayList<>();
        String cultistGradeAndRank = cultist.getGrade() + " " + cultist.getRank();

        for (String gradeAndRank : allGradesAndRanks) {
            lowerOrEqualGradeAndRanks.add(gradeAndRank);

            if (gradeAndRank.equalsIgnoreCase(cultistGradeAndRank)) { break; }
        }

        return lowerOrEqualGradeAndRanks;
    }

    public List<Quest> getAvailableQuestsForCultist(Cultist cultist) {
        List<String> lowerOrEqualGradeRanks = getLowerOrEqualGradesAndRanks(cultist);

        List<Quest> availableQuests = new ArrayList<>();

        for (String gradeAndRank : lowerOrEqualGradeRanks) {
            List<Quest> quests = questRepository.findByMinGradeAndMinRankAndQuestStatus(
                    gradeAndRank.split(" ")[0],
                    gradeAndRank.split(" ")[1],
                    QuestStatus.AVAILABLE);
            availableQuests.addAll(quests);
        }

        return availableQuests;
    }
}

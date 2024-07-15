package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.GradesAndRanks;
import com.immortalidiot.entities.enums.QuestStatus;
import com.immortalidiot.repositories.CultistRepository;
import com.immortalidiot.services.CultistService;
import com.immortalidiot.services.dtos.CultistDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CultistServiceImpl implements CultistService {
    private final CultistRepository cultistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CultistServiceImpl(CultistRepository cultistRepository, ModelMapper modelMapper) {
        this.cultistRepository = cultistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public CultistDTO promoteCultist(CultistDTO cultistDTO) {
        Cultist cultist = mapCultistDTOToEntity(cultistDTO);
        List<Quest> quests = cultistRepository.findQuestsByCultistId(cultist.getNickname());

        if (shouldPromote(quests)) {
            promote(cultist);
            cultistRepository.save(cultist);
        }

        return mapCultistEntityToDTO(cultist);
    }

    private boolean shouldPromote(List<Quest> quests) {
        long questCompleted = quests.stream().filter(quest ->
                QuestStatus.COMPLETED.equals(quest.getQuestStatus())).count();
        long questFailed = quests.stream().filter(quest ->
                QuestStatus.FAILED.equals(quest.getQuestStatus())).count();

        double completedToFailed = (double) questCompleted / (questFailed == 0 ? 1 : questFailed);

        long completedInLastSixMonths = quests.stream()
                .filter(quest -> QuestStatus.COMPLETED.equals(quest.getQuestStatus()))
                .filter(quest -> quest.getDateCompleted().isAfter(OffsetDateTime.now().minusMonths(6)))
                .count();

        double averageCompletionTime = quests.stream()
                .filter(quest -> QuestStatus.COMPLETED.equals(quest.getQuestStatus()))
                .mapToLong(quest -> Duration.between(quest.getDateFormed(), quest.getDateCompleted()).toDays())
                .average()
                .orElse(Double.MAX_VALUE);

        return completedInLastSixMonths > 0.1 * (questCompleted + questFailed) &&
               completedToFailed > 2 &&
               averageCompletionTime < 7;
    }

    private void promote(Cultist cultist) {
        String currentGradeAndRank = "";

        if (cultist.getRank() == null || cultist.getRank().isBlank()) {
            currentGradeAndRank = cultist.getGrade();
        } else {
            currentGradeAndRank = cultist.getGrade() + " " + cultist.getRank();
        }

        List<String> gradesAndRanks = GradesAndRanks.getAllGradesAndRanks();
        int currentGradeAndRankIndex = gradesAndRanks.indexOf(currentGradeAndRank);

        if (currentGradeAndRankIndex != -1 && currentGradeAndRankIndex < gradesAndRanks.size() - 1) {
            String nextGradeAndRank = gradesAndRanks.get(currentGradeAndRankIndex + 1);

            String nextGrade = nextGradeAndRank.split(" ")[0];
            String nextRank = nextGradeAndRank.split(" ")[1];

            cultist.setGrade(nextGrade);
            cultist.setRank(nextRank);
        }
    }

    private Cultist mapCultistDTOToEntity(CultistDTO cultistDTO) {
        return modelMapper.map(cultistDTO, Cultist.class);
    }

    private CultistDTO mapCultistEntityToDTO(Cultist cultist) {
        return modelMapper.map(cultist, CultistDTO.class);
    }
}

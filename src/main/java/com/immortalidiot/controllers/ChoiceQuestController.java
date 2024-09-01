package com.immortalidiot.controllers;

import com.immortalidiot.services.QuestService;
import com.immortalidiot.services.dtos.CultistDTO;
import com.immortalidiot.services.dtos.QuestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quests/available")
public class ChoiceQuestController {

    private final QuestService questService;

    @Autowired
    public ChoiceQuestController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping("/choice")
    public ResponseEntity<QuestDTO> choiceQuest(@RequestParam String nickname,
                                                @RequestParam String grade,
                                                @RequestParam String rank) {
        CultistDTO cultistDTO = new CultistDTO(nickname, grade, rank);
        System.out.println(questService.selectQuest(cultistDTO));
        return ResponseEntity.ok(questService.selectQuest(cultistDTO));
    }
}

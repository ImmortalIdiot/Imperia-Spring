package com.immortalidiot.controllers;

import com.immortalidiot.services.QuestService;
import com.immortalidiot.services.dtos.CultistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quests/available")
public class ChoiceQuestController {
    private final QuestService questService;

    @Autowired
    public ChoiceQuestController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping("/choice")
    public void choiceQuest(@RequestParam CultistDTO cultistDTO) {
        questService.selectQuest(cultistDTO);
    }
}

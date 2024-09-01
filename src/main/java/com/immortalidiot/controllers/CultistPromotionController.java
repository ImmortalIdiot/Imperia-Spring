package com.immortalidiot.controllers;

import com.immortalidiot.services.CultistService;
import com.immortalidiot.services.dtos.CultistDTO;
import com.immortalidiot.services.dtos.CultistResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cultists")
public class CultistPromotionController {

    private final CultistService cultistService;

    @Autowired
    public CultistPromotionController(CultistService cultistService) {
        this.cultistService = cultistService;
    }

    @PostMapping("/promoted")
    public CultistResponseDTO promoteCultist(@RequestParam String nick,
                                             @RequestParam String grade,
                                             @RequestParam String rank) {
        CultistDTO cultistDTO = new CultistDTO(nick, grade, rank);
        return cultistService.promoteCultist(cultistDTO);
    }
}

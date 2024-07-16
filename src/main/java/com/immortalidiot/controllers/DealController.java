package com.immortalidiot.controllers;

import com.immortalidiot.services.DealService;
import com.immortalidiot.services.dtos.ClientDTO;
import com.immortalidiot.services.dtos.DealRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deals")
public class DealController {
    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/create")
    public void createNewDeal(@RequestParam DealRequestDTO dealRequestDTO) {
        System.out.println("Метод запустился");
        ClientDTO clientDTO = new ClientDTO(dealRequestDTO.getContact(), dealRequestDTO.getName());
        dealService.createDeal(clientDTO, dealRequestDTO.getTerms());
    }
}

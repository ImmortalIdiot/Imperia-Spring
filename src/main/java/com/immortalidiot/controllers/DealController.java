package com.immortalidiot.controllers;

import com.immortalidiot.services.DealService;
import com.immortalidiot.services.dtos.ClientDTO;
import com.immortalidiot.services.dtos.DealRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DealController {

    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/deals/create")
    public void createNewDeal(@RequestParam String contact, @RequestParam String name, @RequestParam String terms) {
        DealRequestDTO dealRequestDTO = new DealRequestDTO(contact, name, terms);
        ClientDTO clientDTO = new ClientDTO(dealRequestDTO.getContact(), dealRequestDTO.getName());
        dealService.createDeal(clientDTO, dealRequestDTO.getTerms());
    }
}

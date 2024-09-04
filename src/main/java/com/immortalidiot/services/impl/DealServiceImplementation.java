package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.Manager;
import com.immortalidiot.entities.enums.QuestType;
import com.immortalidiot.repositories.DealRepository;
import com.immortalidiot.repositories.ManagerRepository;
import com.immortalidiot.services.ClientService;
import com.immortalidiot.services.DealService;
import com.immortalidiot.services.QuestService;
import com.immortalidiot.services.dtos.ClientDTO;
import com.immortalidiot.services.dtos.DealDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class DealServiceImplementation implements DealService {

    private final DealRepository dealRepository;
    private final ClientService clientService;
    private final ManagerRepository managerRepository;
    private final QuestService questService;
    private final ModelMapper modelMapper;

    public DealServiceImplementation(DealRepository dealRepository,
                                     ClientService clientService,
                                     ManagerRepository managerRepository,
                                     QuestService questService,
                                     ModelMapper modelMapper) {
        this.dealRepository = dealRepository;
        this.clientService = clientService;
        this.managerRepository = managerRepository;
        this.questService = questService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public DealDTO createDeal(ClientDTO clientDTO, String clientTerms) {
        Manager manager = managerRepository.getRandomManager(managerRepository.getAllManagers());

        Client client = mapClientDTOToEntity(clientDTO);
        clientService.registerClient(clientDTO);

        double amount = calculateCost(clientTerms);

        Deal deal = new Deal(manager, client, clientTerms, amount);
        dealRepository.save(deal);
        questService.createQuest();

        return mapDealEntityToDTO(deal);
    }

    private double calculateCost(String clientTerms) {
        // clientTerms example:
        // "bodyguards (description: questType); 2024-01-01T12:00:00 (description: dealDate);
        //             2024-01-8T23:59:00 (description: targetDate); other details"
        String[] termsParts = clientTerms.split("; ");

        QuestType questType = QuestType.getQuestTypeByName(termsParts[0]);
        validateQuestType(questType);

        double cost = 0;

        switch (questType) {
            case CITIZEN_HELP -> cost += 25000;
            case BODYGUARDS -> cost += 50000;
            case INTELLIGENCE -> cost += 75000;
            case FORGERY -> cost += 150000;
            case MURDER -> cost += 400000;
        }

        LocalDateTime registrationDate = LocalDateTime.parse(termsParts[1]);
        LocalDateTime targetDate = LocalDateTime.parse(termsParts[2]);
        validateDate(registrationDate, targetDate);

        long urgency = Duration.between(registrationDate, targetDate).toDays();

        if (urgency <= 7) {
            cost *= 1.8;
        } else if (urgency <= 14) {
            cost *= 1.6;
        } else if (urgency <= 21) {
            cost *= 1.4;
        } else {
            cost *= 1.2;
        }

        return cost;
    }

    private void validateQuestType(QuestType questType) {
        if (questType == null) throw new IllegalArgumentException("Unknown quest type");
    }

    private void validateDate(LocalDateTime registrationDate, LocalDateTime targetDate) {
        if (registrationDate.isAfter(targetDate)) throw new IllegalArgumentException(
                "Incorrect registration date or target date");
    }

    private Client mapClientDTOToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    private DealDTO mapDealEntityToDTO(Deal deal) {
        return modelMapper.map(deal, DealDTO.class);
    }
}

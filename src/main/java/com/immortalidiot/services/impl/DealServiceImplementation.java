package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.Manager;
import com.immortalidiot.entities.enums.QuestType;
import com.immortalidiot.repositories.impl.DealRepositoryImpl;
import com.immortalidiot.services.DealService;
import com.immortalidiot.services.dtos.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class DealServiceImplementation implements DealService {
    private DealRepositoryImpl dealRepository;
    private ClientServiceImplementation clientServiceImplementation;
    private ManagerServiceImplementation managerServiceImplementation;
    private final ModelMapper modelMapper;

    @Autowired
    public DealServiceImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void createDeal(ClientDTO clientDTO, String clientTerms) {
        Manager manager = managerServiceImplementation.getRandomManager(managerServiceImplementation.getAllManagers());

        Client client = mapClientDTOToEntity(clientDTO);
        clientServiceImplementation.registerClient(clientDTO);

        double amount = calculateCost(clientTerms);

        Deal deal = new Deal(manager, client, clientTerms, amount);

        dealRepository.save(deal);
    }

    private double calculateCost(String clientTerms) {
        // clientTerms example:
        // "bodyguards (description: questType); 14.04.2020 (description: dealDate);
        //             20.04.2020 (description: targetDate); other details"
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

        LocalDate registrationDate =LocalDate.parse(termsParts[1]);
        LocalDate targetDate = LocalDate.parse(termsParts[2]);
        validateDate(registrationDate, targetDate);

        long urgency = Duration.between(registrationDate, targetDate).toDays();

        if (urgency <= 7) {
            cost *= 1.8;
        } else if (urgency <= 14) {
            cost *= 1.6;
        } else if (urgency <= 21) {
            cost *= 1.4;
        } else { cost *= 1.2; }

        return cost;
    }

    private void validateQuestType(QuestType questType) {
        if (questType == null) throw new IllegalArgumentException("Unknown quest type");
    }

    private void validateDate(LocalDate registrationDate, LocalDate targetDate) {
        if (registrationDate.isAfter(targetDate)) throw new IllegalArgumentException(
                "Incorrect registration date or target date");
    }

    private Client mapClientDTOToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }
}

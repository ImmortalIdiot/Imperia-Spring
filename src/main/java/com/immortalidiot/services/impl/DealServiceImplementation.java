package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.Manager;
import com.immortalidiot.entities.enums.QuestType;
import com.immortalidiot.repositories.DealRepository;
import com.immortalidiot.services.DealService;
import com.immortalidiot.services.dtos.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class DealServiceImplementation implements DealService {
    private DealRepository dealRepository;
    private ClientServiceImplementation clientServiceImplementation;
    private ManagerServiceImplementation managerServiceImplementation;

    @Autowired
    public DealServiceImplementation(
            DealRepository dealRepository,
            ClientServiceImplementation clientServiceImplementation,
            ManagerServiceImplementation managerServiceImplementation) {
        this.dealRepository = dealRepository;
        this.clientServiceImplementation = clientServiceImplementation;
        this.managerServiceImplementation = managerServiceImplementation;
    }


    @Override
    @Transactional
    public void createDeal(ClientDTO clientDTO, String clientTerms) {
        Manager manager = managerServiceImplementation.getRandomManager(managerServiceImplementation.getAllManagers());

        Client client = new Client(clientDTO.getContact(), clientDTO.getName());
        clientServiceImplementation.registerClient(client);

        double amount = calculateCost(clientTerms);

        Deal deal = new Deal(manager, client, clientTerms, amount);


    }

    private double calculateCost(String clientTerms) {
        // clientTerms example:
        // "bodyguards (description: questType); 14.04.2020 (description: dealDate);
        //             20.04.2020 (description: targetDate); other details"
        String[] termsParts = clientTerms.split(";");

        QuestType questType = defineQuestType(termsParts[0]);
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

    private QuestType defineQuestType(String questType) {
        for (QuestType type : QuestType.values()) {
            if (type.getQuestType().equalsIgnoreCase(questType)) {
                return type;
            }
        }
        return null;
    }

    private void validateQuestType(QuestType questType) {
        if (questType == null) throw new IllegalArgumentException("Unknown quest type");
    }
}

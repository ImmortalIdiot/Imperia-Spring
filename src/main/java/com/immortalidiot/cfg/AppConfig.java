package com.immortalidiot.cfg;

import com.immortalidiot.repositories.*;
import com.immortalidiot.repositories.impl.*;
import com.immortalidiot.services.ClientService;
import com.immortalidiot.services.CultistService;
import com.immortalidiot.services.DealService;
import com.immortalidiot.services.QuestService;
import com.immortalidiot.services.impl.ClientServiceImplementation;
import com.immortalidiot.services.impl.CultistServiceImpl;
import com.immortalidiot.services.impl.DealServiceImplementation;
import com.immortalidiot.services.impl.QuestServiceImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ClientRepository clientRepository() {
        return new ClientRepositoryImpl();
    }

    @Bean
    public CultistRepository cultistRepository() {
        return new CultistRepositoryImpl();
    }

    @Bean
    public DealRepository dealRepository() {
        return new DealRepositoryImpl();
    }

    @Bean
    public ManagerRepository managerRepository() {
        return new ManagerRepositoryImpl();
    }

    @Bean
    public QuestRepository questRepository() {
        return new QuestRepositoryImpl();
    }

    @Bean
    public ClientService clientService() {
        return new ClientServiceImplementation(clientRepository(), modelMapper());
    }

    @Bean
    public DealService dealService() {
        return new DealServiceImplementation(dealRepository(), clientService(),
                                             managerRepository(), questService(), modelMapper());
    }

    @Bean
    public QuestService questService() {
        return new QuestServiceImplementation(dealRepository(), questRepository(), modelMapper());
    }

    @Bean
    CultistService cultistService() {
        return new CultistServiceImpl(cultistRepository(), modelMapper());
    }
}

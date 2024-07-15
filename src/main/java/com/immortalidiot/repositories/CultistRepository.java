package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CultistRepository {
    Cultist findByNickname(String nickname);
    List<Quest> findQuestsByCultistId(String id);
}

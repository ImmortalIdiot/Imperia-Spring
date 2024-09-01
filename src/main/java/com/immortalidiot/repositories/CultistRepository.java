package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;

import java.util.List;

public interface CultistRepository extends BaseRepository<Cultist, String> {
    List<Quest> findQuestsByCultistId(String id);
}

package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;

public interface CultistRepository {
    Cultist findByNickname(String nickname);
}

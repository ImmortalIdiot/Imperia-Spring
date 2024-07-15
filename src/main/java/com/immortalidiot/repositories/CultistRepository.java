package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import org.springframework.stereotype.Component;

@Component
public interface CultistRepository {
    Cultist findByNickname(String nickname);
}

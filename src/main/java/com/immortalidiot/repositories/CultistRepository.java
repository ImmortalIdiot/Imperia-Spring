package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultistRepository extends JpaRepository <Cultist, String> {

}

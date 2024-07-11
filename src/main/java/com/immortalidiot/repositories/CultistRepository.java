package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultistRepository extends JpaRepository <Cultist, String> {

}

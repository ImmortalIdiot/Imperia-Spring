package com.immortalidiot.repositories;

import com.immortalidiot.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository <Quest, Long> {

}

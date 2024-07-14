package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CultistRepository extends JpaRepository <Cultist, String> {
    @Query(value = "SELECT c FROM Cultist WHERE c.nickname = :nickname")
    Cultist findByNickname(@Param("nickname") String nickname);
}

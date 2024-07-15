package com.immortalidiot.repositories;

import com.immortalidiot.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    @Query(value = "SELECT d FROM Deal d WHERE d.id = (SELECT MAX(deal) FROM Deal deal)")
    Optional<Deal> findLatestDeal();
}

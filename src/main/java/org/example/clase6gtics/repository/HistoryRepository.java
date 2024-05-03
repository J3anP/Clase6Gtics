package org.example.clase6gtics.repository;

import org.example.clase6gtics.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}

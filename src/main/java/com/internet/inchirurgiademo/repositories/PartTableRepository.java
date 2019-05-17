package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.PartTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTableRepository extends JpaRepository<PartTable, Long> {
}

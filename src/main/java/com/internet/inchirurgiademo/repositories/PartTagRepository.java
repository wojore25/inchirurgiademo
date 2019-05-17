package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.PartTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTagRepository extends JpaRepository<PartTag, Long> {
}

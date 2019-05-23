package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.PartTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartTagRepository extends JpaRepository<PartTag, Long> {

    List<PartTag> findAllByPartNumberContaining(String search);
}

package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.TagTaxonomy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagTaxonomyRepository extends JpaRepository<TagTaxonomy, Long> {

    List<TagTaxonomy> findAllByTagType(String category);
}

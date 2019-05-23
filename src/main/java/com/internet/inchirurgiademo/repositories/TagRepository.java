package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.Tag;
import com.internet.inchirurgiademo.entities.TagTaxonomy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByType(TagTaxonomy type);
    Optional<Tag> findByName(String name);
}

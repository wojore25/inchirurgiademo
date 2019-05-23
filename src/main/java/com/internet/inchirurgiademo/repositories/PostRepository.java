package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.PartTag;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTagListContaining(Tag tag);
    Set<Post> findAllByTitleContaining(String search);
    Optional<Post> findFirstByPartTagListContaining(PartTag partTag);

}

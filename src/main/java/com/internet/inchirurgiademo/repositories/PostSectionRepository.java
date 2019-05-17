package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.PostSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostSectionRepository extends JpaRepository<PostSection, Long> {

    List<PostSection> findAllByPost(Post post);
}

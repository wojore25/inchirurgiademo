package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.PostRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRelationRepository extends JpaRepository<PostRelation, Long> {

    List<PostRelation> findAllByParentPostOrderByPositionAsc(Post post);
}

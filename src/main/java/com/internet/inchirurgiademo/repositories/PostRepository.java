package com.internet.inchirurgiademo.repositories;

import com.internet.inchirurgiademo.entities.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}

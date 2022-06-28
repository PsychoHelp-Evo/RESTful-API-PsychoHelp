package com.psycho.psychohelp.review.domain.persistence;

import com.psycho.psychohelp.review.domain.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}

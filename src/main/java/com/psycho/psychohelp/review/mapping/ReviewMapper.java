package com.psycho.psychohelp.review.mapping;

import com.psycho.psychohelp.review.domain.model.entity.Review;
import com.psycho.psychohelp.review.resource.CreateReviewResource;
import com.psycho.psychohelp.review.resource.ReviewResource;
import com.psycho.psychohelp.review.resource.UpdateReviewResource;
import com.psycho.psychohelp.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ReviewMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public ReviewResource toResource(Review model)
    {
        return mapper.map(model, ReviewResource.class);
    }

    public List<ReviewResource> toResource(List<Review> model)
    {
        return mapper.mapList(model, ReviewResource.class);
    }

    public Review toModel(CreateReviewResource resource)
    {
        return mapper.map(resource, Review.class);
    }

    public Review toModel(UpdateReviewResource resource)
    {
        return mapper.map(resource, Review.class);
    }
}

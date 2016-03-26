package com.aoe.astalift.product.repository;

import com.aoe.astalift.product.entity.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joey on 16-3-16.
 */
@Repository
public interface ThumbnailRepository extends JpaRepository<Thumbnail, Integer> {
}

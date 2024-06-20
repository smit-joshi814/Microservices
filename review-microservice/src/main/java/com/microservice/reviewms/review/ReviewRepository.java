package com.microservice.reviewms.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByCompanyId(Long companyId);
	
	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.companyId = :companyId")
    Double findAverageRatingByCompanyId(@Param("companyId") Long companyId);
}

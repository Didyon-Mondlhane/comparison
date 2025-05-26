package com.didyon.comparison_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.didyon.comparison_api.model.Comparison;

public interface ComparisonRepository extends JpaRepository<Comparison, Long> {
    List<Comparison> findAllByOrderByCreatedAtDesc();
    
    @Query("SELECT c FROM Comparison c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Comparison> searchByName(@Param("keyword") String keyword);
    
    @Query("SELECT c FROM Comparison c WHERE c.createdAt BETWEEN :startDate AND :endDate")
    List<Comparison> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                   @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT c FROM Comparison c WHERE :countryId MEMBER OF c.comparedCountryIds")
    List<Comparison> findByCountryIdInComparison(@Param("countryId") Long countryId);
    
    @Query("SELECT c FROM Comparison c WHERE :activityId MEMBER OF c.comparedActivityIds")
    List<Comparison> findByActivityIdInComparison(@Param("activityId") Long activityId);
}
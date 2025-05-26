package com.didyon.comparison_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.didyon.comparison_api.model.ProhibitedActivity;

public interface ActivityRepository extends JpaRepository<ProhibitedActivity, Long> {
    List<ProhibitedActivity> findByCountryId(Long countryId);
    List<ProhibitedActivity> findByEconomicSector(String sector);
    
    @Query("SELECT DISTINCT a.economicSector FROM ProhibitedActivity a")
    List<String> findAllEconomicSectors();
    
    @Query("SELECT a FROM ProhibitedActivity a WHERE a.country.id IN :countryIds")
    List<ProhibitedActivity> findByCountryIds(@Param("countryIds") List<Long> countryIds);
    
    @Query("SELECT a FROM ProhibitedActivity a WHERE a.economicSector IN :sectors")
    List<ProhibitedActivity> findBySectors(@Param("sectors") List<String> sectors);
    
    @Query("SELECT a FROM ProhibitedActivity a WHERE LOWER(a.activityName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ProhibitedActivity> searchByActivityName(@Param("keyword") String keyword);
}
package com.didyon.comparison_api.repository;

import com.didyon.comparison_api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByContinent(String continent);
    List<Country> findByRegion(String region);
    List<Country> findByOfficialLanguage(String language);
    
    @Query("SELECT c FROM Country c WHERE c.gdp BETWEEN :min AND :max")
    List<Country> findByGdpBetween(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
    
    @Query("SELECT c FROM Country c WHERE c.hdi BETWEEN :min AND :max")
    List<Country> findByHdiBetween(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
    
    @Query("SELECT c FROM Country c WHERE c.iloConventionsSigned >= :minConventions")
    List<Country> findByMinIloConventions(@Param("minConventions") Integer minConventions);
    
    List<Country> findByIndependenceYearBetween(Integer startYear, Integer endYear);
    
    @Query("SELECT DISTINCT c.continent FROM Country c")
    List<String> findAllContinents();
    
    @Query("SELECT DISTINCT c.region FROM Country c WHERE c.continent = :continent")
    List<String> findRegionsByContinent(@Param("continent") String continent);
    
    @Query("SELECT DISTINCT c.officialLanguage FROM Country c")
    List<String> findAllLanguages();
}
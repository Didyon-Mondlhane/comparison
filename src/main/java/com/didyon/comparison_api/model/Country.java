package com.didyon.comparison_api.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String continent;
    private String region;
    private BigDecimal gdp;
    private BigDecimal hdi;
    private String officialLanguage;
    private Integer independenceYear;
    
    @Column(name = "ilo_conventions_signed")
    private Integer iloConventionsSigned;
    
    @Column(name = "hazardous_activities_approval_year")
    private Integer hazardousActivitiesApprovalYear;
    
    @Column(name = "safety_legislation_strength")
    private String safetyLegislationStrength;
    
    @Column(name = "youth_percentage")
    private BigDecimal youthPercentage;
    
    @Column(name = "children_percentage")
    private BigDecimal childrenPercentage;
    
    @Column(name = "main_economic_sectors")
    private String mainEconomicSectors;
    
    @Column(name = "employment_sectors")
    private String employmentSectors;
    
    @Column(name = "education_level")
    private String educationLevel;
    
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProhibitedActivity> prohibitedActivities;
    
    public void addProhibitedActivity(ProhibitedActivity activity) {
        activity.setCountry(this);
        this.prohibitedActivities.add(activity);
    }
}
package com.didyon.comparison_api.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDTO {
    private Long id;
    private String name;
    private String continent;
    private String region;
    private BigDecimal gdp;
    private BigDecimal hdi;
    private String officialLanguage;
    private Integer independenceYear;
    private Integer iloConventionsSigned;
    private Integer hazardousActivitiesApprovalYear;
    private String safetyLegislationStrength;
    private BigDecimal youthPercentage;
    private BigDecimal childrenPercentage;
    private String mainEconomicSectors;
    private String employmentSectors;
    private String educationLevel;
}
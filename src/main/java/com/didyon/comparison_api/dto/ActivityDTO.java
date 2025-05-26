package com.didyon.comparison_api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDTO {
    private Long id;
    private String economicSector;
    private String activityName;
    private String justification;
    private String indicators;
    private Long countryId;
    private String countryName;
}
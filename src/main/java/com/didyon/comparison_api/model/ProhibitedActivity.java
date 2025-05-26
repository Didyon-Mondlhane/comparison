package com.didyon.comparison_api.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "prohibited_activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProhibitedActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "economic_sector", nullable = false)
    private String economicSector;
    
    @Column(name = "activity_name", nullable = false)
    private String activityName;
    
    @Column(columnDefinition = "TEXT")
    private String justification;
    
    private String indicators;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
package com.didyon.comparison_api.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comparisons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comparison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @ElementCollection
    @CollectionTable(name = "compared_countries", joinColumns = @JoinColumn(name = "comparison_id"))
    @Column(name = "country_id")
    private List<Long> comparedCountryIds = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(name = "compared_activities", joinColumns = @JoinColumn(name = "comparison_id"))
    @Column(name = "activity_id")
    private List<Long> comparedActivityIds = new ArrayList<>();
    
    @OneToMany(mappedBy = "comparison", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComparisonComment> comments = new ArrayList<>();
    
    @Column(name = "comparison_results", columnDefinition = "TEXT")
    private String comparisonResults;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    public void addComment(ComparisonComment comment) {
        comment.setComparison(this);
        this.comments.add(comment);
    }
}
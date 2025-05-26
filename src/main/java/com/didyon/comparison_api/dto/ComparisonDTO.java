package com.didyon.comparison_api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComparisonDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private List<Long> countryIds;
    private List<Long> activityIds;
    private String comparisonResults;
    private List<CommentDTO> comments;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class CommentDTO {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
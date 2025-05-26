package com.didyon.comparison_api.service;

import com.didyon.comparison_api.model.*;
import com.didyon.comparison_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComparisonService {
    private final ComparisonRepository comparisonRepository;
    private final CommentRepository commentRepository;
    private final CountryRepository countryRepository;
    private final ActivityRepository activityRepository;

    public List<Comparison> getAllComparisons() {
        return comparisonRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Comparison> getComparisonById(Long id) {
        return comparisonRepository.findById(id);
    }

    @Transactional
    public Comparison createComparison(String name, String description, 
                                     List<Long> countryIds, List<Long> activityIds) {
        Comparison comparison = Comparison.builder()
                .name(name)
                .description(description)
                .comparedCountryIds(countryIds)
                .comparedActivityIds(activityIds)
                .build();
        
        String results = generateComparisonResults(countryIds, activityIds);
        comparison.setComparisonResults(results);
        
        return comparisonRepository.save(comparison);
    }

    private String generateComparisonResults(List<Long> countryIds, List<Long> activityIds) {
        StringBuilder sb = new StringBuilder("<h3>Resultados da Comparação</h3>");
        
        if (countryIds != null && !countryIds.isEmpty()) {
            List<Country> countries = countryRepository.findAllById(countryIds);
            sb.append("<h4>Países Comparados</h4><ul>");
            countries.forEach(c -> sb.append("<li>").append(c.getName()).append("</li>"));
            sb.append("</ul>");
            
            sb.append("<h4>Análise Comparativa</h4>");
            sb.append("<p>Total de países: ").append(countries.size()).append("</p>");
        }
        
        if (activityIds != null && !activityIds.isEmpty()) {
            List<ProhibitedActivity> activities = activityRepository.findAllById(activityIds);
            sb.append("<h4>Atividades Proibidas</h4><ul>");
            activities.forEach(a -> sb.append("<li>")
                    .append(a.getActivityName())
                    .append(" (").append(a.getEconomicSector()).append(")")
                    .append("</li>"));
            sb.append("</ul>");
        }
        
        return sb.toString();
    }

    @Transactional
    public Comparison addCommentToComparison(Long comparisonId, String author, String content) {
        Comparison comparison = comparisonRepository.findById(comparisonId)
                .orElseThrow(() -> new RuntimeException("Comparação não encontrada"));
        
        ComparisonComment comment = ComparisonComment.builder()
                .author(author)
                .content(content)
                .comparison(comparison)
                .build();
        
        comparison.addComment(comment);
        return comparisonRepository.save(comparison);
    }

    @Transactional
    public Comparison updateComment(Long commentId, String newContent) {
        ComparisonComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
        
        comment.setContent(newContent);
        commentRepository.save(comment);
        return comment.getComparison();
    }

    @Transactional
    public void deleteComparison(Long id) {
        comparisonRepository.deleteById(id);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comparison> searchComparisons(String keyword) {
        return comparisonRepository.searchByName(keyword);
    }

    public List<Comparison> findComparisonsByDateRange(LocalDateTime start, LocalDateTime end) {
        return comparisonRepository.findByDateRange(start, end);
    }
}
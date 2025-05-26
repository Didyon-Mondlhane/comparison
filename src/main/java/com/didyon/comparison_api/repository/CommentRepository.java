package com.didyon.comparison_api.repository;

import com.didyon.comparison_api.model.ComparisonComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends JpaRepository<ComparisonComment, Long> {
    List<ComparisonComment> findByComparisonIdOrderByCreatedAtDesc(Long comparisonId);
    
    @Modifying
    @Query("UPDATE ComparisonComment c SET c.content = :content WHERE c.id = :id")
    void updateCommentContent(@Param("id") Long id, @Param("content") String content);
    
    @Query("SELECT c FROM ComparisonComment c WHERE LOWER(c.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ComparisonComment> searchByContent(@Param("keyword") String keyword);
}
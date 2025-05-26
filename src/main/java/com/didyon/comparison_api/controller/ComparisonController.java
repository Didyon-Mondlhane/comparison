package com.didyon.comparison_api.controller;

import com.didyon.comparison_api.model.*;
import com.didyon.comparison_api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/comparison")
@RequiredArgsConstructor
public class ComparisonController {
    private final ComparisonService comparisonService;
    private final CountryService countryService;
    private final ActivityService activityService;

    @GetMapping
    public String listComparisons(Model model) {
        model.addAttribute("comparison", comparisonService.getAllComparisons());
        return "comparison";
    }

    @GetMapping("/new")
    public String showComparisonForm(Model model) {
        model.addAttribute("comparison", new Comparison());
        model.addAttribute("countries", countryService.findAllCountries());
        model.addAttribute("activities", activityService.findAllActivities());
        return "comparison/new";
    }

    @PostMapping
    public String createComparison(@ModelAttribute Comparison comparison,
                                 @RequestParam(required = false) List<Long> countryIds,
                                 @RequestParam(required = false) List<Long> activityIds) {
        comparisonService.createComparison(
                comparison.getName(),
                comparison.getDescription(),
                countryIds,
                activityIds
        );
        return "redirect:/comparison";
    }

    @GetMapping("/{id}")
    public String viewComparison(@PathVariable Long id, Model model) {
        Comparison comparison = comparisonService.getComparisonById(id)
                .orElseThrow(() -> new RuntimeException("Comparação não encontrada"));
        
        model.addAttribute("comparison", comparison);
        model.addAttribute("countries", countryService.findAllCountries());
        model.addAttribute("activities", activityService.findAllActivities());
        
        return "comparison/view";
    }

    @PostMapping("/{id}/comments")
    public String addComment(@PathVariable Long id, @RequestParam String author, 
                           @RequestParam String content) {
        comparisonService.addCommentToComparison(id, author, content);
        return "redirect:/comparison/" + id;
    }

    @PostMapping("/comments/{commentId}/edit")
    public String updateComment(@PathVariable Long commentId, @RequestParam String content) {
        Comparison comparison = comparisonService.updateComment(commentId, content);
        return "redirect:/comparison/" + comparison.getId();
    }

    @PostMapping("/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) {
        comparisonService.deleteComment(commentId);
        return "redirect:/comparison";
    }

    @PostMapping("/{id}/delete")
    public String deleteComparison(@PathVariable Long id) {
        comparisonService.deleteComparison(id);
        return "redirect:/comparison";
    }

    @GetMapping("/compare")
    public String showComparePage(@RequestParam(required = false) List<Long> countryIds,
                                @RequestParam(required = false) List<Long> activityIds,
                                Model model) {
        if (countryIds != null && !countryIds.isEmpty()) {
            model.addAttribute("selectedCountries", countryService.findCountryById(countryIds));
        }
        if (activityIds != null && !activityIds.isEmpty()) {
            model.addAttribute("selectedActivities", activityService.findCountryById(activityIds));
        }
        
        model.addAttribute("countries", countryService.findAllCountries());
        model.addAttribute("activities", activityService.findAllActivities());
        
        return "comparison/compare";
    }
}
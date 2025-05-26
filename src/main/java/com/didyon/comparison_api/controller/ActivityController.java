package com.didyon.comparison_api.controller;

import com.didyon.comparison_api.model.ProhibitedActivity;
import com.didyon.comparison_api.service.ActivityService;
import com.didyon.comparison_api.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
    private final CountryService countryService;

    @GetMapping
    public String listActivities(@RequestParam(required = false) String search, Model model) {
        List<ProhibitedActivity> activities;
        if (search != null && !search.isEmpty()) {
            activities = activityService.searchActivities(search);
        } else {
            activities = activityService.findAllActivities();
        }
        model.addAttribute("activities", activities);
        model.addAttribute("sectors", activityService.findAllEconomicSectors());
        return "activities";
    }

    @GetMapping("/{id}")
    public String viewActivity(@PathVariable Long id, Model model) {
        model.addAttribute("activity", activityService.findActivityById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada")));
        return "activities/view";
    }

    @GetMapping("/new")
    public String showActivityForm(Model model) {
        model.addAttribute("activity", new ProhibitedActivity());
        model.addAttribute("countries", countryService.findAllCountries());
        return "activities/new";
    }

    @PostMapping
    public String saveActivity(@ModelAttribute ProhibitedActivity activity) {
        activityService.saveActivity(activity);
        return "redirect:/activities";
    }

    @GetMapping("/{id}/edit")
    public String editActivity(@PathVariable Long id, Model model) {
        model.addAttribute("activity", activityService.findActivityById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada")));
        model.addAttribute("countries", countryService.findAllCountries());
        return "activities/new";
    }

    @PostMapping("/{id}/delete")
    public String deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return "redirect:/activities";
    }

    @GetMapping("/by-country/{countryId}")
    public String listActivitiesByCountry(@PathVariable Long countryId, Model model) {
        model.addAttribute("activities", activityService.findActivitiesByCountry(countryId));
        model.addAttribute("country", countryService.findCountryById(countryId)
                .orElseThrow(() -> new RuntimeException("País não encontrado")));
        return "activities/list-by-country";
    }

    @GetMapping("/by-sector/{sector}")
    public String listActivitiesBySector(@PathVariable String sector, Model model) {
        model.addAttribute("activities", activityService.findActivitiesBySector(sector));
        model.addAttribute("sector", sector);
        return "activities/list-by-sector";
    }
}
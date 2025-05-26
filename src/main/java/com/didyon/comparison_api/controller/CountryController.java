package com.didyon.comparison_api.controller;

import com.didyon.comparison_api.model.Country;
import com.didyon.comparison_api.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public String listCountries(@RequestParam Map<String, String> filters, Model model) {
        model.addAttribute("countries", countryService.filterCountries(filters));
        model.addAttribute("continents", countryService.getAllContinents());
        model.addAttribute("languages", countryService.getAllLanguages());
        return "countries";
    }

    @GetMapping("/{id}")
    public String viewCountry(@PathVariable Long id, Model model) {
        model.addAttribute("country", countryService.findCountryById(id)
                .orElseThrow(() -> new RuntimeException("País não encontrado")));
        return "countries/view";
    }

    @GetMapping("/new")
    public String showCountryForm(Model model) {
        model.addAttribute("country", new Country());
        return "countries/new";
    }

    @PostMapping
    public String saveCountry(@ModelAttribute Country country) {
        countryService.saveCountry(country);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/edit")
    public String editCountry(@PathVariable Long id, Model model) {
        model.addAttribute("country", countryService.findCountryById(id)
                .orElseThrow(() -> new RuntimeException("País não encontrado")));
        return "country/new";
    }

    @PostMapping("/{id}/delete")
    public String deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return "redirect:/countries";
    }

    @GetMapping("/regions")
    @ResponseBody
    public List<String> getRegionsByContinent(@RequestParam String continent) {
        return countryService.getRegionsByContinent(continent);
    }
}
package com.didyon.comparison_api.service;

import com.didyon.comparison_api.model.Country;
import com.didyon.comparison_api.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> findCountryById(Long id) {
        return countryRepository.findById(id);
    }

    @Transactional
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    public List<Country> filterCountries(Map<String, String> filters) {
        if (filters.containsKey("continent")) {
            return countryRepository.findByContinent(filters.get("continent"));
        }
        if (filters.containsKey("minGdp") && filters.containsKey("maxGdp")) {
            BigDecimal min = new BigDecimal(filters.get("minGdp"));
            BigDecimal max = new BigDecimal(filters.get("maxGdp"));
            return countryRepository.findByGdpBetween(min, max);
        }
        return countryRepository.findAll();
    }

    public List<String> getAllContinents() {
        return countryRepository.findAllContinents();
    }

    public List<String> getRegionsByContinent(String continent) {
        return countryRepository.findRegionsByContinent(continent);
    }

    public List<String> getAllLanguages() {
        return countryRepository.findAllLanguages();
    }

    public List<Country> findByHdiRange(BigDecimal min, BigDecimal max) {
        return countryRepository.findByHdiBetween(min, max);
    }

    public List<Country> findByIloConventions(Integer minConventions) {
        return countryRepository.findByMinIloConventions(minConventions);
    }

    public Object findCountryById(List<Long> countryIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCountryById'");
    }
}
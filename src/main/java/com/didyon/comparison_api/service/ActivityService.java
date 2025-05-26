package com.didyon.comparison_api.service;

import com.didyon.comparison_api.model.ProhibitedActivity;
import com.didyon.comparison_api.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public List<ProhibitedActivity> findAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<ProhibitedActivity> findActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public List<ProhibitedActivity> findActivitiesByCountry(Long countryId) {
        return activityRepository.findByCountryId(countryId);
    }

    public List<ProhibitedActivity> findActivitiesBySector(String sector) {
        return activityRepository.findByEconomicSector(sector);
    }

    public List<String> findAllEconomicSectors() {
        return activityRepository.findAllEconomicSectors();
    }

    @Transactional
    public ProhibitedActivity saveActivity(ProhibitedActivity activity) {
        return activityRepository.save(activity);
    }

    @Transactional
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public List<ProhibitedActivity> searchActivities(String keyword) {
        return activityRepository.searchByActivityName(keyword);
    }

    public List<ProhibitedActivity> findActivitiesByCountryIds(List<Long> countryIds) {
        return activityRepository.findByCountryIds(countryIds);
    }

    public List<ProhibitedActivity> findActivitiesBySectors(List<String> sectors) {
        return activityRepository.findBySectors(sectors);
    }

    public Object findCountryById(List<Long> activityIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCountryById'");
    }
}
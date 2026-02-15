package com.olx.challenge;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Filters defects that apply to a given collection of cars,
 * matching by car model and year of issue.
 */
public class DefectService {

    private final DefectApiClient defectApi;

    public DefectService(DefectApiClient defectApi) {
        this.defectApi = defectApi;
    }

    /**
     * Finds all defects that affect at least one of the given cars.
     * A defect matches a car if the car's model equals the defect's model
     * and the car's year of issue is in the defect's affected years.
     *
     * @param cars collection of cars to check
     * @return immutable list of matching defects
     */
    public ImmutableList<Defect> findDefectsForCars(Collection<Car> cars) {
        var models = cars.stream()
                .collect(Collectors.groupingBy(Car::getCarModel));

        var defects = defectApi.findDefectsForModels(models.keySet());

        Predicate<Defect> atLeastOneCarMatches = defect -> {
            if (!models.containsKey(defect.getCarModel())) {
                return false;
            }
            return models.get(defect.getCarModel()).stream()
                    .anyMatch(car -> defect.getAffectedYears().contains(car.getYearOfIssue()));
        };

        return defects.stream()
                .filter(atLeastOneCarMatches)
                .collect(ImmutableList.toImmutableList());
    }
}

package main.java.com.olx.challenge;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefectService {
    private final DefectApiClient defectApi;

    public DefectService(DefectApiClient defectApi) {
        this.defectApi = defectApi;
    }

    public ImmutableList<Defect> findDefectsForCars(Collection<Car> cars) {

        var models =
                cars.stream()
                        .collect(Collectors.groupingBy(Car::getCarModel));
        var defects = defectApi.findDefectsForModels(models.keySet());
        Predicate<Defect> atLeastOneCarMatches = (defect) -> {
            if (!models.containsKey(defect.getCarModel())) {
                return false;
            }
            return models
                    .get(defect.getCarModel())
                    .stream()
                    .anyMatch(car -> defect.getAffectedYears().contains(car.getYearOfIssue()));
        };
        return defects.stream()
                .filter(atLeastOneCarMatches)
                .collect(ImmutableList.toImmutableList());

    }
}

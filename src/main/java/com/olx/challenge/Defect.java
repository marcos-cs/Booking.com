package com.olx.challenge;

import java.util.Set;

/**
 * Represents a defect associated with a car model and a set of affected years.
 */
public class Defect {

    private final String carModel;
    private final Set<Integer> affectedYears;
    private final String description;

    public Defect(String carModel, Set<Integer> affectedYears, String description) {
        this.carModel = carModel;
        this.affectedYears = affectedYears;
        this.description = description;
    }

    public String getCarModel() {
        return carModel;
    }

    public Set<Integer> getAffectedYears() {
        return affectedYears;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Defect{model='" + carModel + "', years=" + affectedYears + ", desc='" + description + "'}";
    }
}

package com.olx.challenge;

/**
 * Represents a car with a model name and year of issue.
 */
public class Car {

    private final String carModel;
    private final int yearOfIssue;

    public Car(String carModel, int yearOfIssue) {
        this.carModel = carModel;
        this.yearOfIssue = yearOfIssue;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    @Override
    public String toString() {
        return "Car{model='" + carModel + "', year=" + yearOfIssue + "}";
    }
}

package org.projectx.designpatterns;

import java.util.Optional;
import java.util.stream.Stream;

public class FunctionalChainOfResponsibility {

    record Car(String make, String model, int year,
               boolean passengerAirBags, boolean driverAirBags,
               boolean powerSteering, boolean driveLaneAssist, boolean rearCamera){ }


    interface Reviewer {
        Optional<String> review(Car car);
    }

    private final Reviewer carAndBuyer = car -> car.passengerAirBags() && car.driverAirBags() ? Optional.of("Car and Buyer") : Optional.empty();

    private final Reviewer carFax = car -> car.driverAirBags() ? Optional.of("CarFax") : Optional.empty();

    private final Reviewer rwPower = car -> car.rearCamera() && car.driveLaneAssist() && car.powerSteering() ? Optional.of("rwPower") : Optional.empty();

    Stream<Reviewer> reviewers = Stream.of(carAndBuyer, carFax, rwPower);

    public static void main(String[] args) {
        FunctionalChainOfResponsibility fcor = new FunctionalChainOfResponsibility();
        Car car =  new Car("Honda", "Accord", 2011, true, true, false, true, true);
        fcor.reviewers.flatMap(reviewer -> reviewer.review(car).stream()).forEach(System.out::println);
    }
}

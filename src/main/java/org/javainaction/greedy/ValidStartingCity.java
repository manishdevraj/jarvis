package org.javainaction.greedy;

/**
 * We have cities that go from a -> b in clock wise direction
 * We are given distances array that indicates from city i the distance[i] is required to reach at i + 1 city
 * Similarly we are given fuel array that indicates from city i the fuel[i] can be filled
 * Car has a particular mileage as mpg meaning if we have a fuel 5 and mileage 10 then we can go 50 miles with this refill.
 *
 * Find out the starting city such that we can traver clockwise and come back at starting city without running out of
 * fuel. Fact that there is only single valid starting city and total fuel is equal to fuel required to travel complete
 * circle.
 *
 * Distance {5, 25, 15, 10, 15}
 * Fuel {1, 2, 1, 0, 3} and mileage = 10
 *
 * Valid starting city is 4
 * @see GasStation
 */
public class ValidStartingCity {

    public int validStartingCity(int[] distances, int[] fuel, int mpg) {
        //this is cost to reach to ith city
        int milesRemaining = 0;
        int minMilesRemaining = 0;
        int startingCity = 0;

        for (int i = 1; i < distances.length; i++) {
            int prevDistance = distances[i - 1];
            int prevFuel = fuel[i - 1];
            //to reach at city i we need to use previous fuel by car's mileage
            //minus the distance we need to traven from i - 1 to i
            milesRemaining += (mpg * prevFuel) - prevDistance;

            //idea is to start from lowest miles at a given city
            //if we do not do that then there is no way we can complete the circle
            //with no fuel to pass through from city i
            //but if we start at city i then it might be possible to reach at this city
            if (milesRemaining < minMilesRemaining) {
                minMilesRemaining = milesRemaining;
                startingCity = i;
            }
        }
        return startingCity;
    }

    public static void main(String[] args) {
        int[] distances = new int[] {5, 25, 15, 10, 15};
        int[] fuel = new int[] {1, 2, 1, 0, 3};
        int mpg = 10;
        int expected = 4;
        var actual = new ValidStartingCity().validStartingCity(distances, fuel, mpg);
        System.out.println("Starting city at " + actual);
    }
}

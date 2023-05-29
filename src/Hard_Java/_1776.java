package Hard_Java;

import java.util.Stack;

public class _1776 {
	public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] collisionTimes = new double[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            collisionTimes[i] = -1.0; // Initialize collision time as -1 (indicating no collision)

            while (!stack.isEmpty()) {
                int j = stack.peek(); // Index of the car in front (if any)
                double time = getTimeToCollision(cars, i, j);

                if (time <= 0 || (collisionTimes[j] > 0 && time >= collisionTimes[j])) {
                    stack.pop(); // Remove the car in front if it won't be collided or it has a later collision time
                } else {
                    collisionTimes[i] = time; // Update collision time for the current car
                    break;
                }
            }

            stack.push(i); // Push the current car onto the stack
        }

        return collisionTimes;
    }

    private double getTimeToCollision(int[][] cars, int i, int j) {
        double distance = cars[j][0] - cars[i][0];
        double relativeSpeed = cars[i][1] - cars[j][1];

        if (relativeSpeed <= 0) {
            return -1.0; // No collision or the car in front is slower
        }

        return distance / relativeSpeed;
    }
}

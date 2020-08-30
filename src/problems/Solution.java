package problems;

import java.util.Arrays;

public class Solution {

    private double[] x;
    private double fitness;
    private boolean evaluated;

    public Solution(double[] x, double fitness) {
        this.x = x;
        this.fitness = fitness;
        evaluated = true;
    }

    public Solution(Solution s) {
        x = new double[s.x.length];
        fitness = s.fitness;
        evaluated = s.evaluated;
        System.arraycopy(s.x, 0, x, 0, s.x.length);
    }

    public Solution(int d) {
        x = new double[d];
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
        evaluated = true;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public boolean isEqual(Solution solution) {
        return Arrays.equals(x, solution.x);
    }
}

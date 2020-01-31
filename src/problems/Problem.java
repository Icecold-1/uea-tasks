package problems;

import java.util.List;
import java.util.Random;

public abstract class Problem {
    protected int numberOfDimensions;
    protected int maxFES;
    protected int FEScount = 0;
    protected double[] upperBounds;
    protected double[] lowerBounds;
    protected String name;

    protected static Random random = new Random();

    protected List<Solution> population;

    public Problem(int numberOfDimensions, int maxFES, String name) {
        this.name = name;
        this.numberOfDimensions = numberOfDimensions;
        this.maxFES = maxFES;
        upperBounds = new double[numberOfDimensions];
        lowerBounds = new double[numberOfDimensions];
    }

    public String getName() {
        return name;
    }

    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }

    public void resetEvaluations() {
        FEScount = 0;
    }

    public int getMaxFES() {
        return maxFES;
    }

    public double[] getUpperBounds() {
        return upperBounds;
    }

    public double[] getLowerBounds() {
        return lowerBounds;
    }

    public abstract double evaluate(double[] x);

    public void evaluate(Solution s) {
        s.setFitness(evaluate(s.getX()));
    }

    public Solution generateRandomSolution() {
        double[] randomSolution = new double[numberOfDimensions];
        for (int i = 0; i < numberOfDimensions; i++) {
            randomSolution[i] = lowerBounds[i] + (upperBounds[i] - lowerBounds[i]) * random.nextDouble();
        }

        return new Solution(randomSolution, evaluate(randomSolution));
    }

    public boolean isFeasible(Solution s) {
        double[] x = s.getX();

        for (int i = 0; i < numberOfDimensions; i++) {
            if (x[i] > upperBounds[i] || x[i] < lowerBounds[i])
                return false;
        }

        return true;
    }

    public boolean isFeasible(double[] x) {
        for (int i = 0; i < numberOfDimensions; i++) {
            if (x[i] > upperBounds[i] || x[i] < lowerBounds[i])
                return false;
        }

        return true;
    }

    public void setFeasible(double[] x) {
        for (int i = 0; i < numberOfDimensions; i++) {
            if (x[i] > upperBounds[i])
                x[i] = upperBounds[i];

            if (x[i] < lowerBounds[i])
                x[i] = lowerBounds[i];
        }
    }

    public int getFEScount() {
        return FEScount;
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

package problems;

import java.util.List;

public class DropWave extends Problem {
    List<Solution> population;
    public double maxFitness = -1.0;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public DropWave(int maxFES) {
        super(2, maxFES, "DropWave");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -5.12;
            upperBounds[i] = 5.12;
        }
    }

    @Override
    public double evaluate(double[] x) {
        if (FEScount > maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double x1 = x[0];
        double x2 = x[1];
        return -((1.0 + Math.cos(12.0 * Math.sqrt((x1 * x1) + (x2 * x2)))) / ((((x1 * x1) + (x2 * x2)) / 2.0) + 2.0));
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

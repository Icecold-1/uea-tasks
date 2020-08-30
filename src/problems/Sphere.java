package problems;

import java.util.List;

public class Sphere extends Problem {
    List<Solution> population;
    public double maxFitness = 80.0;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public Sphere(int maxFES) {
        super(2, maxFES, "Sphere");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -5.12;
            upperBounds[i] = 5.12;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double x1 = x[0];

        return x1*x1;
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

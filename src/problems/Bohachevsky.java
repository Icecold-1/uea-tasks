package problems;

import java.util.List;

public class Bohachevsky extends Problem {
    List<Solution> population;
    public double maxFitness = 30000.0;
    public Bohachevsky(int maxFES) {
        super(2, maxFES, "Bohachevsky");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -100;
            upperBounds[i] = 100;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double x1 = x[0];
        double x2 = x[1];

        return Math.pow(x1, 2) + (2*Math.pow(x2, 2))- (0.3 * Math.cos(3*Math.PI*x1))*(0.4*Math.cos(4*Math.PI*x2))+0.3;
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }
}

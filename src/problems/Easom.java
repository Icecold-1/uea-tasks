package problems;

import java.util.List;

public class Easom extends Problem {
    List<Solution> population;
    public double maxFitness = -1.0;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public Easom(int maxFES) {
        super(2, maxFES, "Easom");
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

        return -Math.cos(x1)*Math.cos(x2)*Math.exp(-Math.pow(x1-Math.PI,2)-Math.pow(x2-Math.PI,2));
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

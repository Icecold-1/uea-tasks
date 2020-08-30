package problems;

import java.util.List;

public class Michalewicz extends Problem {
    List<Solution> population;
    public double maxFitness = -1.8;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public Michalewicz(int maxFES) {
        super(2, maxFES, "Michalewicz");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = 0;
            upperBounds[i] = Math.PI;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double result = 0.0;
        double m = 10.0;

        for(int i = 1; i < 3; i++) {
            result = result - (Math.sin(x[i-1])*Math.pow(Math.sin((i*Math.pow(x[i-1],2)/Math.PI)), 2*m));
        }

        return result;
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

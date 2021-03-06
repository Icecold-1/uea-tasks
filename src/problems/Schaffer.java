package problems;

import java.util.List;

public class Schaffer extends Problem {
    List<Solution> population;
    public double maxFitness = 1.0;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public Schaffer(int maxFES) {
        super(2, maxFES, "Schaffer");
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

        return 0.5+(Math.pow(Math.sin(Math.pow(x1, 2)-Math.pow(x2, 2)-0.5), 2)/(Math.pow(1+0.001*(Math.pow(x1,2)+Math.pow(x2,2)),2)));
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

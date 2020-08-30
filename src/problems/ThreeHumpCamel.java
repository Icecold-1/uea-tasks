package problems;

import java.util.List;

public class ThreeHumpCamel extends Problem {
    List<Solution> population;
    public double maxFitness = 2500.0;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public ThreeHumpCamel(int maxFES) {
        super(2, maxFES, "ThreeHumpCamel");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -5;
            upperBounds[i] = 5;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double x1 = x[0];
        double x2 = x[1];

        return 2*Math.pow(x1,2)-1.05*Math.pow(x1,4)+(Math.pow(x1,6)/6)+x1*x2+Math.pow(x2,2);
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

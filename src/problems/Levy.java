package problems;

import java.util.List;

public class Levy extends Problem {
    List<Solution> population;
    public Levy(int maxFES) {
        super(2, maxFES, "Levy");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -10;
            upperBounds[i] = 10;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;
        double x1 = x[0];
        double x2 = x[1];

        return Math.pow(Math.sin(3*Math.PI*x1), 2) + Math.pow(x1 - 1, 2)*((1+Math.pow(Math.sin(3*Math.PI*x2), 2))+(Math.pow(x2 - 1, 2)*Math.pow(Math.sin(2*Math.PI*x2), 2)));
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

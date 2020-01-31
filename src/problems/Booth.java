package problems;

import java.util.List;

public class Booth extends Problem{
    List<Solution> population;
    public Booth(int maxFES) {
        super(2, maxFES, "Booth");
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

        return Math.pow(x1+2*x2-7, 2)+Math.pow(2*x1+x2-5, 2);
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

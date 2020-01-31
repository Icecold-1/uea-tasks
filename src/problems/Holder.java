package problems;

import java.util.List;

public class Holder extends Problem {
    List<Solution> population;
    public Holder(int maxFES) {
        super(2, maxFES, "Holder");
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

        return -(Math.sin(x1)*Math.cos(x2)*Math.exp(Math.abs(1-(Math.sqrt(Math.pow(x1,2)+Math.pow(x2, 2))/Math.PI))));
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

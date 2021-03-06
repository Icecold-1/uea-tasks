package problems;

import java.util.List;

public class Eggholder extends Problem {
    List<Solution> population;
    public double maxFitness = 2500.0;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public Eggholder(int maxFES) {
        super(2, maxFES, "Eggholder");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -512;
            upperBounds[i] = 512;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double x1 = x[0];
        double x2 = x[1];

        return -(x2+47)*Math.sin(Math.sqrt(Math.abs(x2+(x1/2)+47)))-x1*Math.sin(Math.sqrt(Math.abs(x1-(x2+47))));
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

package problems;

import java.util.List;

public class Branin extends Problem {
    List<Solution> population;
    public double maxFitness = 350.0 ;

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public Branin(int maxFES) {
        super(2, maxFES, "Branin");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -5;
            upperBounds[i] = 10;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double a = 1;
        double b = (5.1/(4*Math.pow(Math.PI,2)));
        double c = 5/Math.PI;
        double r = 6;
        double s = 10;
        double t = 1/(8*Math.PI);

        double x1 = x[0];
        double x2 = x[1];

        return a*Math.pow((x2-(b*Math.pow(x1, 2))+c*x1-r),2)+s*(1-t)*Math.cos(x1)+s;
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public void setPopulation(List<Solution> population) {
        this.population = population;
    }
}

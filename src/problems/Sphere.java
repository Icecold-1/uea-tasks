package problems;

public class Sphere extends Problem {
    public Sphere(int maxFES) {
        super(2, maxFES, "Sphere");
        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBounds[i] = -5.12;
            upperBounds[i] = 5.12;
        }
    }
    @Override
    public double evaluate(double[] x) {
        if(FEScount>maxFES)
            return Double.MAX_VALUE;
        FEScount++;

        double x1 = x[0];

        return x1*x1;
    }
}

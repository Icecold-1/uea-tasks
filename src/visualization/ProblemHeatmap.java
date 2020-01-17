package visualization;

import problems.*;

import java.io.*;

public class ProblemHeatmap {

    public static final String folderLocation = "ProblemLandscapes";

    public static void main(String[] args) throws IOException {

        int numOfPartitions = 1000;
        Problem problem = new DropWave(numOfPartitions * numOfPartitions);
        double lowerBound = problem.getLowerBounds()[0];
        double upperBound = problem.getUpperBounds()[0];

        double[][] data = new double[numOfPartitions][numOfPartitions];

        //TODO fill data

        for(int i = 0; i < numOfPartitions; i++) {
            for(int j = 0; j < numOfPartitions; j++) {
                data[i][j] = 3.44444444;
            }
        }

        String fileName = problem.getName() + "_" + numOfPartitions + ".txt";

        File file = new File(folderLocation + "/" + fileName);

        //TODO write data to file
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

        for(int i = 0; i < numOfPartitions; i++) {
            for (int j = 0; j < numOfPartitions; j++) {
                writer.write(Double.toString(data[i][j]));
                if(i!=numOfPartitions-1 && j!=numOfPartitions-1)
                    writer.newLine();
            }
        }

        writer.close();
    }

    public static double[][] GetProblemHeatmapValues(String problemName, int numOfPartitions) {

        double[][] data = new double[numOfPartitions][numOfPartitions];

        String file = folderLocation + "/" + problemName + "_" + numOfPartitions + ".txt";

        //TODO read data from file

        return data;
    }
}

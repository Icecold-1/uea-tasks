package visualization;

import problems.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProblemHeatmap {

    public static final String folderLocation = "ProblemLandscapes";


    public static void main(Problem problem) throws IOException {

        int numOfPartitions = 1000;
        double lowerBound = problem.getLowerBounds()[0];
        double upperBound = problem.getUpperBounds()[0];
        List<Solution> pop = problem.getPopulation();

        double[][] data = new double[numOfPartitions][numOfPartitions];

        //TODO fill data
        

        for(int i = 0; i < numOfPartitions; i++) {
            for(int j = 0; j < numOfPartitions; j++) {
                if((int)pop.get(i).getX()[0] != i && (int)pop.get(i).getX()[1] != j)
                    data[i][j] = Double.MAX_VALUE;
                else
                    data[i][j] = pop.get(i).getFitness();
            }
        }

        String fileName = problem.getName() + "_" + numOfPartitions + ".txt";

        File file = new File(folderLocation + "/" + fileName);

        //TODO write data to file
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

        for(int i = 0; i < numOfPartitions; i++) {
            for (int j = 0; j < numOfPartitions; j++) {
                if(data[i][j] != Double.MAX_VALUE) {
                    writer.write(i + " " + j + " " + data[i][j]);
                    writer.newLine();
                }
            }
        }
        writer.close();
    }

    public static double[][] GetProblemHeatmapValues(String problemName, int numOfPartitions) throws FileNotFoundException {

        double[][] data = new double[numOfPartitions][numOfPartitions];

        String file = folderLocation + "/" + problemName + "_" + numOfPartitions + ".txt";

        //TODO read data from file
        try {
            File output = new File(file);
            BufferedReader br = new BufferedReader(new FileReader(output));
            String line;
            while ((line = br.readLine())!=null) {
                String[] parsed = line.split(" ");
                int i = Integer.parseInt(parsed[0]);
                int j = Integer.parseInt(parsed[1]);
                double f = Double.parseDouble(parsed[2]);
                data[i][j] = f;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

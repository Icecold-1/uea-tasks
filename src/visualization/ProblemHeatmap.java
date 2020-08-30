package visualization;

import problems.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProblemHeatmap {

    public static final String folderLocation = "ProblemLandscapes";


    public static void main(Problem problem) throws IOException {
        int numOfPartitions = 1000;
        List<Solution> pop = problem.getPopulation();
        //TODO fill data -DONE

        String fileName = problem.getName() + "_" + numOfPartitions + ".txt";

        File file = new File(folderLocation + "/" + fileName);

        Files.deleteIfExists(file.toPath());

        //TODO write data to file -DONE
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        for(Solution s:pop) {
            writer.write(""+s.getFitness());
            writer.newLine();
        }
        writer.close();
    }

    public static double[][] GetProblemHeatmapValues(String problemName, int numOfPartitions) throws FileNotFoundException {

        double[][] data = new double[numOfPartitions][numOfPartitions];

        String file = folderLocation + "/" + problemName + "_" + numOfPartitions + ".txt";

        //TODO read data from file
        try {
            File output = new File(file);
            if(output.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(output));
                String line;
                int x = 0, y = 999;
                while ((line = br.readLine())!=null && x < 1000) {
                    double f = Double.parseDouble(line);
                    data[x][y] = f;
                    if (y == 0) {
                        y = 999;
                        x++;
                    } else
                        y--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

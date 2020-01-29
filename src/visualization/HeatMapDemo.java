package visualization;

import problems.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>This class shows the various options of the HeatMap.</p>
 *
 * <hr />
 * <p><strong>Copyright:</strong> Copyright (c) 2007, 2008</p>
 *
 * <p>HeatMap is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.</p>
 *
 * <p>HeatMap is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.</p>
 *
 * <p>You should have received a copy of the GNU General Public License
 * along with HeatMap; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA</p>
 *
 * @author Matthew Beckler (matthew@mbeckler.org)
 * @author Josh Hayes-Sheen (grey@grevian.org), Converted to use BufferedImage.
 * @author J. Keller (jpaulkeller@gmail.com), Added transparency (alpha) support, data ordering bug fix.
 * @version 1.6
 */

class HeatMapDemo extends JFrame implements ItemListener, FocusListener
{
    HeatMap panel;
    JCheckBox drawLegend;
    JCheckBox drawTitle;
    JCheckBox drawXTitle;
    JCheckBox drawXTicks;
    JCheckBox drawYTitle;
    JCheckBox drawYTicks;
    JTextField textTitle;
    JTextField textXTitle;
    JTextField textYTitle;
    JTextField textXMin;
    JTextField textXMax;
    JTextField textYMin;
    JTextField textYMax;
    JTextField textFGColor;
    JTextField textBGColor;
    JComboBox gradientComboBox;
    JComboBox problemComboBox;

    int numberOfPartitions = 1000;
    int numberOfEvaluations = 30000; //TODO set from UI
    boolean useGraphicsYAxis = false;

    ImageIcon[] icons;
    String[] names = {"GRADIENT_BLACK_TO_WHITE",
                      "GRADIENT_BLUE_TO_RED",
                      "GRADIENT_GREEN_YELLOW_ORANGE_RED",
                      "GRADIENT_HEAT",
                      "GRADIENT_HOT",
                      "GRADIENT_MAROON_TO_GOLD",
                      "GRADIENT_RAINBOW",
                      "GRADIENT_RED_TO_GREEN",
                      "GRADIENT_ROY"};
    Color[][] gradients = {Gradient.GRADIENT_BLACK_TO_WHITE,
                           Gradient.GRADIENT_BLUE_TO_RED,
                           Gradient.GRADIENT_GREEN_YELLOW_ORANGE_RED,
                           Gradient.GRADIENT_HEAT,
                           Gradient.GRADIENT_HOT,
                           Gradient.GRADIENT_MAROON_TO_GOLD,
                           Gradient.GRADIENT_RAINBOW,
                           Gradient.GRADIENT_RED_TO_GREEN,
                           Gradient.GRADIENT_ROY};

    Problem[] problems = {
            new DropWave(numberOfEvaluations),
            new Bohachevsky(numberOfEvaluations),
            new Booth(numberOfEvaluations),
            new Eggholder(numberOfEvaluations),
            new Holder(numberOfEvaluations),
            new Levy(numberOfEvaluations),
            new Schaffer(numberOfEvaluations),
            new Sphere(numberOfEvaluations)
    }; //TODO fill with available problems

    Problem selectedProblem;

    public HeatMapDemo() throws Exception
    {
        super("Problem Heat Map");
        
        // gui stuff to demonstrate options
        JPanel listPane = new JPanel();
        listPane.setLayout(new GridBagLayout());
        listPane.setBorder(BorderFactory.createTitledBorder("Problem Heat Map"));

        GridBagConstraints gbc;        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(2, 1, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        drawTitle = new JCheckBox("Draw Title");
        drawTitle.setSelected(true);
        drawTitle.addItemListener(this);
        listPane.add(drawTitle, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        drawLegend = new JCheckBox("Draw Legend");
        drawLegend.setSelected(true);
        drawLegend.addItemListener(this);
        listPane.add(drawLegend, gbc);
        
        drawXTitle = new JCheckBox("Draw X-Axis Title");
        drawXTitle.setSelected(true);
        drawXTitle.addItemListener(this);
        listPane.add(drawXTitle, gbc);
        
        drawXTicks = new JCheckBox("Draw X-Axis Ticks");
        drawXTicks.setSelected(true);
        drawXTicks.addItemListener(this);
        listPane.add(drawXTicks, gbc);
        
        drawYTitle = new JCheckBox("Draw Y-Axis Title");
        drawYTitle.setSelected(true);
        drawYTitle.addItemListener(this);
        listPane.add(drawYTitle, gbc);
        
        drawYTicks = new JCheckBox("Draw Y-Axis Ticks");
        drawYTicks.setSelected(true);
        drawYTicks.addItemListener(this);
        listPane.add(drawYTicks, gbc);
        
        listPane.add(Box.createVerticalStrut(10), gbc);
        
        JLabel label = new JLabel("Title:");
        listPane.add(label, gbc);
        
        textTitle = new JTextField();
        textTitle.addFocusListener(this);
        listPane.add(textTitle, gbc);
        
        label = new JLabel("X-Axis Title:");
        listPane.add(label, gbc);
        
        textXTitle = new JTextField();
        textXTitle.addFocusListener(this);
        listPane.add(textXTitle, gbc);

        label = new JLabel("Y-Axis Title:");
        listPane.add(label, gbc);

        textYTitle = new JTextField();
        textYTitle.addFocusListener(this);
        listPane.add(textYTitle, gbc);
        
        listPane.add(Box.createVerticalStrut(10), gbc);
        
        //14 is next row number
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        label = new JLabel("X min:");
        gbc.gridx = 0;
        gbc.gridy = 14;
        listPane.add(label, gbc);
        textXMin = new JTextField();
        textXMin.addFocusListener(this);
        gbc.gridy = 15;
        listPane.add(textXMin, gbc);
        
        label = new JLabel("X max:");
        gbc.gridx = 1;
        gbc.gridy = 14;
        listPane.add(label, gbc);
        textXMax = new JTextField();
        textXMax.addFocusListener(this);
        gbc.gridy = 15;
        listPane.add(textXMax, gbc);
        
        label = new JLabel("Y min:");
        gbc.gridx = 2;
        gbc.gridy = 14;
        listPane.add(label, gbc);
        textYMin = new JTextField();
        textYMin.addFocusListener(this);
        gbc.gridy = 15;
        listPane.add(textYMin, gbc);
        
        label = new JLabel("Y max:");
        gbc.gridx = 3;
        gbc.gridy = 14;
        listPane.add(label, gbc);
        textYMax = new JTextField();
        textYMax.addFocusListener(this);
        gbc.gridy = 15;
        listPane.add(textYMax, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        listPane.add(Box.createVerticalStrut(10), gbc);
        
        label = new JLabel("FG Color:");
        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.gridwidth = 2;
        listPane.add(label, gbc);
        textFGColor = new JTextField();
        textFGColor.addFocusListener(this);
        gbc.gridy = 18;
        listPane.add(textFGColor, gbc);
        
        label = new JLabel("BG Color:");
        gbc.gridx = 2;
        gbc.gridy = 17;
        listPane.add(label, gbc);
        textBGColor = new JTextField();
        textBGColor.addFocusListener(this);
        gbc.gridy = 18;
        listPane.add(textBGColor, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        listPane.add(Box.createVerticalStrut(10), gbc);

        //----------------------------------------------------------------------

        label = new JLabel("Gradient:");
        listPane.add(label, gbc);

        icons = new ImageIcon[names.length];
        Integer[] intArray = new Integer[names.length];
        for (int i = 0; i < names.length; i++)
        {
            intArray[i] = new Integer(i);
            icons[i] = createImageIcon(names[i] + ".gif");
        }

        gradientComboBox = new JComboBox(intArray);
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        gradientComboBox.setRenderer(renderer);
        gradientComboBox.addItemListener(this);

        listPane.add(gradientComboBox, gbc);

        //----------------------------------------------------------------------

        label = new JLabel("Problem:");
        listPane.add(label, gbc);

        Integer[] selectArray = new Integer[problems.length];
        for (int i = 0; i < problems.length; i++)
        {
            selectArray[i] = i;
        }

        problemComboBox = new JComboBox(selectArray);
        ProblemCbRenderer cbRenderer = new ProblemCbRenderer();
        problemComboBox.setRenderer(cbRenderer);
        problemComboBox.addItemListener(this);

        listPane.add(problemComboBox, gbc);

        //----------------------------------------------------------------------

        JButton OKButton = new JButton("Run EA");
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Running");
                // set max eval selectedProblem
                selectedProblem.resetEvaluations();
                panel.drawData(); //repaint the heatmap to clear previous population
                //TODO run EA on selected problem and draw population with drawPoint method
                //TODO map solution x values to x and y coordinates on the heatmap
                //TODO check useGraphicsYAxis
                Problem p = new DropWave(numberOfEvaluations);

                switch (problemComboBox.getSelectedIndex()) {
                    case 0:
                        p = new DropWave(numberOfEvaluations);
                        break;
                    case 1:
                        p = new Bohachevsky(numberOfEvaluations);
                        break;
                    case 2:
                        p = new Booth(numberOfEvaluations);
                        break;
                    case 3:
                        p = new Eggholder(numberOfEvaluations);
                        break;
                    case 4:
                        p = new Holder(numberOfEvaluations);
                        break;
                    case 5:
                        p = new Levy(numberOfEvaluations);
                        break;
                    case 6:
                        p = new Schaffer(numberOfEvaluations);
                        break;
                    case 7:
                        p = new Sphere(numberOfEvaluations);
                        break;
                }

                int eval = 0, a, b, c;
                List<Solution> population = new ArrayList<>();
                Solution best;
                double [] f = new double[1000];
                double S_best = 0;

                best = p.generateRandomSolution();
                population.add(new Solution(best));
                for(int j = 0 ; j < numberOfPartitions; j++) {
                    Solution s = p.generateRandomSolution();
                    population.add(s);
                    if(s.getFitness() < best.getFitness())
                        best = s;
                }
                Random r = new Random();
                double fy;
                while (eval < numberOfEvaluations) {
                    for(int i = 0; i < population.size(); i++) {
                        do {
                            a = r.nextInt(numberOfPartitions);
                        } while (i == a);
                        do {
                            b = r.nextInt(numberOfPartitions);
                        } while (i == b || a == b);
                        do {
                            c = r.nextInt(numberOfPartitions);
                        } while (i == c || a == c || b == c);
                        int R = r.nextInt(2);
                        double[] y = new double[2];
                        for(int j = 0; j < 2; j++) {
                            if((r.nextDouble()<0.4) || (j == R)) {
                                y[j] = population.get(a).getX()[j] + 0.4*(population.get(b).getX()[j]-population.get(c).getX()[j]);
                            }
                            else {
                                y[j] = population.get(i).getX()[j];
                            }
                            fy = p.evaluate(y);
                            eval++;
                            if(f[i]>fy) {
                                f[i] = fy;
                                population.get(i).setX(y);
                            }
                            if(eval>= numberOfEvaluations)
                                break;
                        }
                        population.get(i).setX(y);
                        p.evaluate(population.get(i).getX());

                    }
                }

            }
        });

        listPane.add(OKButton,gbc);

        //----------------------------------------------------------------------

        problemComboBox.setSelectedIndex(0);
        selectedProblem = problems[0];
        double[][] data =  ProblemHeatmap.GetProblemHeatmapValues(selectedProblem.getName(), numberOfPartitions);

        // you can use a pre-defined gradient:
        panel = new HeatMap(data, useGraphicsYAxis, Gradient.GRADIENT_RAINBOW);
        gradientComboBox.setSelectedIndex(6);

        // set miscellaneous settings
        panel.setDrawLegend(true);

        panel.setDrawTitle(true);

        panel.setXAxisTitle("X1");
        textXTitle.setText("X1");
        panel.setDrawXAxisTitle(true);

        panel.setYAxisTitle("X2");
        textYTitle.setText("X2");
        panel.setDrawYAxisTitle(true);

        panel.setTitle(selectedProblem.getName());
        textTitle.setText(selectedProblem.getName());

        panel.setCoordinateBounds(selectedProblem.getLowerBounds()[0], selectedProblem.getUpperBounds()[0], selectedProblem.getLowerBounds()[0], selectedProblem.getUpperBounds()[0]);

        textXMin.setText(selectedProblem.getLowerBounds()[0]+"");
        textXMax.setText(selectedProblem.getUpperBounds()[0]+"");
        textYMin.setText(selectedProblem.getLowerBounds()[0]+"");
        textYMax.setText(selectedProblem.getUpperBounds()[0]+"");

        panel.setDrawXTicks(true);
        panel.setDrawYTicks(true);

        panel.setColorForeground(Color.black);
        textFGColor.setText("000000");
        panel.setColorBackground(Color.white);
        textBGColor.setText("FFFFFF");
        
        
	    this.getContentPane().add(listPane, BorderLayout.EAST);
        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private double getXCoordinate(double x, double coef) {
        return 500 + (x * coef);
    }
    private double getYCoordinate(double y, double coef) {
        return 500 + (-y * coef);
    }
    public double[] Point(double[] p, double limit) {
        double[] result = new double[2];
        double coef = 500/limit;
        result[0] = getXCoordinate(p[0], coef);
        result[1] = getYCoordinate(p[1], coef);
        return result;
    }
    
    public void focusGained(FocusEvent e)
    {}
    
    public void focusLost(FocusEvent e)
    {
        Object source = e.getSource();
        
        if (source == textTitle)
        {
            panel.setTitle(textTitle.getText());
        }
        else if (source == textXTitle)
        {
            panel.setXAxisTitle(textXTitle.getText());
        }
        else if (source == textYTitle)
        {
            panel.setYAxisTitle(textYTitle.getText());
        }
        else if (source == textXMin)
        {
            double d;
            try
            {
                d = Double.parseDouble(textXMin.getText());
                panel.setXMinCoordinateBounds(d);
            }
            catch (Exception ex){}
        }
        else if (source == textXMax)
        {
            double d;
            try
            {
                d = Double.parseDouble(textXMax.getText());
                panel.setXMaxCoordinateBounds(d);
            }
            catch (Exception ex){}
        }
        else if (source == textYMin)
        {
            double d;
            try
            {
                d = Double.parseDouble(textYMin.getText());
                panel.setYMinCoordinateBounds(d);
            }
            catch (Exception ex){}
        }
        else if (source == textYMax)
        {
            double d;
            try
            {
                d = Double.parseDouble(textYMax.getText());
                panel.setYMaxCoordinateBounds(d);
            }
            catch (Exception ex){}
        }
        else if (source == textFGColor)
        {
            String c = textFGColor.getText();
            if (c.length() != 6)
                return;
            
            Color color = colorFromHex(c);
            if (color == null)
                return;

            panel.setColorForeground(color);
        }
        else if (source == textBGColor)
        {
            String c = textBGColor.getText();
            if (c.length() != 6)
                return;

            Color color = colorFromHex(c);
            if (color == null)
                return;

            panel.setColorBackground(color);
        }
        
    }
    
    private Color colorFromHex(String c)
    {
        try
        {
            int r = Integer.parseInt(c.substring(0, 2), 16);
            int g = Integer.parseInt(c.substring(2, 4), 16);
            int b = Integer.parseInt(c.substring(4, 6), 16);
            
            float rd = r / 255.0f;
            float gd = g / 255.0f;
            float bd = b / 255.0f;
            
            return new Color(rd, gd, bd);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    public void itemStateChanged(ItemEvent e)
    {
    	Object source = e.getItemSelectable();

        if (source == drawLegend)
        {
            panel.setDrawLegend(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (source == drawTitle)
        {
            panel.setDrawTitle(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (source == drawXTitle)
        {
            panel.setDrawXAxisTitle(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (source == drawXTicks)
        {
            panel.setDrawXTicks(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (source == drawYTitle)
        {
            panel.setDrawYAxisTitle(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (source == drawYTicks)
        {
            panel.setDrawYTicks(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (source == gradientComboBox)
        {
            // must be from the combo box
            Integer ix = (Integer) e.getItem();
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                panel.updateGradient(gradients[ix]);
            }
        }
        else if(source == problemComboBox) {
            Integer ix = (Integer) e.getItem();
            selectedProblem = problems[ix];

            updateProblemData();
        }
    }

    private void updateProblemData() {
        if(panel == null)
            return;

        double[][] data =  ProblemHeatmap.GetProblemHeatmapValues(selectedProblem.getName(), numberOfPartitions);
        panel.updateData(data, useGraphicsYAxis);

        panel.setTitle(selectedProblem.getName());
        textTitle.setText(selectedProblem.getName());

        panel.setCoordinateBounds(selectedProblem.getLowerBounds()[0], selectedProblem.getUpperBounds()[0], selectedProblem.getLowerBounds()[0], selectedProblem.getUpperBounds()[0]);

        textXMin.setText(selectedProblem.getLowerBounds()[0]+"");
        textXMax.setText(selectedProblem.getUpperBounds()[0]+"");
        textYMin.setText(selectedProblem.getLowerBounds()[0]+"");
        textYMax.setText(selectedProblem.getUpperBounds()[0]+"");
    }
    
    // this function will be run from the EDT
    private static void createAndShowGUI() throws Exception
    {
        HeatMapDemo hmd = new HeatMapDemo();
        hmd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hmd.setSize(1200, 800);
        hmd.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    createAndShowGUI();
                }
                catch (Exception e)
                {
                    System.err.println(e);
                    e.printStackTrace();
                }
            }
        });
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path)
    {
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL imgURL = classLoader.getResource(path);
        if (imgURL != null)
        {
            return new ImageIcon(imgURL);
        }
        else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    class ProblemCbRenderer extends JLabel implements ListCellRenderer
    {
        public ProblemCbRenderer()
        {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
            setVerticalAlignment(CENTER);
        }

        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus)
        {
            int selectedIndex = ((Integer)value).intValue();
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setText(problems[selectedIndex].getName());
            return this;
        }
    }

    
    class ComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        public ComboBoxRenderer()
        {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
            setVerticalAlignment(CENTER);
        }
        
        public Component getListCellRendererComponent(
                                                JList list,
                                                Object value,
                                                int index,
                                                boolean isSelected,
                                                boolean cellHasFocus)
        {
            int selectedIndex = ((Integer)value).intValue();
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            ImageIcon icon = icons[selectedIndex];
            setIcon(icon);
            setText(names[selectedIndex].substring(9));
            return this;
        }
    }
}

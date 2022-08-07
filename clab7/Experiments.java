import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int i=0; i < 5000 ; i++) {
            int num = r.nextInt(5000);
            bst.add(num);
            xValues.add(i+1);
            yValues.add(bst.AverageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(i+1));
        }
        XYChart chart1 = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart1.addSeries("ActualAverage", xValues, yValues);
        chart1.addSeries("OptimalAverage", xValues, y2Values);

        new SwingWrapper(chart1).displayChart();
    }

    public static void experiment2() {
        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int times = 10000;
        for (int i=0; i < 1000 ; i++) {
            int num = r.nextInt(times);
            bst.add(num);
        }
        for (int time = 1;time < times; time++) {
            xValues.add(time);
            bst = ExperimentHelper.BST_Delete_AS(bst,r,times);
            yValues.add(bst.AverageDepth());
        }
        XYChart chart2 = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart2.addSeries("deleteTakingSuccessor", xValues, yValues);
        new SwingWrapper(chart2).displayChart();

    }

    public static void experiment3() {
        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int times = 10000;
        for (int i=0; i < 1000 ; i++) {
            int num = r.nextInt(times);
            bst.add(num);
        }
        for (int time = 1;time < times; time++) {
            xValues.add(time);
            bst = ExperimentHelper.BST_Delete_S(bst,r,times);
            yValues.add(bst.AverageDepth());
        }
        XYChart chart3 = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart3.addSeries("deleteTakingRandom", xValues, yValues);
        new SwingWrapper(chart3).displayChart();
    }

    public static void main(String[] args) {
        experiment1();
        experiment2();
        experiment3();

    }
}

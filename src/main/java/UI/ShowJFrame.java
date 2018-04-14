package UI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ShowJFrame {

    //public Chart chart;
    public JTextArea text;
    public StandardChartTheme mChartTheme;
    public DefaultCategoryDataset mDataset;
    public CategoryDataset dataset;
    public JFreeChart mChart;
    public JTextField field;

    public ShowJFrame(){
        text = new JTextArea();
        field = new JTextField();

        mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("黑体",Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        mDataset = new DefaultCategoryDataset();
        dataset = mDataset;
        mChart = ChartFactory.createLineChart(
                "GA",//图名字
                "current generation",//横坐标
                "fitness/score",//纵坐标
                dataset,//数据集
                PlotOrientation.VERTICAL,
                true, // 显示图例
                true, // 采用标准生成器
                false);// 是否生成超链接

        CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
        mPlot.setOutlinePaint(Color.RED);//边界线

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        ChartPanel chartPanel = new ChartPanel(mChart);
        frame.add(chartPanel);
        JPanel textPanel = new JPanel();
        textPanel.add(text);
        frame.add(textPanel);
        JPanel fieldPanel = new JPanel();
        fieldPanel.add(field);
        field.setText("");
        frame.add(fieldPanel);

        frame.pack();
        frame.setVisible(true);
//        ChartFrame mChartFrame = new ChartFrame("折线图", mChart);
//        mChartFrame.pack();
//        mChartFrame.setVisible(true);
    }

    public void refresh(int curgeneration, int fitness, double score){
        CategoryPlot plot = (CategoryPlot)mChart.getPlot();
        mDataset.addValue(fitness, "Fitness", String.valueOf(curgeneration));
        mDataset.addValue(score, "Score", String.valueOf(curgeneration));
        dataset = mDataset;
        plot.setDataset(dataset);
    }
}

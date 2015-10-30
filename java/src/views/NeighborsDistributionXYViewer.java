package views;

import java.awt.Color;
import java.awt.Paint;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import interfaces.Graph;
import models.AdjacencyListGraph;

public class NeighborsDistributionXYViewer  extends ApplicationFrame {
	public NeighborsDistributionXYViewer(String title, Graph g) {
		super(title); 
		setContentPane(createDemoPanel(g));
	}
	private XYDataset createDataset(Graph g) {
		XYSeries serie = new XYSeries("Noeuds");
		int mean = 0;
		for (int i = 11; i < g.getV(); i++) { // Starting at 10 because first nodes have a lot more neighbors than others
			mean += g.getAdjacents(g.getVertex(i)).size();
			if (i % 10 == 0) {
				serie.add(i*10, mean/10);
				mean = 0;
			}
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie); 
		return dataset;
	}
	private JFreeChart createChart(XYDataset xyDataset) {
		JFreeChart chart = ChartFactory.createXYLineChart(      
				"Neighbors Distribution",  // chart title
				"Noeuds",
				"# Voisins",
				xyDataset,        // data   
				PlotOrientation.VERTICAL,
				false,           // include legend   
				true, 
				false);
		/*final CategoryPlot plot = chart.getCategoryPlot();
		CategoryItemRenderer renderer = this.new CustomRenderer();
		plot.setRenderer(renderer);
		plot.setBackgroundAlpha((float) 0.0);;
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());*/
		return chart;
	}
	public JPanel createDemoPanel(Graph g){
		JFreeChart chart = createChart(createDataset(g));  
		return new ChartPanel(chart); 
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			String path = args[0];
			Graph g = new AdjacencyListGraph(path);
			NeighborsDistributionXYViewer demo = new NeighborsDistributionXYViewer("Neighbors Distribution " + path, g);  
			demo.setSize(900, 600);
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);    
			demo.setVisible(true);
		}
	}
	class CustomRenderer extends BarRenderer
	{
	   public CustomRenderer() {
	   }
	   public Paint getItemPaint(final int row, final int column) {
	      return Color.black ;
	   }
	}
}

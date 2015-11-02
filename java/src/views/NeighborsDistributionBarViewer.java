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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import interfaces.Graph;
import models.AdjacencyListGraph;

public class NeighborsDistributionBarViewer  extends ApplicationFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NeighborsDistributionBarViewer(String title, Graph g) {
		super(title); 
		setContentPane(createDemoPanel(g));
	}
	private CategoryDataset createDataset(Graph g) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int mean = 0;
		for (int i = 101; i < g.getV(); i++) {
			mean += g.getAdjacents(g.getVertex(i)).size();
			if (i % 100 == 0) {
				dataset.addValue(mean, i+"", "");
				mean = 0;
			}
		}
		return dataset;
	}
	private JFreeChart createChart(CategoryDataset categoryDataset) {
		JFreeChart chart = ChartFactory.createBarChart(      
				"Neighbors Distribution",  // chart title
				"Noeuds",
				"# Voisins",
				categoryDataset,        // data   
				PlotOrientation.VERTICAL,
				false,           // include legend   
				true, 
				false);
		final CategoryPlot plot = chart.getCategoryPlot();
		CategoryItemRenderer renderer = this.new CustomRenderer();
		plot.setRenderer(renderer);
		plot.setBackgroundAlpha((float) 0.0);;
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setShadowVisible(false);
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
			NeighborsDistributionBarViewer demo = new NeighborsDistributionBarViewer("Neighbors Distribution " + path, g);  
			demo.setSize(900, 600);
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);    
			demo.setVisible(true);
		}
	}
	class CustomRenderer extends BarRenderer
	{
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	public CustomRenderer() {
	   }
	   public Paint getItemPaint(final int row, final int column) {
	      return Color.black ;
	   }
	}
}

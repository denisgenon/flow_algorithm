package views;

import java.awt.Color;
import java.awt.Paint;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryMarker;
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
import models.LinkedListGraph;

public class NeighborsDistributionBarViewer  extends ApplicationFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int median = 0;
	
	public NeighborsDistributionBarViewer(String title, Graph g) {
		super(title); 
		setContentPane(createDemoPanel(g));
	}
	private CategoryDataset createDataset(Graph g) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		int[] tab = new int[g.getV()];
		for (int i = 0; i < tab.length; i++) {
			tab[i] = 0;
		}
		for (int i = 0; i < g.getV(); i++) {
			tab[g.getAdjacentsSize(i)] += 1;
			
		}
		int count = 0;
		boolean set = true;
		for (int i = 0; i < tab.length; i++) {
			//System.out.println(tab[i]);
		}
		for (int i = 0; i < tab.length; i++) {
			//TODO
			count += tab[i];

			if (count > g.getV()/2 && set) {
				this.median = i;
				System.out.println(i);
				set = false;
			}
			if (tab[i] == 0 && i > 5) {
				break;
			}
			dataset.addValue(tab[i], i+"", i+"");
		}
		return dataset;
	}
	private JFreeChart createChart(CategoryDataset categoryDataset) {
		JFreeChart chart = ChartFactory.createBarChart(      
				"Neighbors Distribution",  // chart title
				"# de Voisins",
				"# de Noeuds",
				categoryDataset,        // data   
				PlotOrientation.VERTICAL,
				false,           // include legend   
				true, 
				true);
		final CategoryPlot plot = chart.getCategoryPlot();
		CategoryItemRenderer renderer = this.new CustomRenderer();
		plot.setRenderer(renderer);
		plot.setBackgroundAlpha((float) 0.0);;
		
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setShadowVisible(false);
		
		
		CategoryMarker marker = new CategoryMarker(this.median);  // position is the value on the axis
		marker.setPaint(Color.red);

		plot.addDomainMarker(marker);
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
			Graph g = new LinkedListGraph(path);
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

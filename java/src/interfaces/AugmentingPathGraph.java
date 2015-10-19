package interfaces;

import object.Vertex;

public interface AugmentingPathGraph extends Graph{

	public int getMinFlow(Vertex[] path);

	public int getFlowValue();

	public void applyPath(int capacity, Vertex [] path);
}

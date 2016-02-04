package solver;

import interfaces.Graph;

public abstract class AugmentingPathScaling extends AugmentingPath {
	protected int source = 0;
	protected int sink = 0;
	/**
	 * @param g, the representation of the instance
	 */
	public AugmentingPathScaling(Graph g) {
		super.g = g;
		this.source = 0;
		this.sink = g.getV() - 1;
		timeStart=System.currentTimeMillis();
		int delta = (int) Math.pow(2, Math.log(g.getMaxCapacity()));
		while(delta>=1 && !timeout) {
			int [] myPath = getPath(delta);
			if(myPath == null) { // If there is no path found with the current delta value, we update the delta
				delta = (int) Math.round(delta/2);
			}
			else {
				applyPath(getMinFlow(myPath),myPath);
			}
			timeout=(System.currentTimeMillis()-timeStart)>limitTime;
		}
	}
	
	/**
	 * @param g, the representation of the instance
	 * @param source, the source node
	 * @param sink, the sink node
	 */
	public AugmentingPathScaling(Graph g, int source, int sink) {
		super.g = g;
		this.source = source;
		this.sink = sink;
		timeStart=System.currentTimeMillis();
		int delta = (int) Math.pow(2, Math.log(g.getMaxCapacity()));
		while(delta>=1 && !timeout) {
			int [] myPath = getPath(delta);
			if(myPath == null) { // If there is no path found with the current delta value, we update the delta
				delta = (int) Math.round(delta/2);
			}
			else {
				applyPath(getMinFlow(myPath),myPath);
			}
			timeout=(System.currentTimeMillis()-timeStart)>limitTime;
		}
	}
	
	public abstract int [] getPath(int delta);
	
	public int [] getPath(){
		return null;
	}
}
package solver;

import interfaces.Graph;

public abstract class AugmentingPathScaling extends AugmentingPath {
	protected int source = 0;
	protected int sink = 0;

	/**
	 * @param g, the representation of the instance
	 * @param source, the source node
	 * @param sink, the sink node
	 */
	public AugmentingPathScaling(Graph g, int source, int sink) {
		super.g = g;
		this.source = source;
		this.sink = sink;
		int addedFlow = 0;
		timeStart = System.currentTimeMillis();
		int delta = g.getMaxCapacity();
		int jfjf = 0;

		while (delta >= 1 && !timeout) {
			addedFlow = getPath(delta);
			if(addedFlow == 0) { // If there is no path found with the current delta value, we update the delta
				delta = (int) Math.round(delta/2);
			}
			else {
				super.flow += addedFlow;
			}
			jfjf++;
			timeout=(System.currentTimeMillis()-timeStart)>limitTime;
		}
		System.out.println(jfjf);

	}
	
	public abstract int getPath(int delta);
	public int getPath() {
		return 0;
	}
}
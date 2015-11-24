package solver;

import interfaces.Graph;

public abstract class AugmentingPathScaling extends AugmentingPath {
	/**
	 * @param g, the representation of the instance
	 */
	public AugmentingPathScaling(Graph g) {
		super.g = g;
		timeStart=System.currentTimeMillis();
		int delta = (int) (2*Math.log(g.getMaxCapacity()));
		while(delta>=1) {
			int [] myPath = getPath(delta);
			if(myPath == null) { // If there is no path found with the current delta value, we update the delta
				delta = (int) Math.round(delta/2);
			}
			else {
				applyPath(getMinFlow(myPath),myPath);
			}
		}
	}
	
	public abstract int [] getPath(int delta);
	
	public int [] getPath(){
		return null;
	}
}
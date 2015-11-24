package solver;

import interfaces.Graph;

public abstract class AugmentingPathScaling extends AugmentingPath{

	public AugmentingPathScaling(Graph g) {
		super.g = g;
		timeStart=System.currentTimeMillis();
		int delta = (int) (2*Math.log(g.getMaxCapacity()));
		while(delta>=1 && !timeout) {
			int [] myPath = getPath(delta);
			if(myPath == null) {
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
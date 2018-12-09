package HarvestPackage;

/**
 * @author RYANF This is intended to be used in conjuction with the linkList.
 */
public class Link {
	
	//Link have the wieght and the plot number.
	protected Link next;
	protected int plotNum;
	protected double correctedWeight;
	

	/**
	 * @param plotNum is plot num
	 * @param correctedWeight is weight.
	 */
	public Link(int plotNum, double correctedWeight) {
		this.correctedWeight = correctedWeight;
		this.plotNum = plotNum;
	}
	
	/**
	 * returns plot number and yeild as a message string.
	 */
	public String displayLink() {
		return "Plot Number:  " + plotNum + "   Yield :  " + correctedWeight;
	}
}

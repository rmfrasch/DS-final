package HarvestPackage;

/**
 * @author RYANF This program is intended to take in weights, and moistures of a
 *         corn plot, normalize the yield to 15 percent (To make make good
 *         determination of weight. This Defines the field object, with name,
 *         passes, ranges, harvest patter, as well as taking in information that
 *         will be used in the application. You need to install JavaFX into
 *         elipse for this to work.
 */
public class fieldDefinition {
	// Name of the field
	private String fieldName;
	// Users starting and ending passes. They can start anywhere, like 1, or 100.
	// Similarly for ending pass.
	private int startP;
	private int endP;
	// Users starting and ending ranges.
	private int startR;
	private int endR;
	// the Harvest Pattern the user selected. Will be used to determine the harvest
	// order of the field in the 'NextPlot()' method.
	private String harvestPattern;

	// Used to pull into the label for the current plot so the user can see which
	// plot he's on.
	private int currPass;
	private int currRange;

	// Used to pull in the hypothetical next pass in the labels for the next plot. I
	// only implemented this, and only just realized I can just grabbed the text for
	// current to update it.
	// But I don't have time for this, so another time to change it.
	private int nextPass;
	private int nextRange;

	// Number of ranges per pass, passes and plots per field. Because the users can
	// start on any pass or range number, this is for the number of passes and
	// ranges, not what the user started on and ended on in the beginning screen
	private int totalRangesPerPass;
	private int totalPassesPerField;
	private int plotNum;

	// To compare to the plotNum, so I know when you should be done with the field.
	private int runningPlotNum;

	// LinkedList to hold information for the labels in the iowaField() window. My
	// intention was to implement a grid with values, but didn't have time. it holds
	// yield of past plots.
	protected Queue myQueue;

	// Used to house values. The field Array is used to record all the yields, and
	// then implementing a binary tree to order them and output to he terminal.
	protected Double[][] fieldArray;
	protected BinaryTree fieldTree;

	// Used to determine how to harvest the field. The harvest direction is
	// neccessary to aid the pattern.
	private String harvestDirection;
	private String nextHarvestDirection;

	// Only used in Circular harvesting mode... to keep track of the number of
	// passes harvested on either side.
	private int passesOnEitherSideDone;

	// to keep track of the next plot nums. Again, I would change this if I had
	// time, but clock is ticking.
	private int nextRunningPlotNum;
	private int nextPassesOnEitherSideDone;

	protected fieldDefinition(String fieldName, int startP, int endP, int startR, int endR, String harvestPattern) {
		super();
		this.fieldName = fieldName;
		this.startP = startP;
		this.endP = endP;
		this.startR = startR;
		this.endR = endR;
		this.harvestPattern = harvestPattern;
		this.currPass = startP;
		this.currRange = startR;
		this.nextPass = startP;
		this.nextRange = startR;
		this.totalRangesPerPass = endR - startR + 1;
		this.totalPassesPerField = endP - startP + 1;
		this.plotNum = totalRangesPerPass * totalPassesPerField;
		this.myQueue = new Queue(3);
		this.runningPlotNum = 1;
		this.nextRunningPlotNum = 1;
		this.fieldTree = new BinaryTree();
		this.fieldArray = new Double[totalPassesPerField][totalRangesPerPass];
		this.harvestDirection = "up";
		this.nextHarvestDirection = "up";

		this.passesOnEitherSideDone = 0;
		this.nextPassesOnEitherSideDone = 0;

	}

	// Getters and setters. I kept having to change get various items, so I made
	// getters and setter for all so make it esaier on programming.
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getStartP() {
		return startP;
	}

	public void setStartP(int startP) {
		this.startP = startP;
	}

	public int getEndP() {
		return endP;
	}

	public void setEndP(int endP) {
		this.endP = endP;
	}

	public int getStartR() {
		return startR;
	}

	public void setStartR(int startR) {
		this.startR = startR;
	}

	public int getEndR() {
		return endR;
	}

	public void setEndR(int endR) {
		this.endR = endR;
	}

	public String getHarvestPattern() {
		return harvestPattern;
	}

	public void setHarvestPattern(String harvestPattern) {
		this.harvestPattern = harvestPattern;
	}

	public int getCurrPass() {
		return currPass;
	}

	public void setCurrPass(int currPass) {
		this.currPass = currPass;
	}

	public int getCurrRange() {
		return currRange;
	}

	public void setCurrRange(int currRange) {
		this.currRange = currRange;
	}

	public int getNextPass() {
		return nextPass;
	}

	public void setNextPass(int nextPass) {
		this.nextPass = nextPass;
	}

	public int getNextRange() {
		return nextRange;
	}

	public void setNextRange(int nextRange) {
		this.nextRange = nextRange;
	}

	public int getTotalRangesPerPass() {
		return totalRangesPerPass;
	}

	public void setTotalRangesPerPass(int totalRangesPerPass) {
		this.totalRangesPerPass = totalRangesPerPass;
	}

	public int getTotalPassesPerField() {
		return totalPassesPerField;
	}

	public void setTotalPassesPerField(int totalPassesPerField) {
		this.totalPassesPerField = totalPassesPerField;
	}

	public int getPlotNum() {
		return plotNum;
	}

	public void setPlotNum(int plotNum) {
		this.plotNum = plotNum;
	}

	public int getRunningPlotNum() {
		return runningPlotNum;
	}

	public void setRunningPlotNum(int runningPlotNum) {
		this.runningPlotNum = runningPlotNum;
	}

	public Queue getMyQueue() {
		return myQueue;
	}

	public void setMyQueue(Queue myQueue) {
		this.myQueue = myQueue;
	}

	public Double[][] getFieldArray() {
		return fieldArray;
	}

	public void setFieldArray(Double[][] fieldArray) {
		this.fieldArray = fieldArray;
	}

	public String getHarvestDirection() {
		return harvestDirection;
	}

	public void setHarvestDirection(String harvestDirection) {
		this.harvestDirection = harvestDirection;
	}

	public String getNextHarvestDirection() {
		return nextHarvestDirection;
	}

	public void setNextHarvestDirection(String nextHarvestDirection) {
		this.nextHarvestDirection = nextHarvestDirection;
	}

	public int getPassesOnEitherSideDone() {
		return passesOnEitherSideDone;
	}

	public void setPassesOnEitherSideDone(int passesOnEitherSideDone) {
		this.passesOnEitherSideDone = passesOnEitherSideDone;
	}

	public int getNextRunningPlotNum() {
		return nextRunningPlotNum;
	}

	public void setNextRunningPlotNum(int nextRunningPlotNum) {
		this.nextRunningPlotNum = nextRunningPlotNum;
	}

	public int getNextPassesOnEitherSideDone() {
		return nextPassesOnEitherSideDone;
	}

	public void setNextPassesOnEitherSideDone(int nextPassesOnEitherSideDone) {
		this.nextPassesOnEitherSideDone = nextPassesOnEitherSideDone;
	}

	/**
	 * This method is called when going through the plot. Becuase you turn around,
	 * and harvest from back to front, you need to keep track of which direction you
	 * are heading. In terms of elegance, this is the best code I have written...
	 * even though I could improve it more with a switch statment.
	 */
	public void nextPlot() {
		if (runningPlotNum > plotNum) {
			currRange = 0;
			currPass = 0;
			return;
		}

		// Serpentine and Circular patterns have an 'up' or 'down'. This is due to the
		// nature of harvesting a plot. You harvest from front to back, and then from
		// back to front. This saves time, especially with large fields.
		if (this.getHarvestPattern().compareTo("Serpentine") == 0) {
			if (harvestDirection.compareTo("up") == 0) {
				// If the range is same as total ranges in a pass, then harvest down.
				if (currRange == totalRangesPerPass) {
					this.setHarvestDirection("down");
					// advance pass
					currPass++;
				} else {
					// advance range
					currRange++;
				}
				// if the range is same as the first range, harvest back up the field
			} else if (harvestDirection.compareTo("down") == 0) {
				if (currRange == 1) {
					this.setHarvestDirection("up");
					// advance pass
					currPass++;
				} else {
					// decrease range
					currRange--;
				}
			}
			// This regular pattern is easier, since in the field, you are harvesting up the
			// field, and then coming back to harvest from the front again. Only 1 way
			// harvesting.
		} else if (this.getHarvestPattern().compareTo("Regular") == 0) {
			if (currRange == totalRangesPerPass) {
				currRange = 1;
				currPass++;
			} else {
				currRange++;
			}
			// Circular is similar to serpentine, but with the kicker is harvesting the
			// first pass up the field, last pass down the field, second pass up,
			// penultimate pass down... etc
		} else if (this.getHarvestPattern().compareTo("Circular") == 0) {
			if (harvestDirection.compareTo("up") == 0) {
				if (currRange == totalRangesPerPass) {
					this.setHarvestDirection("down");
					// This passesOnEitherSideDone variable is needed to keep track of which pass
					// you'll be on on either side of the field when you are doing circular.
					currPass = endP - passesOnEitherSideDone;
				} else {
					currRange++;
				}
			} else if (harvestDirection.compareTo("down") == 0) {
				if (currRange == 1) {
					this.setHarvestDirection("up");
					passesOnEitherSideDone++;
					currPass = startP + passesOnEitherSideDone;
				}
			} else {
				currRange--;
			}
		}
		// This is to keep track of the number of plots the person is on. Once plot
		// number equals this running counter, the field is done.
		runningPlotNum++;
		if (runningPlotNum > plotNum) {
			currRange = 0;
			currPass = 0;
			return;
		}
	}

	// This is to keep track of the next plot labels.... this is not ideal, but its
	// the same as the nextPlot() method above. Same as above, with variables being
	// slightly different.
	public void nextNextPlot() {
		if (nextRunningPlotNum > plotNum) {
			nextRange = 0;
			nextPass = 0;
			return;
		}
		if (this.getHarvestPattern().compareTo("Serpentine") == 0) {
			if (nextHarvestDirection.compareTo("up") == 0) {
				if (nextRange == totalRangesPerPass) {
					this.setNextHarvestDirection("down");
					nextPass++;
				} else {
					nextRange++;
				}
			} else if (nextHarvestDirection.compareTo("down") == 0) {
				if (nextRange == 1) {
					this.setNextHarvestDirection("up");
					nextPass++;
				} else {
					nextRange--;
				}
			}

		} else if (this.getHarvestPattern().compareTo("Regular") == 0) {
			if (nextRange == totalRangesPerPass) {
				nextRange = 1;
				nextPass++;
			} else {
				nextRange++;
			}
		} else if (this.getHarvestPattern().compareTo("Circular") == 0) {
			if (nextHarvestDirection.compareTo("up") == 0) {
				if (nextRange == totalRangesPerPass) {
					this.setNextHarvestDirection("down");
					nextPass = endP - nextPassesOnEitherSideDone;
				} else {
					nextRange++;
				}
			} else if (nextHarvestDirection.compareTo("down") == 0) {
				if (nextRange == 1) {
					this.setNextHarvestDirection("up");
					nextPassesOnEitherSideDone++;
					nextPass = startP + nextPassesOnEitherSideDone;
				}
			} else {
				nextRange--;
			}
		}

		nextRunningPlotNum++;
		if (nextRunningPlotNum > plotNum) {
			nextRange = 0;
			nextPass = 0;
			return;
		}

	}
}

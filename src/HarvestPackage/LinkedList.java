package HarvestPackage;

/**
 * @author RYANF This program is intended to created a linked list with a few
 *         commands. This is to keep track of the recent yield of previous plots.  Holds 3 nodes, displaying each so the user can see if there are any dicrepencies or errors that happen, like havesting into another plot, or running over plants, etc.
 */
/**
 * @author RYANF
 *
 */
public class LinkedList {
	private Link first;
	private Link last;
	private int maxSize;
	private int count;

	/**
	 * Constructor - makes empty list.
	 */
	public LinkedList() {
		
		first = null;
	}

	/**
	 * @param maxNum for the Queue, Constructor
	 */
	public LinkedList(int maxNum) {
		
		first = null;
		maxSize = maxNum;
		count = 0;
	}

	/**
	 * @return true if the list is empty
	 */
	public boolean isEmpty() {
		return first==null;

	}

	
	/**
	 * @param plotNum is just what you would expect, the plot number that you are doing in the field.
	 * @param correctedWeight This is the corrected weight standardized to 15 percent moisture, which is an unofficial standard farmers and cooperative like to have data in, so they more accurately predict the price.
	 */
	public void insertLast(int plotNum, double correctedWeight) {
		//Ticks the count up
		count++;
		Link x = new Link(plotNum, correctedWeight);
		//if the isn't full, then make new link label it first
		
		if (isEmpty()) {
			first = x;
			last = x;
		}
		//If queue is not full.
		else if(!isFull())  {
			last.next = x;
			last = x;
		}
		//If queue is is full, pop to make room for the next number.
		else if(isFull()) {
			deleteFirst();
			insertLast(plotNum, correctedWeight);
		}

	}

	/**
	 * @return deletes the first one, and turns the next one into first. 
	 */
	public void deleteFirst() {
		//decreases the count
		count--;
		//If the count isn't 0, the first link is also the temp link, first is now the next link, output deleted message.
		if (count != 0) {
			Link temp = first;
			first = first.next;
		
		//Will look more into using exceptions.
		} else if (count <= 0) {
			count++;
		}
	}

	/**
	 * Displays the items in the Linked List.
	 */
	public void displayList() {
		System.out.println("Here is the full list");
		Link current = first;
		// displays the current item, and then marks the next item current to display it
		// unless there are no items, or current = null.
		while (current != null) {
			System.out.println(current.displayLink());
			current = current.next;
		}
		System.out.println("");
	}

	/**
	 * Displays first link.
	 */
	public String getFirst() {
		return first.displayLink();
	}

	/**
	 * Checks to see if linked list is full, which it never is.
	 */
	public boolean isFull() {
		if(count > maxSize) {
			return true;
		}
		else {
			return false;
		}
	}
	public void qSize() {
		System.out.println("The size of the linked list is:  " + maxSize);
	}
	
}

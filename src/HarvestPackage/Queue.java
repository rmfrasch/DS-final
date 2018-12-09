package HarvestPackage;

/**
 * @author RYANF This is a queue used to store infomation about previous plots and display it to the user.  This is implemented with a LinkedList.
 *
 */
public class Queue {
	LinkedList queueList;
	
	/**
	 * Default constructor... size of queue unlimited
	 */
	public Queue() {
		queueList = new LinkedList();
	}
	
	/**
	 * @param queueSize Constructor that takes queue size into account.
	 */
	public Queue(int queueSize) {
		queueList = new LinkedList(queueSize);
	}
	
	/**
	 * Sees the size of the Queue
	 */
	public void size() {
		queueList.qSize();
	}
	
	/**
	 * @param whatContents enters in data as a string
	 */
	public void add(int plotNum, double yield) {
		queueList.insertLast(plotNum, yield);
	}
	
	/**
	 * removes a link from the front of the queue.
	 */
	public void remove() {
		queueList.deleteFirst();
	}
	
	/**
	 * See what the first link is.
	 * @return 
	 */
	public String peek() {
		return queueList.getFirst();
	}
	
	/**
	 * Prints out the list of link contents
	 */
	public void print() {
		queueList.displayList();
	}
	
	/**
	 * Checks if full.
	 */
	public void isFull() {
		queueList.isFull();
	}
	
	/**
	 * Checks if empty.
	 */
	public void isEmpty() {
		queueList.isEmpty();
	}
}

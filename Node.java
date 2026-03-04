
public class Node {

    private HurricaneRowData value = null;
    private Node next = null;
    private Node previous = null;

    /**
     * Default constructor
     */
    public Node() {

    }

    /**
     * Constructor
     * 
     * @param value The hurricane data passed into the node
     */
    public Node(HurricaneRowData value) {
        this.value = value;
    }

    /**
     * Gets the data points inside a given hurricane data row
     * 
     * @return The data points inside the given hurricane data row
     */
    public HurricaneRowData getValue() {
        return value;
    }

    /**
     * Check to see if this node has a next node
     * 
     * @return True if there is a next node, false otherwise
     */
    public boolean hasNext() {

        if (this.next != null) {
            return true;
        }

        return false;
    }

    /**
     * Sets the next node
     * 
     * @param next The node to set as next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Gets the next node
     * 
     * @return The next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Check if this node has a previous node
     * 
     * @return True if there is a previous, false otherwise
     */
    public boolean hasPrevious() {

        if (this.previous != null) {
            return true;
        }

        return false;
    }

    /**
     * Sets the previous node
     * 
     * @param previous The node to set as previous
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * Gets the previous node
     * 
     * @return The previous node
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     * Pretty-prints the data in a hurricane row
     * 
     * @return The formatted data string
     */
    public String toString() {
        String output = String.format("%10d %10d %10d %10d %10d", value.getYear(), value.getAce(), value.getNumStorms(),
                value.getCat1To5(), value.getCat3To5());
        return output;
    }
}

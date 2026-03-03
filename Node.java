
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
     * @param value The value passed into the node
     */
    public Node(HurricaneRowData value) {
        this.value = value;
    }

    public HurricaneRowData getValue() {
        return value;
    }

    public boolean hasNext() {

        if (this.next != null) {
            return true;
        }

        return false;
    }

    public void setNext(Node next) {
        this.next = next;
        next.previous = this;
    }

    public Node getNext() {
        return next;
    }

    public boolean hasPrevious() {

        if (this.previous != null) {
            return true;
        }

        return false;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
        previous.next = this;
    }

    public Node getPrevious() {
        return previous;
    }

    public HurricaneRowData getData() {
        return this.value;
    }

    public String toString() {
        return "";
    }
}

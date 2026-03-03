
public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface {

    private Node head = null;
    private Node tail = null;

    public Node getFirst() {
        return head;
    }

    public Node getLast() {
        return tail;
    }

    public Node remove(HurricaneRowData toRemove) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    public void insert(HurricaneRowData newValue) {
        Node newNode = new Node(newValue);

        // case if list is empty
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // case if new value is smaller than head, insert before current head, then set
        // new references to new head and its tail
        if (newValue.getAce() <= head.getValue().getAce()) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            return;
        }

        // traverse list if new value should be inserted after head
        Node current = head;
        while (current != null && current.getData().getAce() < newValue.getAce()) {
            current = current.getNext();
        }

        // case if inserting after the tail
        if (current == null) {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        } else {
            // case if inserting somewhere between head and tail
            Node before = current.getPrevious();
            newNode.setNext(current);
            newNode.setPrevious(before);
            current.setPrevious(newNode);
            if (before != null) {
                before.setNext(newNode);
            }
        }
    }

    public boolean contains(HurricaneRowData data) {
        return false;
    }

    public Node getByValue(HurricaneRowData value) {
        return null;
    }

    @Override
    public String toString() {

        String output = ""; // placeholder
        return output;
    }

}

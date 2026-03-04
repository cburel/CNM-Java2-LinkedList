
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

        // if list is empty
        if (head == null) {
            System.out.println("No node to remove!");
            return null;
        }

        // if node is head
        if (head.getValue() == toRemove) {
            return head;
        }

        // if node is tail
        if (tail.getValue() == toRemove) {
            return tail;
        }

        // if node is between head and tail
        // TODO: finish this
        Node temp = new Node(toRemove);

        return temp;
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
        while (current != null && current.getValue().getAce() < newValue.getAce()) {
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

        if (head == null) {
            return false;
        }

        Node current = head;
        while (current.hasNext()) {
            if (current.getValue().getAce() == data.getAce()
                    && current.getValue().getCat1To5() == data.getCat1To5()
                    && current.getValue().getCat3To5() == data.getCat3To5()
                    && current.getValue().getNumStorms() == data.getNumStorms()
                    && current.getValue().getYear() == data.getYear()) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Node getByValue(HurricaneRowData value) {
        Node current = head;
        while (current != null) {
            if (current.getValue() == value) {
                return current;
            }
        }

        System.out.println("Value " + value + " was not found in the list.");

        return null;
    }

    @Override
    public String toString() {

        Node current = head;
        String output = ""; // placeholder

        while (current != null) {
            output += current.toString() + "\n";
        }

        return output;
    }

}

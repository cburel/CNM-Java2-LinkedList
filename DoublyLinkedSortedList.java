
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

        Node current = head;

        while (current != null) {
            if (current.getValue().getYear() == toRemove.getYear()) {

                Node before = current.getPrevious();
                Node after = current.getNext();

                // update the previous node
                if (before != null) {
                    before.setNext(after);
                } else {

                    // there was no previous node
                    head = after;
                }

                // update the next node
                if (after != null) {
                    after.setPrevious(before);
                } else {

                    // there was no next node
                    tail = before;
                }

                // disconnect the node to remove
                current.setNext(null);
                current.setPrevious(null);
                return current;

            }

            current = current.getNext();
        }

        // node was not found in the list
        return null;
    }

    public void insert(HurricaneRowData newValue) {

        Node newNode = new Node(newValue);

        // case if list is empty
        if (head == null && tail == null) {
            head = tail = newNode;
            return;
        }

        // case if node is null. disallow. this would break things.
        if (newValue == null) {
            System.out.println("Node cannot be null.");
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
        while (current != null) {

            int currentAce = current.getValue().getAce();
            int newAce = newValue.getAce();
            int currentYear = current.getValue().getYear();
            int newYear = newValue.getYear();

            // keep going if the current ACE is smaller or ACE is equal and current year is
            // greater than new year
            if (currentAce <= newAce) {
                current = current.getNext();
            } else if (currentAce == newAce && currentYear < newYear) {

                // ACE is equal, but current year is older, so keep going
                current = current.getNext();
            } else {

                // the new node either has a higher ACE or a newer year. exit the loop
                break;
            }
        }

        // insert the node
        if (current == null) {

            // insert at the tail
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        } else {

            // insert before current
            Node before = current.getPrevious();

            newNode.setNext(current);
            newNode.setPrevious(before);
            current.setPrevious(newNode);

            if (before != null) {
                before.setNext(newNode);
            } else {
                head = newNode;
            }
        }
    }

    public boolean contains(HurricaneRowData data) {

        if (head == null) {
            return false;
        }

        Node current = head;
        while (current != null) {
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
            } else {
                current = current.getNext();
            }
        }

        System.out.println("Value " + value + " was not found in the list.");

        return null;
    }

    @Override
    public String toString() {

        Node current = head;
        String output = "";

        while (current != null) {
            output += current.toString() + "\n";
            current = current.getNext();
        }

        return output;
    }

}

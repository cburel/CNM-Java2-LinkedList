
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

        // if node is null
        if (toRemove == null) {
            System.out.println("Node is null!");
            return null;
        }

        // if node is head
        if (head.getValue().getYear() == toRemove.getYear()) {

            head = head.getNext();

            if (head != null) {
                head.setPrevious(null);
            } else {

                // list is empty
                tail = null;
            }

            return head;
        }

        // if node is tail
        if (tail.getValue().getYear() == toRemove.getYear()) {

            tail = tail.getPrevious();

            if (tail != null) {
                tail.setNext(null);
            } else {

                // list is empty
                head = null;
            }

            return tail;
        }

        // if node is between head and tail
        Node current = head;

        while (current != null) {
            if (current.getValue().getYear() == toRemove.getYear()) {

                // the node was found
                Node remove = current;

                // if the node is the head
                if (current == head) {
                    head = current.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    } else {

                        // list is now empty
                        tail = null;
                    }
                }

                // if the node is the tail
                else if (current == tail) {
                    tail = current.getPrevious();
                    if (tail != null) {
                        tail.setNext(null);
                    } else {

                        // list is now empty
                        head = null;
                    }
                }

                // if the node is between head and tail
                else {
                    Node before = current.getPrevious();
                    Node after = current.getNext();
                    before.setNext(after);
                    after.setPrevious(before);
                }

                // remove the node from the list
                remove.setNext(null);
                return remove;

            }

            current = current.getNext();
        }

        // node wasn't found
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

            // if the current ACE is smaller, keep going
            if (currentAce <= newAce) {
                current = current.getNext();
            }

            // the ACEs are the same, but the current year is smaller. put the newer year in
            // front of the older one.
            else if (currentAce == newAce && current.getValue().getYear() > newValue.getYear()) {
                current = current.getPrevious();
            }

            // otherwise, exit the loop
            else {
                break;
            }
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
            before.setNext(newNode);
            current.setPrevious(newNode);
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
        }

        return output;
    }

}

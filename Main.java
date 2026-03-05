
/**
 * @author Celeste Burel
 * Date: 3/8/2026
 * Purpose: Refactor the Hurricane assignment to use a doubly linked list instead of an array list
 * Sources: https://www.w3schools.com/java/java_generics.asp
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // tracks hurricane data
        int year, ace, numStorms, cat1To5, cat3To5;

        // setup
        File file = new File("ace.csv");
        DoublyLinkedSortedList rowData = new DoublyLinkedSortedList();

        // try to open the file and read it into rowData arraylist if it exists
        try (Scanner scanner = new Scanner(file)) {

            // while there is more data, iterate
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // split the CSV values on comma
                String[] data = line.split(",");

                try {
                    // insert each value into its respective variable, turning strings into ints so
                    // we can pass it into the new hurricane row data object
                    year = Integer.parseInt(data[0].trim());
                    ace = Integer.parseInt(data[1].trim());
                    numStorms = Integer.parseInt(data[2].trim());
                    cat1To5 = Integer.parseInt(data[3].trim());
                    cat3To5 = Integer.parseInt(data[4].trim());

                    // create a new row of data
                    HurricaneRowData newData = new HurricaneRowData(year, ace, numStorms, cat1To5, cat3To5);
                    rowData.insert(newData);
                } catch (NumberFormatException e) {

                    // if the read value is not an integer, it should not be placed in the row. skip
                    // it and start the loop again
                    continue;
                }
            }
        } catch (FileNotFoundException e) {

            // if the file doesn't exist, display an error and exit the program
            System.err.println("File " + file.getName() + "not found");
            System.exit(1);
        }

        // print the ultimate result to the console and to a txt file
        writeOutput(rowData);

        // section to test various methods. comment/uncomment as desired. all results
        // print to the console.
        System.out.println("====== TESTING METHODS ======");

        // tests the method that checks if a list contains a specific hurricane row data
        // object
        System.out.println();
        testContains();

        // tests the method that gets a specific hurricane row data object from a list
        System.out.println();
        testGetByValue();

        // tests removing a node from a list
        System.out.println();
        testRemove();

        // tests to ensure correct backwards node linkage by printing a list in reverse
        // order
        System.out.println();
        printReverse();

        // tests to ensure a list's length is as expected
        System.out.println();
        checkLength();
    }

    /**
     * Handles writing the value of the max ACE and its year to the console and an
     * output.txt file
     * 
     * @param rowData The array list of data to use.
     */
    private static void writeOutput(DoublyLinkedSortedList rowData) {

        // for printing to console and txt file
        String output = "Year of max ace: ";
        String header = "All data in order of Ace: ";

        // set up for list traversal. we're going in descending order of ACE, so start
        // at the tail.
        Node current = rowData.getLast();
        HurricaneRowData data = current.getValue();
        int maxYear = data.getYear();

        // for printing the "year of max ace" line
        output += maxYear + "\n";

        // for printing the "all data in order of ace" header
        output += header + "\n";

        // while there exists a node, add its data to the output as a string
        while (current != null) {
            output += current.toString() + "\n";

            // go down the list by one node
            current = current.getPrevious();
        }

        // display the year where the max ACE occurred in the console. uncomment for
        // debug
        // System.out.println(output);

        // output the year where the max ACE occurred into a txt file
        Path path = Path.of("output.txt");

        try {
            // write the output to txt file
            Files.writeString(path, output);
            System.out.println("File created.");
        } catch (Exception e) {
            System.err.println("Could not write to file " + path);
        }
    }

    /**
     * Tests the contains method in DoublyLinkedSortedList.java
     */
    public static void testContains() {

        DoublyLinkedSortedList newList = new DoublyLinkedSortedList();

        // this could probably be done better but it's staying this way at least for now
        HurricaneRowData contains = new HurricaneRowData(2023, 15, 3, 3, 2);
        HurricaneRowData contains1 = new HurricaneRowData(2026, 10, 5, 2, 1);
        HurricaneRowData contains2 = new HurricaneRowData(1993, 5, 3, 1, 2);

        // add the hurricane data rows to the list
        newList.insert(contains);
        newList.insert(contains1);
        newList.insert(contains2);

        // grab the head
        Node current = newList.getFirst();

        // while there exists a node, get the year and print it. will print in ascending
        // order by ACE since we're using the head and following by next
        System.out.println("TESTING CONTAINS:");
        System.out.println("Printing list:");
        while (current != null) {
            System.out.println(current.toString());
            current = current.getNext();
        }

        // checks to see if the list contains a specific row and returns true or false.
        // prints result to the console
        System.out.println("List contains expected value:");
        System.out.println(newList.contains(contains1));
    }

    /**
     * Tests the getByValue method in DoublyLinkedSortedList.java
     */
    public static void testGetByValue() {

        DoublyLinkedSortedList newList = new DoublyLinkedSortedList();

        // this could be done better too
        HurricaneRowData value = new HurricaneRowData(2026, 10, 5, 2, 1);
        HurricaneRowData value2 = new HurricaneRowData(2029, 11, 5, 2, 1);

        // insert the values into the list
        newList.insert(value);
        newList.insert(value2);

        // grab the head of the list
        Node current = newList.getFirst();

        // traverse the list
        while (current != null) {
            if (current.hasNext()) {
                current = current.getNext();
            } else {
                break;
            }

            // print results to the console
            System.out.println("TESTING GET BY VALUE");
            System.out.println("The value of the gotten row is:");
            System.out.println(newList.getByValue(current.getValue()));
        }
    }

    /**
     * Tests the remove method in DoublyLinkedSortedList.java
     */
    public static void testRemove() {
        DoublyLinkedSortedList newList = new DoublyLinkedSortedList();

        // TODO: change this later?
        HurricaneRowData value = new HurricaneRowData(2027, 10, 5, 2, 1);
        HurricaneRowData value2 = new HurricaneRowData(2029, 10, 5, 2, 1);
        HurricaneRowData value3 = new HurricaneRowData(2026, 10, 5, 2, 1);

        // insert the values into the list
        newList.insert(value);
        newList.insert(value2);
        newList.insert(value3);

        // grab the head of the list
        Node current = newList.getFirst();

        System.out.println("TESTING NODE REMOVAL");
        System.out.println("Printing list:");

        // traverses the list and prints each node to the console
        while (current != null) {
            System.out.println(current.toString());
            current = current.getNext();
        }

        System.out.println("REMOVING NODE");
        System.out.println("List after removal: ");

        // remove a given node
        newList.remove(value);

        // print the list without the removed node to the console
        System.out.println(newList.toString());
    }

    /**
     * Tests printing the list in reverse order to ensure correct back-pointer links
     */
    public static void printReverse() {

        DoublyLinkedSortedList newList = new DoublyLinkedSortedList();

        // TODO: change this later?
        HurricaneRowData value = new HurricaneRowData(2027, 11, 5, 2, 1);
        HurricaneRowData value2 = new HurricaneRowData(2029, 12, 5, 2, 1);
        HurricaneRowData value3 = new HurricaneRowData(2026, 13, 5, 2, 1);

        // insert the values into the list
        newList.insert(value);
        newList.insert(value2);
        newList.insert(value3);

        System.out.println("Printing list in reverse:");

        // grab the tail of the list
        Node current = newList.getLast();

        // traverse the list backwards and print each node to the console
        while (current != null) {
            System.out.println(current.toString());
            current = current.getPrevious();
        }
    }

    public static void checkLength() {
        DoublyLinkedSortedList newList = new DoublyLinkedSortedList();

        // TODO: change this later?
        HurricaneRowData value = new HurricaneRowData(2027, 11, 5, 2, 1);
        HurricaneRowData value2 = new HurricaneRowData(2029, 12, 5, 2, 1);
        HurricaneRowData value3 = new HurricaneRowData(2026, 13, 5, 2, 1);

        // insert the values into the list
        newList.insert(value);
        newList.insert(value2);
        newList.insert(value3);

        System.out.println("TESTING LENGTH OF LIST");
        System.out.println("Checking the length of a list. Expected: 3");

        // grab the head of the list
        Node current = newList.getFirst();

        // track how many times we've gone to a new node
        int count = 0;
        while (current != null) {

            // we're on a new node. increment the counter
            count++;

            // go to the next node
            current = current.getNext();
        }

        // print the results to the console
        System.out.print("The current length of the list is: ");
        System.out.println(count);
    }
}
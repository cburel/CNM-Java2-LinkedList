
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

        testContains();

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
        Node link = rowData.getLast();
        HurricaneRowData data = link.getValue();
        int maxYear = data.getYear();

        // for printing the "year of max ace" line
        output += maxYear + "\n";

        // for printing the "all data in order of ace" header
        output += header + "\n";

        // while there exists a node, add its data to the output as a string
        while (link != null) {
            output += link.toString() + "\n";

            // go down the list by one node
            link = link.getPrevious();
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
        Node link = newList.getFirst();

        // while there exists a node, get the year and print it. will print in ascending
        // order since we're using the head and following by next
        while (link != null) {
            System.out.println(link.getValue().getYear());
            link = link.getNext();
        }

        // checks to see if the list contains a specific row and returns true or false
        System.out.println(newList.contains(contains1));
    }

    public static void testGetByValue() {

    }
}
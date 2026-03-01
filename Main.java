
/**
 * @author Celeste Burel
 * Date: 3/8/2026
 * Purpose: Refactor the Hurricane assignment to use a doubly linked list instead of an array list
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
                    rowData.add(newData);
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

    }

    /**
     * Handles writing the value of the max ACE and its year to the console and an
     * output.txt file
     * 
     * @param rowData The array list of data to use. Called in getMaxAceYear and
     *                used to print.
     */
    private static void writeOutput(ArrayList<HurricaneRowData> rowData) {

        // for printing to console and txt file
        String output = "";

        // search for the year with the maximum ACE in the array
        for (HurricaneRowData data : rowData) {

            // if the current row has the same year as the year found in getMaxAceYear,
            // update the output to that year and exit the loop
            if (data.getYear() == getMaxAceYear(rowData)) {
                output = data.toString();
                break;
            }
        }

        // display the year where the max ACE occurred in the console
        System.out.println(output);

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
     * Takes the hurricane row data and calculates the year that had the maximum ACE
     * value
     * 
     * @param data The array list containing hurricane row data
     * @return The year where the maximum ACE occurred
     */
    private static int getMaxAceYear(ArrayList<HurricaneRowData> data) {

        int maxAce = 0; // tracks the maximum ACE value

        int year = 0; // the year of the maximum ACE value

        // iterate through the hurricane data
        for (int i = 0; i < data.size(); i++) {

            // if the ACE in the current year is larger than the current maximum ACE value,
            // update the maximum value to the current value
            if (data.get(i).getAce() > maxAce) {
                maxAce = data.get(i).getAce();
                year = data.get(i).getYear();
            }
        }

        return year;
    }
}
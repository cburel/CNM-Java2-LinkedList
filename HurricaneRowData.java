public class HurricaneRowData {

    // Year,Ace Index,# Tropical Storms,# Hurricanes Cat. 1-5,# Major Huricanes Cat.
    // 3-5
    private int year, ace, numStorms, cat1To5, cat3To5;

    /**
     * Constructor. Creates a row of data.
     * 
     * @param year      The year of the hurricanes
     * @param ace       The ACE index of the hurricane
     * @param numStorms The number of hurricanes
     * @param cat1To5   The number of hurricanes, category 1 through 5
     * @param cat3To5   The number of major hurricanes, category 3 through 5
     */
    HurricaneRowData(int year, int ace, int numStorms, int cat1To5, int cat3To5) {
        this.year = year;
        this.ace = ace;
        this.numStorms = numStorms;
        this.cat1To5 = cat1To5;
        this.cat3To5 = cat3To5;
    }

    /**
     * Gets the year of a row of data
     * 
     * @return The gotten year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets the ACE index of a row of data
     * 
     * @return The gotten ACE index
     */
    public int getAce() {
        return this.ace;
    }

    /**
     * Pretty-prints the information in a row of data
     */
    @Override
    public String toString() {
        return "The maximum ACE value happened in the year " + year + " at a value of "
                + ace;
    }
}

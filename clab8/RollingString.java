/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    private StringBuilder current_str;

    private int check_length;

    private int currnet_hash;

    private int exp;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        this.current_str = new StringBuilder(s);
        check_length = length;
        currnet_hash = hash();

        exp = 1;

        for (int i = 1; i < check_length ; i++) {
            exp = Math.floorMod((UNIQUECHARS * exp) , PRIMEBASE);
        }
        /* FIX ME */
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        char delete_char = current_str.charAt(0);
        current_str.append(c);
        current_str.delete(0,1);
        currnet_hash = Math.floorMod(currnet_hash + PRIMEBASE - Math.floorMod(exp * delete_char, PRIMEBASE),PRIMEBASE);
        currnet_hash = Math.floorMod(currnet_hash * UNIQUECHARS + (int) c,PRIMEBASE);
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        /* FIX ME */
        return current_str.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return check_length;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        RollingString other = (RollingString) o;
        if (current_str.equals(other.current_str) &&
                check_length == other.check_length && currnet_hash == other.currnet_hash) {
            return true;
        }
        return false;
    }

    /*initialize hashcode*
     */

    public int hash() {
        int h = 0;
        for (int i=0 ; i<check_length ; i++) {
            h = Math.floorMod((UNIQUECHARS * h + (int)current_str.charAt(i)),PRIMEBASE);
        }
        return h;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        /* FIX ME */
        return currnet_hash;
    }
}

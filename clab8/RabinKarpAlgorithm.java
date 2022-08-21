public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        if(pattern.length() > input.length()) {
            return -1;
        }

        RollingString rs_input = new RollingString(input.substring(0,pattern.length()),pattern.length());
        RollingString rs_pattern = new RollingString(pattern,pattern.length());

        for (int i = 0; i < input.length()-pattern.length() + 1;i++) {
            if (rs_input.hashCode() == rs_pattern.hashCode()) {
                if (rs_input.toString().equals(rs_pattern.toString())) {
                    return i;
                }
            }
            if (i+ rs_pattern.length()<input.length()) {
                rs_input.addChar(input.charAt(i+pattern.length()));
            }
        }
        return -1;
    }

}

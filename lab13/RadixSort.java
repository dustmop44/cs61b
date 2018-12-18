/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxlength = 0;
        String[] returnarray = new String[asciis.length];
        System.arraycopy(asciis, 0, returnarray, 0, asciis.length);
        for (int i = 0; i < asciis.length; i++) {
            maxlength = maxlength > asciis[i].length() ? maxlength : asciis[i].length();
        }
        for (int i = 0; i < maxlength; i++) {
            sortHelperLSD(returnarray, i);
        }
        return returnarray;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[257];
        String[] copy = new String[asciis.length];
        System.arraycopy(asciis, 0, copy, 0, asciis.length);
        for (String i : asciis) {
            if (i.length() - 1 < index) {
                counts[0] = 1;
            } else {
                counts[((int) i.charAt(i.length() - 1 - index)) + 1]++;
            }
        }
        int k = 0;
        for (int i = 0; i < counts.length; i++) {
            int m = k + counts[i];
            counts[i] = k;
            k = m;
        }
        for (String i : copy) {
            if (i.length() - 1 < index) {
                asciis[counts[0]] = i;
                counts[0]++;
            } else {
                asciis[counts[((int) i.charAt(i.length() - 1 - index)) + 1]] = i;
                counts[(int) i.charAt(i.length() - 1 - index)]++;
            }
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        int k = 0;
        for (int i = 0; i < 256; i++) {
            if (k == 8) {
                System.out.println((char) i + " ");
                k = 0;
            }
            System.out.print((char) i + " ");
            k++;
        }
    }
}

/**
 * This class is a tester class for demonstrating the fairness of the WinnerPicker class. The
 * WinnerPicker will be called for a number of times and total times of being picked for each
 * people will be kept and printed to see whether the random winner picker is truly random.
 */
public class WinnerPickerTester {
    private static int repeatTimes = 1000; // Number of times the WinnerPicker is called
    private static int[] counts; // Number of times each person is picked.
    private static String[] candidates; // List of participants

    /**
     * This class starts the test
     * @param args not used here
     */
    public static void main(String[] args) {
        WinnerPicker test = new WinnerPicker();
        test.readData();
        candidates = test.getList();
        counts = new int[candidates.length];
        test.setTestMode(true);
        for (int i = 0; i < repeatTimes; i++) {
            test.randomPicker();
            for (int j = 0; j < candidates.length; j++) {
                if (candidates[j].equals(test.getWinner())) {
                    counts[j] = counts[j] + 1;
                }
            }
        }
        resultPrinter();

    }

    /**
     * This class print out the result of the test
     */
    public static void resultPrinter() {
        for (int i = 0; i < counts.length; i++) {
            System.out.print(candidates[i] + ": ");
            System.out.println(counts[i] + " times picked by the system");
        }
    }
}

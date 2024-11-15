public class WinnerPickerTester {
    private static int repeatTimes = 1000;
    private static int[] counts;
    private static String[] candidates;

    public static void main(String[] args) {
        WinnerPicker test = new WinnerPicker();
        test.readData();
        String[] candidates = test.getList();
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
        graphPrinter();

    }
    public static void graphPrinter(){
        for (int i = 0; i < counts.length; i++) {
            System.out.println(counts[i]);
        }
    }
}

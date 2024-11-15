import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * This class picks a random winner from a list of participants. To imitate random picker similar
 * to the website, a number of times of random number generation will be conducted to pick the
 * winner.
 */
public class WinnerPicker {
    private String[] list = null; // Store the names of the participants, but in list form
    private String contestors = ""; // Store the names of the participants, but in String form
    private int times = 100; // Times of generation of random winner
    private String winner = ""; // The winner name
    private boolean testMode = false; // Indicate whether it is the test mode

    /**
     * This method returns the status of program (whether it is in test or not)
     *
     * @return status of program
     */
    public boolean getTestMode() {
        return this.testMode;
    }

    /**
     * This method sets the status of program (whether it is in test or not)
     *
     * @param testMode status of program user wishes to set.
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    /**
     * This method returns the name of the winner
     *
     * @return the name of the winner
     */
    public String getWinner() {
        return this.winner;
    }

    /**
     * This method returns the list of the participants
     *
     * @return list of participants
     */
    public String[] getList() {
        return this.list;
    }

    /**
     * This method read in the data in the Names.csv and get the list of the participants
     */
    public void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Names.csv"))) {
            contestors = reader.readLine();
            list = contestors.split(",");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    /**
     * This method conducts the generation of random numbers for many times and call the
     * resultPrinter method to print out the result on time.
     */
    public void randomPicker() {
        Random rand = new Random();
        for (int i = 0; i < times; i++) {
            int luckyIndex = rand.nextInt(list.length);
            if (!testMode) {
                resultPrinter(luckyIndex);
            }
            winner = list[luckyIndex];
            if (i != times - 1) {
                clearConsole();
            }
        }
        if (!testMode) {
            System.out.println("Winner is " + winner);
        }
    }

    /**
     * This method prints out the result of each pick
     *
     * @param luckyIndex The random number generated (represented the winner in this generation)
     */
    public void resultPrinter(int luckyIndex) {
        for (int i = 0; i < list.length; i++) {
            if (i != luckyIndex) {
                System.out.print("    " + list[i] + "     \n");
            } else {
                System.out.print(" -->" + list[i] + "<--  \n");
            }
        }
        try {
            Thread.sleep(10); // 10 milliseconds = 0.01 second
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted: " + e.getMessage());
        }

    }

    /**
     * This method clears a console by printing an escape sequence (ESC is 33 in octal, 0x1b in hexadecimal)
     * By doing this, the terminal output is more smooth.
     *
     * This behaviour is supported by all terminals (JetBrains' too)
     */
    public static void clearConsole() {
        System.out.print("\033c");
    }

    /**
     * This method
     * @param picker
     */
    public void runPicker(WinnerPicker picker) {
        picker.readData();
        picker.randomPicker();
    }

    public static void main(String[] args) {
        WinnerPicker picker = new WinnerPicker();
        picker.runPicker(picker);
    }
}

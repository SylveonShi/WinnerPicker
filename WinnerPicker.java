import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WinnerPicker {
    private String[] list = null;
    private String contestors = "";
    private int times = 100;
    private String winner = "";
    private boolean testMode = false;

    public boolean getTestMode() {
        return this.testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public String getWinner() {
        return this.winner;
    }

    public String[] getList() {
        return this.list;
    }

    public void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Names.csv"))) {
            contestors = reader.readLine();
            list = contestors.split(",");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

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

    public void resultPrinter(int luckyIndex) {
        for (int i = 0; i < list.length; i++) {
            if (i != luckyIndex) {
                System.out.print("    " + list[i] + "     \n");
            } else {
                System.out.print(" -->" + list[i] + "<--  \n");
            }
        }
        try {
            Thread.sleep(10); // 3000 milliseconds = 3 seconds
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted: " + e.getMessage());
        }

    }

    public void runPicker(WinnerPicker picker) {
        picker.readData();
        picker.randomPicker();
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WinnerPicker picker = new WinnerPicker();
        picker.runPicker(picker);
    }
}
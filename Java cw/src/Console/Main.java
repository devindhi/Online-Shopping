package Console;

public class Main {
    public static void main(String[] args) {
        WestminsterShoppingManager sys = new WestminsterShoppingManager (0);
        boolean exit = false;
        while (!exit) {
            exit = sys.runMenu();
        }
    }
}
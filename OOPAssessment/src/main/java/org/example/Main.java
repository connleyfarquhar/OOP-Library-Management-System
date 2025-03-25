package org.example;

public class Main {
    public static void main(String[] args) {
        // Creates a new Inventory Object.
        Inventory inventory = new Inventory();

        // Creates a new Menu Object.
        Menu menu = new Menu();

        // Creates a new Controller that coordinates with Inventory and Menu.
        Controller controller = new Controller(inventory, menu);

        // Connects the controller to the Menu in order for both to function as expected.
        menu.setController(controller);

        // Call the Menu Method from Menu.java, launches user Navigation on program start.
        menu.userMenu();
    }
}

package org.example;
import java.util.Scanner;

public class Menu {
    // Creating a scanner to retrieve user input and use it functionally within the Library System.
    private final Scanner scan = new Scanner(System.in);

    // Creating a controller to make it functional with the Menu.
    private Controller controller;

    // Method to set the controller object
    public void setController(Controller controller) {
        this.controller = controller;
    }

    // Method to display a message to the user.
    public void userMessage(String message) {
        System.out.println(message);
    }

    // userMenu Method works in the same way as the userNavigation Method in another class, Maintains the navigation of choices for the
    // End User to use in the Library System.
    public void userMenu() {
        while (true) {
            userMessage("Enter a number relating to an option:");
            userMessage("#1: Add an item into the system");
            userMessage("#2: Edit an item in the system");
            userMessage("#3: View all Items in the system");
            userMessage("#4: Search for Item via item ID");
            userMessage("#5: Remove Item via item ID");
            userMessage("#6: Calculate Total Inventory Cost");
            userMessage("#7: Calculate Insurance Cost");
            userMessage("#8: Exit System");
            String userChoice = scan.nextLine();

            // Check if the controller is not null before attempting to call the userNavigation method with the user's choice
            // Without it, the userNavigation will run in a loop and not function as expected.
            if (controller != null) {
                controller.userNavigation(userChoice);
            }
        }
    }

    // Retrieves user input and outputs said input to continue on in the Library System.
    public String retrieveuserInput(String userPrompt) {
        userMessage(userPrompt);
        return scan.nextLine();
    }
}

package org.example;
import java.util.Optional;

public class Controller {
    private final Inventory inventory;
    private final Menu view;

    // Controller Constructor.
    public Controller(Inventory inventory, Menu view) {
        this.inventory = inventory;
        this.view = view;
    }

    // Validates and parses an integer from the input. If invalid, prompts the user to try again.
    private Integer validateInteger(String input, String context) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException num) {
            return validateInteger(view.retrieveuserInput("Invalid " + context + ". Try again:"), context);
        }
    }

    // Validates and parses a float from the input. If invalid, prompts the user to try again.
    private Float validateFloat(String input, String context) {
        try {
            float cost = Float.parseFloat(input);
            if (cost < 1) {
                view.userMessage("Error, Item Cost must be 1 or more.");
                return validateFloat(view.retrieveuserInput("Enter cost again:"), context);
            }
            return cost;

        } catch (NumberFormatException num) {
            return validateFloat(view.retrieveuserInput("Invalid " + context + ". Try again:"), context);
        }
    }

    // Validates if the string input is not empty. If empty, prompts the user to try again.
    private String validateString(String input, String context) {
        return input.isEmpty() ? validateString(view.retrieveuserInput("Invalid " + context + ". Try again:"), context) : input;
    }

    // Print Inventory method, used to print all Inventory items from the Library System Inventory.
    public void printInventory() {
        if (inventory.getItemCount() == 0) {
            view.userMessage("Inventory is empty.");
        } else {
            view.userMessage("Inventory:");
            for (int i = 0; i < inventory.getItemCount(); i++) {
                view.userMessage(inventory.getItemByIndex(i).toString());
            }
        }
    }

    // User Navigation Menu, handled by a switch statement
    public void userNavigation(String choice) {
        switch (validateInteger(choice, "User Navigation Menu Logic")) {
            case 1:
                addItem();
                break;
            case 2:
                editItem();
                break;
            case 3:
                printInventory();
                break;
            case 4:
                viewItemByID();
                break;
            case 5:
                removeItemByID();
                break;
            case 6:
                view.userMessage("Total Inventory Cost: £" + inventory.calculateTotalCost());
                break;
            case 7:
                view.userMessage("Insurance Cost: £" + inventory.calculateInsuranceCost());
                break;
            case 8:
                System.exit(0);
                break;
            default:
                userNavigation(view.retrieveuserInput("You Provided an Invalid Option, Try Again:"));
                break;
        }
    }

    // Add Item method, used to add an item to the Library System.
    private void addItem() {
        String itemTypeChoice = validateString(
                view.retrieveuserInput("Choose item type (Book/Journal/Video):"),
                "Item Type"
        ).toLowerCase();

        // Validates user input to ensure only Book, Journal or Video is submitted to the Library System.
        // Validation Implementation created after Failed Test Case.
        if (!itemTypeChoice.equals("book") &&
                !itemTypeChoice.equals("journal") &&
                !itemTypeChoice.equals("video")) {
            view.userMessage("Error, Invalid item type. Choose a Book, Journal, or Video.");
            addItem();
            return;
        }

        Integer id = validateInteger(view.retrieveuserInput("Enter ID:"), "Item ID");
        String title = validateString(view.retrieveuserInput("Enter title:"), "Item title");
        Float cost = validateFloat(view.retrieveuserInput("Enter cost:"), "Item cost");
        String location = validateString(view.retrieveuserInput("Enter location:"), "Item location");

        // Switch statement used within the User Navigation Menu, ensures that the user can only choose a book, journal or video.
        // If said option is picked it will run through the menu asking for the user to input its unique attributes, else Error.
        switch (itemTypeChoice) {
            case "book":
                addBookItem(id, title, cost, location);
                break;
            case "journal":
                addJournalItem(id, title, cost, location);
                break;
            case "video":
                addVideoItem(id, title, cost, location);
                break;
        }
    }

    // Method to add a Book item
    private void addBookItem(Integer id, String title, Float cost, String location) {
        String author = validateString(view.retrieveuserInput("Enter author:"), "Book author");
        String publisher = validateString(view.retrieveuserInput("Enter publisher:"), "Book publisher");
        Integer pages = validateInteger(view.retrieveuserInput("Enter number of pages:"), "Book pages");

        Book bookItem = new Book(author, pages, publisher, id, false, title, "Book", cost, location);
        inventory.addItem(bookItem);
        view.userMessage("Book added successfully.");
    }

    // Method to add a Journal item
    private void addJournalItem(Integer id, String title, Float cost, String location) {
        String publisher = validateString(view.retrieveuserInput("Enter publisher:"), "Journal publisher");
        Integer issueNumber = validateInteger(view.retrieveuserInput("Enter issue number:"), "Journal issue number");
        String subjectArea = validateString(view.retrieveuserInput("Enter subject area:"), "Journal subject area");
        Integer pages = validateInteger(view.retrieveuserInput("Enter number of pages:"), "Journal pages");

        Journal journalItem = new Journal(issueNumber, publisher, pages, subjectArea, id, false, title, "Journal", cost, location);
        inventory.addItem(journalItem);
        view.userMessage("Journal added successfully.");
    }

    // Method to add a Video item
    private void addVideoItem(Integer id, String title, Float cost, String location) {
        Float duration = validateFloat(view.retrieveuserInput("Add duration:"), "Video duration");
        String format = validateString(view.retrieveuserInput("Add format:"), "Video format");

        // Validating the video input to ensure end user can only input a CD or DVD option.
        if (!(format.equalsIgnoreCase("CD") || format.equalsIgnoreCase("DVD"))) {
            view.userMessage("Invalid format. Choose CD or DVD.");
            addVideoItem(id, title, cost, location);
            return;
        }

        Video videoItem = new Video(format, duration, id, false, title, "Video", cost, location);
        inventory.addItem(videoItem);
        view.userMessage("Video added successfully.");
    }

    // Method to edit an Item
    private void editItem() {
        Integer id = validateInteger(view.retrieveuserInput("Enter item ID to edit:"), "Item ID");
        Optional<LendItem> optionalItem = inventory.findItemById(id);

        if (optionalItem.isEmpty()) {
            view.userMessage("Specified Item not found, check your input and Try Again.");
            return;
        }
        LendItem item = optionalItem.get();

        // If statement makes use of Instanceof to ensure that specific instances are receiving the correct output such as the possible choice of book, journal or video in this instance.
        if (item instanceof Book) {
            editBook((Book) item);
        } else if (item instanceof Journal) {
            editJournal((Journal) item);
        } else if (item instanceof Video) {
            editVideo((Video) item);
        }
    }

    // Edits an existing Book that is in the Library System by updating its unique attributes through user input.
    private void editBook(Book book) {
        book.setTitle(validateString(view.retrieveuserInput("Enter new title:"), "Item title"));
        book.setCost(validateFloat(view.retrieveuserInput("Enter new cost:"), "Item cost"));
        book.setLocation(validateString(view.retrieveuserInput("Enter new location:"), "Item location"));
        book.setAuthor(validateString(view.retrieveuserInput("Enter new author:"), "A Books author"));
        book.setPublisher(validateString(view.retrieveuserInput("Enter new publisher:"), "A Books publisher"));
        book.setNumberOfPages(validateInteger(view.retrieveuserInput("Enter new number of pages:"), "Number of Book Pages"));
        view.userMessage("Book updated.");
    }

    // Edits an existing Journal that is in the Library System by updating its unique attributes through user input.
    private void editJournal(Journal journal) {
        journal.setTitle(validateString(view.retrieveuserInput("Enter new title:"), "Item title"));
        journal.setCost(validateFloat(view.retrieveuserInput("Enter new cost:"), "Item cost"));
        journal.setLocation(validateString(view.retrieveuserInput("Enter new location:"), "Item location"));
        journal.setPublisher(validateString(view.retrieveuserInput("Enter new publisher:"), "A Journals publisher"));
        journal.setIssueNo(validateInteger(view.retrieveuserInput("Enter new issue number:"), "A Journals issue number"));
        journal.setSubject(validateString(view.retrieveuserInput("Enter new subject area:"), "A Journals subject area"));
        journal.setNumberOfPages(validateInteger(view.retrieveuserInput("Enter new number of pages:"), "Number of Journal Pages"));
        view.userMessage("Journal updated.");
    }

    // Edits an existing Video that is in the Library System by updating its unique attributes through user input.
    private void editVideo(Video video) {
        video.setTitle(validateString(view.retrieveuserInput("Enter new title:"), "Item title"));
        video.setCost(validateFloat(view.retrieveuserInput("Enter new cost:"), "Item cost"));
        video.setLocation(validateString(view.retrieveuserInput("Enter new location:"), "Item location"));
        video.setDuration(validateFloat(view.retrieveuserInput("Enter new duration:"), "Video duration amount"));

        // Validate Input to ensure only a CD or DVD option is submitted to the Library System.
        String format = validateString(view.retrieveuserInput("Enter new format (CD/DVD):"), "Video format");
        if (!format.equalsIgnoreCase("CD") && !format.equalsIgnoreCase("DVD")) {
            view.userMessage("Invalid format. Please choose CD or DVD.");
            return;
        }
        video.setFormat(format);
        view.userMessage("Video updated.");
    }

    // View Item method, used to view Items by unique ID in the Library System.
    private void viewItemByID() {
        Integer id = validateInteger(view.retrieveuserInput("Enter item ID:"), "Item ID");
        Optional<LendItem> item = inventory.findItemById(id);
        view.userMessage(item.isEmpty() ? "Specified Item not found, check your input and Try Again." : item.get().toString());
    }

    // Remove Item method, used to remove Items by unique ID in the Library System.
    private void removeItemByID() {
        Integer id = validateInteger(view.retrieveuserInput("Enter item ID to remove:"), "Item ID");
        if (inventory.removeItemById(id)) {
            view.userMessage("Item removed from the Inventory.");
        } else {
            view.userMessage("Specified Item not found, check your input and Try Again.");
        }
    }
}
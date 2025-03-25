package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {

    // Create a new Array for a List to store Inventory items for the duration of the program running.
    private final List<LendItem> items;
    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Add Item to Inventory, additionally will check if the ItemID that is attempted to be added is already in the system,
    // if its found it is blocked from doing so.
    public boolean addItem(LendItem item) {
        if (item != null) {
            if (item.getID() < 1) {
                System.out.println("Error, An ID Number must be 1 or greater.");
                return false;
            }
            if (findItemById(item.getID()).isPresent()) {
                System.out.println("Error, The Item with ID " + item.getID() + " already exists in the Library System, Try again with an alternative ID Number.");
                return false;
            }
            items.add(item);
            return true;
        }
        return false;
    }

    // Find an item by its ID in the Library Inventory.
    public Optional<LendItem> findItemById(Integer id) {
        return items.stream()
                .filter(item -> item.getID().equals(id))
                .findFirst();
    }

    // Remove an item by its ID from the Library Inventory.
    public boolean removeItemById(Integer id) {
        return items.removeIf(item -> item.getID().equals(id));
    }

    // Calculate the total cost of all items in the Library Inventory.
    public float calculateTotalCost() {
        return (float) items.stream()
                .mapToDouble(item -> item.getCost())
                .sum();
    }

    // Calculate the insurance cost.
    public float calculateInsuranceCost() {
        float insuranceCost = (float) items.stream()
                .mapToDouble(item -> item.getCost() / 2)
                .sum();
        return Math.min(insuranceCost, 400);
    }

    // Get the total number of items in the Library System.
    public int getItemCount() {
        return items.size();
    }

    // Get an item by its index, or null if the index is out of bounds.
    public LendItem getItemByIndex(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }
}
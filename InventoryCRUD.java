// SCTP-04
import java.util.ArrayList;
import java.util.Scanner;

// Abstract class representing an item in the inventory
abstract class Item {
    // Product of service ID
    private int id;
    // Item name
    private String name;
    // Price of item
    private double price;

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter for Id
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Add setter methods for name
    public void setName(String name) {
        this.name = name;
    }

    // Add setter methods for price
    public void setPrice(double price) {
        this.price = price;
    }

    // Abstract method to be implemented by subclasses 
    // for specific details
    public abstract String getDetails();
}

// Class representing a product item
class Product extends Item {
    private String category;

    public Product(int id, String name, double price, String category) {
        super(id, name, price);  // Pass values to the superclass constructor
        this.category = category;
    }

    // Modify setter methods to use superclass setter methods
    public void setName(String name) {
        super.setName(name);
    }

    // 
    public void setPrice(double price) {
        super.setPrice(price);
    }

    //
    public void setCategory(String category) {
        this.category = category;
    }

    // 
    @Override
    public String getDetails() {
        return "Product - Category: " + category;
    }
}

// Class representing a service item
class Service extends Item {
    private String description;

    public Service(int id, String name, double price, String description) {
        super(id, name, price);  // Pass values to the superclass constructor
        this.description = description;
    }

    // Modify setter methods to use superclass setter methods
    public void setName(String name) {
        super.setName(name);
    }

    public void setPrice(double price) {
        super.setPrice(price);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDetails() {
        return "Service - Description: " + description;
    }
}

// Main class to handle the CRUD operations
public class InventoryCRUD {
    private ArrayList<Item> inventory = new ArrayList<>();
    private int nextId = 1;

    // Create operation
    public void createItem(String type, String name, double price, String additionalInfo) {
        if (type.equalsIgnoreCase("product")) {
            Product product = new Product(nextId++, name, price, additionalInfo);
            inventory.add(product);
            System.out.println("Product created successfully with ID: " + product.getId());
        } else if (type.equalsIgnoreCase("service")) {
            Service service = new Service(nextId++, name, price, additionalInfo);
            inventory.add(service);
            System.out.println("Service created successfully with ID: " + service.getId());
        } else {
            System.out.println("Invalid item type.");
        }
    }

    // Read operation
    public void readItems() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Inventory List:");
        for (Item item : inventory) {
            System.out.println("ID: " + item.getId() + ", Name: " + item.getName() + ", Price: " + item.getPrice() + ", " + item.getDetails());
        }
    }

    // Update operation
    public void updateItem(int id, String newName, double newPrice, String newAdditionalInfo) {
        for (Item item : inventory) {
            if (item.getId() == id) {
                if (item instanceof Product) {
                    ((Product) item).setName(newName);
                    ((Product) item).setPrice(newPrice);
                    ((Product) item).setCategory(newAdditionalInfo);
                } else if (item instanceof Service) {
                    ((Service) item).setName(newName);
                    ((Service) item).setPrice(newPrice);
                    ((Service) item).setDescription(newAdditionalInfo);
                }
                System.out.println("Item with ID " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Item with ID " + id + " not found.");
    }

    // Delete operation
    public void deleteItem(int id) {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getId() == id) {
                inventory.remove(i);
                System.out.println("Item with ID " + id + " deleted successfully.");
                return;
            }
        }
        System.out.println("Item with ID " + id + " not found.");
    }

    public static void main(String[] args) {
        InventoryCRUD crud = new InventoryCRUD();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create an item (product or service)");
            System.out.println("2. Read all items in inventory");
            System.out.println("3. Update an item");
            System.out.println("4. Delete an item");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Key-in product or service for the type of item:");
                    String type = scanner.nextLine();
                    System.out.println("Enter the name of the item:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the price of the item:");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.println("Enter additional information (category for product, description for service):");
                    String additionalInfo = scanner.nextLine();
                    crud.createItem(type, name, price, additionalInfo);
                    break;
                case 2:
                    crud.readItems();
                    break;
                case 3:
                    System.out.println("Enter the ID of the item to update:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Enter the new name of the item:");
                    //String tempString = scanner.nextLine();
                    String newName = scanner.nextLine();
                    System.out.println("Enter the new price of the item:");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.println("Enter the new additional information:");
                    String newAdditionalInfo = scanner.nextLine();
                    crud.updateItem(updateId, newName, newPrice, newAdditionalInfo);
                    break;
                case 4:
                    System.out.println("Enter the ID of the item to delete or 0 to cancel:");
                    int deleteId = scanner.nextInt();
                    if (deleteId == 0){
                        System.out.println("Cancelled deleting an item.");
                    } else {
                        crud.deleteItem(deleteId);
                    }
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
import java.util.ArrayList;
import java.util.Scanner;

abstract class Item {
    private int id;            
    private String title;
    private boolean issued;

    public Item(int id, String title) {
        this.id = id;
        this.title = title;
        this.issued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isIssued() { return issued; }
    public void setIssued(boolean issued) { this.issued = issued; }

    
    public abstract String getDetails();
}


class Book extends Item {
    private String author;

    public Book(int id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    @Override
    public String getDetails() {
        return "Book   | ID: " + getId() + " | " + getTitle() + " by " + author
                + " | " + (isIssued() ? "Issued" : "Available");
    }
}


class Magazine extends Item {
    private int issueNumber;

    public Magazine(int id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getDetails() {
        return "Magazine | ID: " + getId() + " | " + getTitle() + " #" + issueNumber
                + " | " + (isIssued() ? "Issued" : "Available");
    }
}


class Library {
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item) { items.add(item); }

    public void showAll() {
        if (items.isEmpty()) {
            System.out.println("No items in library.");
            return;
        }
        
        for (Item item : items) {
            System.out.println(item.getDetails());
        }
    }

    public Item findById(int id) {
        for (Item item : items) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    public void issueItem(int id) {
        Item item = findById(id);
        if (item == null) {
            System.out.println("Item not found.");
        } else if (item.isIssued()) {
            System.out.println("Already issued.");
        } else {
            item.setIssued(true);
            System.out.println("Issued: " + item.getTitle());
        }
    }

    public void returnItem(int id) {
        Item item = findById(id);
        if (item == null) {
            System.out.println("Item not found.");
        } else if (!item.isIssued()) {
            System.out.println("This item was not issued.");
        } else {
            item.setIssued(false);
            System.out.println("Returned: " + item.getTitle());
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.addItem(new Book(1, "Java Basics", "James Gosling"));
        library.addItem(new Book(2, "Clean Code", "Robert Martin"));
        library.addItem(new Magazine(3, "Tech Today", 42));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Show all items");
            System.out.println("2. Issue item");
            System.out.println("3. Return item");
            System.out.println("4. Add book");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    library.showAll();
                    break;
                case 2:
                    System.out.print("Enter item ID: ");
                    library.issueItem(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter item ID: ");
                    library.returnItem(sc.nextInt());
                    break;
                case 4:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addItem(new Book(id, title, author));
                    System.out.println("Book added.");
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}

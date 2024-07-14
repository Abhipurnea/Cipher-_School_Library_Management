import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Borrowed: " + isBorrowed;
    }
}

public class LibraryManagementSystem {
    private static Map<String, Book> books = new HashMap<>();

    public static void addBook(String title, String author) {
        if (books.containsKey(title)) {
            System.out.println("Book " + title + " already exists.");
        } else {
            books.put(title, new Book(title, author));
            System.out.println("Book " + title + " added successfully.");
        }
    }

    public static void borrowBook(String title) {
        Book book = books.get(title);
        if (book != null) {
            if (!book.isBorrowed()) {
                book.borrow();
                System.out.println("Book " + title + " borrowed successfully.");
            } else {
                System.out.println("Book " + title + " is already borrowed.");
            }
        } else {
            System.out.println("Book " + title + " not found.");
        }
    }

    public static void returnBook(String title) {
        Book book = books.get(title);
        if (book != null) {
            if (book.isBorrowed()) {
                book.returnBook();
                System.out.println("Book " + title + " returned successfully.");
            } else {
                System.out.println("Book " + title + " was not borrowed.");
            }
        } else {
            System.out.println("Book " + title + " not found.");
        }
    }

    public static void viewAvailableBooks() {
        boolean found = false;
        for (Book book : books.values()) {
            if (!book.isBorrowed()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Available Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title to add: ");
                        String titleToAdd = scanner.nextLine();
                        System.out.print("Enter author name: ");
                        String authorToAdd = scanner.nextLine();
                        addBook(titleToAdd, authorToAdd);
                        break;
                    case 2:
                        System.out.print("Enter book title to borrow: ");
                        String titleToBorrow = scanner.nextLine();
                        borrowBook(titleToBorrow);
                        break;
                    case 3:
                        System.out.print("Enter book title to return: ");
                        String titleToReturn = scanner.nextLine();
                        returnBook(titleToReturn);
                        break;
                    case 4:
                        viewAvailableBooks();
                        break;
                    case 5:
                        System.out.println("Exiting the system. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid input
            }
        }
    }
}

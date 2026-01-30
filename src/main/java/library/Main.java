package library;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Register Member");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Statistics");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Year: ");
                    int year = sc.nextInt();

                    library.addBook(new Book(isbn, title, author, year));
                }

                case 2 -> library.viewBooks();

                case 3 -> {
                    System.out.print("Member ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    library.registerMember(new Member(id, name));
                }

                case 4 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Member ID: ");
                    String id = sc.nextLine();
                    library.borrowBook(isbn, id);
                }

                case 5 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    library.returnBook(isbn);
                }

                case 6 -> library.statistics();

                case 7 -> {
                    System.out.println("Thank you!");
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

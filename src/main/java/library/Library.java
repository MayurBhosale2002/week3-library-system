package library;

import java.util.*;

public class Library {

    private List<Book> books;
    private List<Member> members;
    private FileHandler fileHandler;

    public Library() {
        fileHandler = new FileHandler();
        books = fileHandler.loadBooks();
        members = fileHandler.loadMembers();
    }

    public void addBook(Book book) {
        books.add(book);
        fileHandler.saveBooks(books);
        System.out.println("Book added successfully.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.forEach(System.out::println);
    }

    public Book findBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    public Member findMember(String id) {
        for (Member m : members) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    public void registerMember(Member member) {
        members.add(member);
        fileHandler.saveMembers(members);
        System.out.println("Member registered successfully.");
    }

    public void borrowBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid book or member.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book already borrowed.");
            return;
        }

        book.borrow(memberId);
        member.borrowBook(isbn);

        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);

        System.out.println("Book borrowed successfully.");
        System.out.println("Due Date: " + book.getDueDate());
    }

    public void returnBook(String isbn) {
        Book book = findBook(isbn);

        if (book == null || book.isAvailable()) {
            System.out.println("Invalid return.");
            return;
        }

        Member member = findMember(book.getBorrowedBy());
        if (member != null) member.returnBook(isbn);

        if (book.isOverdue()) {
            long days = java.time.temporal.ChronoUnit.DAYS.between(
                    book.getDueDate(),
                    java.time.LocalDate.now()
            );
            System.out.println("Overdue fine: ₹" + (days * 5));
        }

        book.returnBook();
        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);

        System.out.println("Book returned successfully.");
    }

    public void statistics() {
        long available = books.stream().filter(Book::isAvailable).count();

        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + available);
        System.out.println("Borrowed Books: " + (books.size() - available));
        System.out.println("Members: " + members.size());
    }
}

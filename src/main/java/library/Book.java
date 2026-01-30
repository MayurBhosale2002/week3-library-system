package library;

import java.time.LocalDate;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private int year;
    private boolean available;
    private String borrowedBy;
    private LocalDate dueDate;

    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void borrow(String memberId) {
        available = false;
        borrowedBy = memberId;
        dueDate = LocalDate.now().plusDays(14);
    }

    public void returnBook() {
        available = true;
        borrowedBy = null;
        dueDate = null;
    }

    public boolean isOverdue() {
        return dueDate != null && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
                " | Title: " + title +
                " | Author: " + author +
                " | Year: " + year +
                " | " + (available ? "Available"
                : "Borrowed by: " + borrowedBy + " | Due: " + dueDate);
    }
}

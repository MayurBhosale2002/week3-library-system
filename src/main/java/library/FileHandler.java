package library;

import java.io.*;
import java.util.*;

public class FileHandler {

    private final String BOOK_FILE = "data/books.txt";
    private final String MEMBER_FILE = "data/members.txt";

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BOOK_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Book book = new Book(
                        data[0],
                        data[1],
                        data[2],
                        Integer.parseInt(data[3])
                );
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("Books file not found. Starting fresh.");
        }

        return books;
    }

    public void saveBooks(List<Book> books) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOK_FILE))) {
            for (Book b : books) {
                pw.println(
                        b.getIsbn() + "," +
                        b.getTitle() + "," +
                        b.getAuthor() + "," +
                        b.getYear()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    public List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                members.add(new Member(data[0], data[1]));
            }
        } catch (Exception e) {
            System.out.println("Members file not found. Starting fresh.");
        }

        return members;
    }

    public void saveMembers(List<Member> members) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEMBER_FILE))) {
            for (Member m : members) {
                pw.println(m.getId() + "," + m.getName());
            }
        } catch (IOException e) {
            System.out.println("Error saving members.");
        }
    }
}

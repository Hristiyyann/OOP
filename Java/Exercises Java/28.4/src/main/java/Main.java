import java.util.ArrayList;
import java.util.Collections;

public class Main
{
    public static void main(String[] args)
    {
        Book book1 = new Book("Hamaida", 1985);
        Book book3 = new Book("TUES 2", 2005);
        Book book2 = new Book("Ocelqvane", 1956);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        Collections.sort(books);

        System.out.println(book1.toString());
        System.out.println(book2.toString());
        System.out.println(book3.toString());

        for(Book b : books)
        {
            System.out.println(b.toString());
        }
    }
}

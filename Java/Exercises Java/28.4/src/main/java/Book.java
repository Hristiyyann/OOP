
public class Book
        implements Comparable<Book>
{
    protected String title;
    protected int year;

    public Book(String title, int year)
    {
        this.title = title;
        this.year = year;
    }

    @Override
    public String toString()
    {
        return "Book {" +
                "title='" + title + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public int compareTo(Book o)
    {
        String compareYear = o.title;
        return this.title.compareTo(o.title);
        //return this.year - compareYear;
    }
}

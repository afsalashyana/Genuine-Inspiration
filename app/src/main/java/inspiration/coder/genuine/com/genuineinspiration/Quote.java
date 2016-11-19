package inspiration.coder.genuine.com.genuineinspiration;


public class Quote {
    String quote;
    String author;
    int id;

    public Quote(int id,String quote, String author) {
        this.quote = quote;
        this.author = author;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

package inspiration.coder.genuine.com.genuineinspiration.likedquotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import inspiration.coder.genuine.com.genuineinspiration.R;
import inspiration.coder.genuine.com.genuineinspiration.core.QuoteReaderService;
import inspiration.coder.genuine.com.genuineinspiration.mainquotes.model.Quote;

public class LikedQuotesActivity extends AppCompatActivity {

    private ListView listView;
    private List<Quote> quoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_quotes);
        try {
            initComponents();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() throws FileNotFoundException {
        listView = findViewById(R.id.likedQuotes_list);

        QuoteReaderService readerService = new QuoteReaderService();
        quoteList = readerService.getAllQuotes(this);
        List<Integer> likedQuoteIdList = readerService.getAllLikedQuotes(this);

        List<Quote> likedQuoteList = new ArrayList<>();
        for (Integer id : likedQuoteIdList) {
            likedQuoteList.add(findById(id));
        }

        inflateListView(likedQuoteList);
    }

    private void inflateListView(List<Quote> likedQuoteIdList) {
        ArrayAdapter<Quote> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, likedQuoteIdList);
        listView.setAdapter(adapter);
    }

    private Quote findById(int id) {
        for (Quote quote : quoteList) {
            if (quote.getId() == id) {
                return quote;
            }
        }
        return null;
    }
}

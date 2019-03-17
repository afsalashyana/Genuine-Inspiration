package inspiration.coder.genuine.com.genuineinspiration.mainquotes;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import inspiration.coder.genuine.com.genuineinspiration.R;
import inspiration.coder.genuine.com.genuineinspiration.core.QuoteReaderService;
import inspiration.coder.genuine.com.genuineinspiration.mainquotes.adapters.QuotePagerAdapter;
import inspiration.coder.genuine.com.genuineinspiration.mainquotes.model.Quote;

public class MainActivity extends AppCompatActivity {

    public static final String QUOTE_STORE = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Quote> list = new QuoteReaderService().getAllQuotes(this);

        ViewPager viewPager = findViewById(R.id.viewpager);
        QuotePagerAdapter adapter = new QuotePagerAdapter(list, this);
        viewPager.setAdapter(adapter);
    }

}

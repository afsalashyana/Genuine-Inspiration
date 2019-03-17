package inspiration.coder.genuine.com.genuineinspiration.likedquotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import inspiration.coder.genuine.com.genuineinspiration.R;
import inspiration.coder.genuine.com.genuineinspiration.mainquotes.model.Quote;

public class LikedQuoteListAdapter extends ArrayAdapter<Quote> {
    public LikedQuoteListAdapter(Context context, List<Quote> quoteList) {
        super(context, 0, quoteList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Quote quote = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liked_quote_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.likedQuoteListItem_tfQuote);
        textView.setText(quote.getQuote());

        return convertView;
    }
}

package inspiration.coder.genuine.com.genuineinspiration;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuotePagerAdapter extends PagerAdapter {
    ArrayList<Quote> list;
    Context context;

    public QuotePagerAdapter(ArrayList<Quote> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Quote object = list.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slide_layout, container, false);

        TextView quote = (TextView) layout.findViewById(R.id.quoteHolder);
        TextView author = (TextView) layout.findViewById(R.id.authorHolder);

        quote.setText(object.getQuote());
        author.setText(object.getAuthor());

        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

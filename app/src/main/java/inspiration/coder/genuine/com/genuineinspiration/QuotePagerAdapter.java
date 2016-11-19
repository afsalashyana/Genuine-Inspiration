package inspiration.coder.genuine.com.genuineinspiration;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

        ImageButton btn = (ImageButton)layout.findViewById(R.id.shareButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toShare = object.getQuote() + " by " + object.getAuthor() + " send using Genuine Inspiration";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, toShare);
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });

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

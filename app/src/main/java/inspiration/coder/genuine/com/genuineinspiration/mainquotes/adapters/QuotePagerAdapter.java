package inspiration.coder.genuine.com.genuineinspiration.mainquotes.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import inspiration.coder.genuine.com.genuineinspiration.R;
import inspiration.coder.genuine.com.genuineinspiration.mainquotes.model.Quote;

import static android.content.Context.MODE_APPEND;

public class QuotePagerAdapter extends PagerAdapter {
    private static final String LIKED_QUOTES = "likedQuotes.txt";
    private List<Quote> list;
    private Context context;

    public QuotePagerAdapter(List<Quote> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Quote object = list.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slide_layout, container, false);

        final TextView quote = layout.findViewById(R.id.quoteHolder);
        TextView author = layout.findViewById(R.id.authorHolder);

        quote.setText(object.getQuote());
        author.setText(object.getAuthor());

        ImageButton btn = layout.findViewById(R.id.shareButton);
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

        ImageButton btn1 = layout.findViewById(R.id.likeButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean isAlreadyLiked = false;
                    if (fileExists(LIKED_QUOTES)) {
                        Scanner scanner = new Scanner(context.openFileInput(LIKED_QUOTES));
                        while (scanner.hasNext()) {
                            int id = Integer.parseInt(scanner.nextLine());
                            Log.d("QuotePagerAdapter", "Liked " + id + " where liked quote = " + quote.getId());
                            if (id == object.getId())
                                isAlreadyLiked = true;
                        }
                        if (isAlreadyLiked) {
                            Toast.makeText(context, "This quote is already liked :-)", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    FileOutputStream fout = context.openFileOutput(LIKED_QUOTES, MODE_APPEND);
                    PrintWriter printWriter = new PrintWriter(fout, true);
                    printWriter.println(object.getId());
                    printWriter.close();

                    Toast.makeText(context, "Quote Added to Liked List", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        container.addView(layout);

        return layout;
    }

    private boolean fileExists(String filename) {
        File file = context.getFileStreamPath(filename);
        return file != null && file.exists();
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

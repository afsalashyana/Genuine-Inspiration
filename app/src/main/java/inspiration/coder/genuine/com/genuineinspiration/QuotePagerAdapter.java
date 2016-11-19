package inspiration.coder.genuine.com.genuineinspiration;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_WORLD_READABLE;

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

        final TextView quote = (TextView) layout.findViewById(R.id.quoteHolder);
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

        ImageButton btn1 = (ImageButton)layout.findViewById(R.id.likeButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Boolean isAlreadyLiked = false;
                    Scanner scanner = new Scanner(context.openFileInput("likedQuotes.txt"));
                    while (scanner.hasNext()){
                        int id = Integer.parseInt(scanner.nextLine());
                        Log.d("QuotePagerAdapter","Liked " + id + " where liked quote = " + quote.getId());
                        if(id==object.getId())
                            isAlreadyLiked=true;
                    }
                    if(isAlreadyLiked){
                        Toast.makeText(context,"This quote is already liked :-)", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    FileOutputStream fout = context.openFileOutput("likedQuotes.txt",  MODE_APPEND);
                    PrintWriter printWriter = new PrintWriter(fout, true);
                    printWriter.println(object.getId());
                    printWriter.close();

                    Toast.makeText(context,"Quote Added to Liked List", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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

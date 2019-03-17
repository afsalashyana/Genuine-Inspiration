package inspiration.coder.genuine.com.genuineinspiration.core;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import inspiration.coder.genuine.com.genuineinspiration.mainquotes.model.Quote;

public class QuoteReaderService {
    public static final String QUOTE_STORE = "data.txt";
    private static final String LIKED_QUOTES = "likedQuotes.txt";

    public List<Quote> getAllQuotes(Activity activity) {
        ArrayList<Quote> list = new ArrayList<>();
        AssetManager manager = activity.getAssets();
        try {
            InputStream in = manager.open(QUOTE_STORE);
            Scanner scn = new Scanner(in);
            while (scn.hasNext()) {
                String temp = scn.nextLine();
                int quoteID = Integer.parseInt(temp.split("[|]")[0]);
                String quote = temp.split("[|]")[1];
                String author = temp.split("[|]")[2];
                Log.d("MainActivity", quote + "---by " + author + " at " + quoteID);

                Quote qt = new Quote(quoteID, quote, author);
                list.add(qt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Integer> getAllLikedQuotes(Activity activity) throws FileNotFoundException {
        List<Integer> likedIdList = new ArrayList<>();
        if (fileExists(activity, LIKED_QUOTES)) {
            Scanner scanner = new Scanner(activity.openFileInput(LIKED_QUOTES));
            while (scanner.hasNext()) {
                int id = Integer.parseInt(scanner.nextLine());
                likedIdList.add(id);
            }
        }
        return likedIdList;
    }

    private boolean fileExists(Activity activity, String filename) {
        File file = activity.getFileStreamPath(filename);
        return file != null && file.exists();
    }
}

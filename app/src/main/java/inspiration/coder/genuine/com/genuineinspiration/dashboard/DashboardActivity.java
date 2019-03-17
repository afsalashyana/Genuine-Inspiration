package inspiration.coder.genuine.com.genuineinspiration.dashboard;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import inspiration.coder.genuine.com.genuineinspiration.R;
import inspiration.coder.genuine.com.genuineinspiration.likedquotes.LikedQuotesActivity;
import inspiration.coder.genuine.com.genuineinspiration.mainquotes.MainActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeComponents();
        setSupportActionBar((Toolbar) findViewById(R.id.dashboard_toolbar));
    }

    private void initializeComponents() {
        CardView viewAll = findViewById(R.id.dashboard_btnViewAllQuotes);
        CardView viewLiked = findViewById(R.id.dashboard_btnViewLikedQuotes);

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        viewLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, LikedQuotesActivity.class);
                startActivity(intent);
            }
        });
    }
}

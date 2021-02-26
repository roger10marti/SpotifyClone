package cat.itb.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    TextView songTitleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        songTitleText = findViewById(R.id.songtitle);
        songTitleText.setSelected(true);
    }
}
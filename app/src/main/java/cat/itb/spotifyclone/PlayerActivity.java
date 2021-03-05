package cat.itb.spotifyclone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    ImageView b_play, b_back;
    TextView songTitleText;
    boolean playing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        songTitleText = findViewById(R.id.songtitle);
        songTitleText.setSelected(true);

        b_back = findViewById(R.id.arrdown);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b_play = findViewById(R.id.b_play);
        b_play.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (playing){
                    b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_pause));
                    playing=false;
                }else {
                    b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_play));
                    playing=true;
                }
            }
        });
    }
}
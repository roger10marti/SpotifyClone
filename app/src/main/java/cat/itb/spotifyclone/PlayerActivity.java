package cat.itb.spotifyclone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

import cat.itb.spotifyclone.model.FavouriteSong;

public class PlayerActivity extends AppCompatActivity {

    ImageView b_play, b_back, favImageView;
    TextView songTitleText;
    boolean playing = false;
    boolean favourite = false;
    private DatabaseReference dref;
    FirebaseDatabase firebaseDatabase;
    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dref = firebaseDatabase.getReference("Favourites");

        songTitleText = findViewById(R.id.songtitle);
        favImageView = findViewById(R.id.imageViewCorazon);
        songTitleText.setSelected(true);

        Query q = dref.orderByChild("song").equalTo(songTitleText.getText().toString());

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if (post.exists()) {
                        key = (String) post.child("idFavourite").getValue();
                        favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_action_favorite));
                        favourite = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

        favImageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (!favourite) {
                    favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_action_favorite));
                    favourite = true;
                    FavouriteSong fav = new FavouriteSong();
                    fav.setSong(songTitleText.getText().toString());
                    String key = dref.push().getKey();
                    fav.setIdFavourite(key);
                    fav.setFecha(LocalDate.now().toString());
                    dref.child(key).setValue(fav);

                } else {
                    favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_action_favourite));
                    favourite = false;
                    System.out.println("Key ->>>>>>>>>><"+ key);
                    dref.child(key).removeValue();
                }
            }
        });
    }
}
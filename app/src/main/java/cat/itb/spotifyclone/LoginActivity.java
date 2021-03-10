package cat.itb.spotifyclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button loginbutton;
    private MaterialButton loginGoogle;
    private FirebaseAuth mAuth;
    private TextView signUp;
    private TextInputEditText etPass, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loginbutton = findViewById(R.id.loginbutton);
        signUp = findViewById(R.id.registerText);
        etPass = findViewById(R.id.etpass);
        etEmail = findViewById(R.id.etloginUser);
        loginGoogle = findViewById(R.id.fbutton);

        session();

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etEmail.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty()){
                    mAuth.signInWithEmailAndPassword(etEmail.getText().toString(),etPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("email",etEmail.getText().toString());
                                        startActivity(intent);


                                    } else {
                                        showAlert("Esta cuenta de usuario no existe");
                                    }
                                }

                    });
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void session() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        String email = sp.getString("email",null);

        if (email != null) {
            View l = getWindow().getDecorView().findViewById(R.id.loginContent);
            l.setVisibility(View.INVISIBLE);
            
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("email",etEmail.getText().toString());
            startActivity(intent);
        }
    }

    public void showAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(text);
        builder.setPositiveButton("Aceptar",null);
        AlertDialog al = builder.create();
        al.show();
    }
}
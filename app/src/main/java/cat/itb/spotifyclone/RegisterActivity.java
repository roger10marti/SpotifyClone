package cat.itb.spotifyclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cat.itb.spotifyclone.model.Usuario;

public class RegisterActivity extends AppCompatActivity {

    private Button signUpButton;
    private TextView alreadyHaveAcc;
    private TextInputEditText etPass, etEmail, etRepeatPassword, etUsername;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    static DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etPass = findViewById(R.id.etpass);
        etEmail = findViewById(R.id.etloginUser);
        etRepeatPassword = findViewById(R.id.etrepeatpass);
        etUsername = findViewById(R.id.etusername);

        signUpButton = findViewById(R.id.signupButton);
        alreadyHaveAcc = findViewById(R.id.alreadyAccount);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Usuarios");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etEmail.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty()
                        && !etRepeatPassword.getText().toString().isEmpty() && !etUsername.getText().toString().isEmpty()
                        && etPass.getText().toString().equals(etRepeatPassword.getText().toString())) {

                    mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String key = databaseReference.push().getKey();
                                        Usuario user = new Usuario(key, etEmail.getText().toString(), etUsername.getText().toString());
                                        databaseReference.child(key).setValue(user);

                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                                                .Builder().setDisplayName(etUsername.getText().toString()).build();
                                        mAuth.getCurrentUser().updateProfile(profileUpdates);


                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.putExtra("email", etEmail.getText().toString());
                                        startActivity(intent);
                                    } else {
                                        Exception e = task.getException();
                                        if (e instanceof FirebaseAuthUserCollisionException) {
                                            showAlert("Esta cuenta email ya pertenece a una cuenta");
                                        } else if (e instanceof FirebaseAuthEmailException) {
                                            showAlert("Email con formato incorrecto");
                                        } else if (e instanceof FirebaseAuthWeakPasswordException) {
                                            showAlert("Esta contraseña es denasiado corta");
                                        }
                                    }
                                }
                            });
                }
            }
        });

        alreadyHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(text);
        builder.setPositiveButton("Aceptar", null);
        AlertDialog al = builder.create();
        al.show();
    }
}
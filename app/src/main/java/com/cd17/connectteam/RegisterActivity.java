package com.cd17.connectteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity
{
    private EditText fname;
    private EditText lname;
    private EditText phone;
    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth auth;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_fname = fname.getText().toString();
                String txt_lname = lname.getText().toString();
                String txt_email = email.getText().toString();
                String txt_phone = phone.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_fname) || TextUtils.isEmpty(txt_lname) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_phone) || TextUtils.isEmpty(txt_password))
                    Toast.makeText(RegisterActivity.this, "Empty Credentials..!", Toast.LENGTH_SHORT).show();
                else if(txt_password.length() < 6)
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                else{
                    //registerUser(txt_name, txt_email, txt_password);
                    auth.createUserWithEmailAndPassword(txt_email , txt_password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                userId = auth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("Admins").document(userId);
                                Map<String,Object> admin = new HashMap<>();
                                admin.put("fname",txt_fname);
                                admin.put("lname",txt_lname);
                                admin.put("email",txt_email);
                                admin.put("phone",txt_phone);
                                documentReference.set(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegisterActivity.this, "Registering User", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                startActivity(new Intent(RegisterActivity.this  , MainActivity.class));
                                finish();
                            }
                            else
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

   /* private void registerUser(String name, String email, String password)
    {
    } */
}











package com.cd17.connectteam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileActivity extends AppCompatActivity {

    private TextView fname;
    private TextView lname;
    private TextView empMail;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fname = findViewById(R.id.name_fname);
        lname = findViewById(R.id.name_lname);
        empMail = findViewById(R.id.emp_email);
        phone = findViewById(R.id.phone);

        Intent intent = getIntent();
        String s = intent.getStringExtra("email");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Admins").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(DocumentSnapshot snap : value) {
                    Admin admin = snap.toObject(Admin.class);
                    if(s.equals(admin.getEmail())){
                        fname.setText(admin.getFname());
                        lname.setText(admin.getLname());
                        empMail.setText(admin.getEmail());
                        phone.setText(admin.getPhone());
                    }
                }
            }
        });

    }
}
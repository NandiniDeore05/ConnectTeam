package com.cd17.connectteam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class YoeActivity extends AppCompatActivity {

    private EditText from;
    private EditText to;
    private Button filter;
    private ListView filterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoe);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        filter = findViewById(R.id.filter_but);
        filterList = findViewById(R.id.filterList);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list1 = new ArrayList<>();
                ArrayAdapter adapter = new ArrayAdapter<String>(YoeActivity.this, R.layout.list_item, list1);
                filterList.setAdapter(adapter);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        list1.clear();
                        for(DocumentSnapshot snap : value) {
                            Employee emp = snap.toObject(Employee.class);
                            int exp = Integer.parseInt(emp.getYoe());
                            String f = from.getText().toString();
                            int start = Integer.parseInt(f);
                            String t = to.getText().toString();
                            int end = Integer.parseInt(t);
                            if (exp >= start && exp <= end) {
                                String text = emp.getId( ) + " : " + emp.getFname() + " " + emp.getLname();
                                list1.add(text);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
               /* DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list1.clear();
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {

                            Employee emp = snap.getValue(Employee.class);
                            int exp = Integer.parseInt(emp.getYoe());
                            String f = from.getText().toString();
                            int start = Integer.parseInt(f);
                            String t = to.getText().toString();
                            int end = Integer.parseInt(t);
                            if (exp >= start && exp <= end) {
                                String text = emp.getId() + " : " + emp.getFname() + " " + emp.getLname();
                                list1.add(text);
                            }

                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
            }

        });
    }
}
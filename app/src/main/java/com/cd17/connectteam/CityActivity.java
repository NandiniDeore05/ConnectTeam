package com.cd17.connectteam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {

    private EditText city;
    private Button sort;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        city = findViewById(R.id.City);
        sort = findViewById(R.id.city_but);
        lv = findViewById(R.id.sortList);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                ArrayAdapter adapter = new ArrayAdapter<String>(CityActivity.this, R.layout.list_item, list);
                lv.setAdapter(adapter);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        list.clear();
                        for (DocumentSnapshot snap : value) {

                            Employee emp = snap.toObject(Employee.class);
                            String txt_city = city.getText().toString();
                            String city = emp.getCity();
                            if (txt_city.equalsIgnoreCase(city)) {
                                String text = emp.getId() + " : " + emp.getFname() + " " + emp.getLname();
                                list.add(text);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

              /*  DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {

                            Employee emp = snap.getValue(Employee.class);
                            String txt_city = city.getText().toString();
                            String city = emp.getCity();
                            if(txt_city.equalsIgnoreCase(city)){
                                String text = emp.getId() + " : " + emp.getFname() + " " + emp.getLname();
                                list.add(text);
                            }
                           boolean equal = txt_city.equals(city);
                            if(equal){
                                String text = emp.getId() + " : " + emp.getFname() + " " + emp.getLname();
                                list.add(text);
                            }

                            if(txt_city.equals(city) && txt_city.hashCode() == city.hashCode()){
                                String text = emp.getId() + " : " + emp.getFname() + " " + emp.getLname();
                                list.add(text);
                            }

                            if(txt_city.compareToIgnoreCase(city)==0){
                                String text = emp.getId() + " : " + emp.getFname() + " " + emp.getLname();
                                list.add(text);
                            }
                            if(txt_city.contentEquals("Pune")){
                                String text = emp.getId() + " : " + emp.getFname() + " " + emp.getLname();
                                list.add(text);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
            }
        });
    }
}
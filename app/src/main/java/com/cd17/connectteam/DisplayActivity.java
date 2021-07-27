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

public class DisplayActivity extends AppCompatActivity
{

    private TextView name_fname;
    private TextView name_lname;
    private TextView emp_email;
    private TextView phone;
    private TextView city;
    private TextView doj;
    private TextView gender;
    private TextView age;
    private TextView quali;
    private TextView domain;
    private TextView yoe;
    private TextView role;
    private TextView salary;
    private TextView leaves;
    private TextView dept;
    private TextView proj;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        name_fname = findViewById(R.id.name_fname);
        name_lname = findViewById(R.id.name_lname);
        emp_email = findViewById(R.id.emp_email);
        phone = findViewById(R.id.phone);
        city = findViewById(R.id.city);
        doj = findViewById(R.id.date);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        quali = findViewById(R.id.qualification);
        domain = findViewById(R.id.domain);
        yoe = findViewById(R.id.yoe);
        role = findViewById(R.id.role);
        salary = findViewById(R.id.salary);
        leaves = findViewById(R.id.leaves);
        dept = findViewById(R.id.dept);
        proj = findViewById(R.id.proj);

        Intent intent = getIntent();
        String s = intent.getStringExtra("key");

        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
            {
                for(DocumentSnapshot snap : value)
                {
                    System.out.println(snap.getString("id")+" " +s.equals(snap.getString("id")) + s + "   ############");
                    if( s.equalsIgnoreCase(snap.getString("id")) )
                    {
                        name_fname.setText(snap.getString("fname"));
                        name_lname.setText(snap.getString("lname"));
                        emp_email.setText(snap.getString("mail"));
                        phone.setText(snap.getString("phone"));
                        city.setText(snap.getString("city"));
                        doj.setText(snap.getString("doj"));
                        gender.setText(snap.getString("gender"));
                        age.setText(snap.getString("age"));
                        quali.setText(snap.getString("qualification"));
                        domain.setText(snap.getString("domain"));
                        yoe.setText(snap.getString("yoe"));
                        role.setText(snap.getString("role"));
                        salary.setText(snap.getString("salary"));
                        leaves.setText(snap.getString("leaves"));
                        dept.setText(snap.getString("dept"));
                        proj.setText(snap.getString("project"));
                    }
                }
            }
        });
        // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        //  DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");



        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot dataSnapshot, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snap : dataSnapshot) {
                    Employee emp = snap.toObject(Employee.class);
                    if (Integer.parseInt(s) == Integer.parseInt(emp.getId())) {
                        name_fname.setText(emp.getFname());
                        name_lname.setText(emp.getLname());
                        emp_email.setText(emp.getMail());
                        phone.setText(emp.getPhone());
                        city.setText(emp.getCity());
                        doj.setText(emp.getDoj());
                        gender.setText(emp.getGender());
                        age.setText(emp.getAge());
                        quali.setText(emp.getQualification());
                        domain.setText(emp.getDomain());
                        yoe.setText(emp.getYoe());
                        role.setText(emp.getRole());
                        salary.setText(emp.getSalary());
                        leaves.setText(emp.getLeaves());
                        dept.setText(emp.getDept());
                        proj.setText(emp.getProject());
                    }
                }
            }
         /*reference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for(DataSnapshot snap : dataSnapshot.getChildren()) {
                     Employee emp = snap.getValue(Employee.class);
                     if(Integer.parseInt(s) == Integer.parseInt(emp.getId()) ) {
                         fname.setText(emp.getFname());
                         lname.setText(emp.getLname());
                         empMail.setText(emp.getMail());
                         phone.setText(emp.getPhone());
                         city.setText(emp.getCity());
                         doj.setText(emp.getDoj());
                         gender.setText(emp.getGender());
                         age.setText(emp.getAge());
                         quali.setText(emp.getQualification());
                         domain.setText(emp.getDomain());
                         yoe.setText(emp.getYoe());
                         role.setText(emp.getRole());
                         salary.setText(emp.getSalary());
                         leaves.setText(emp.getLeaves());
                         dept.setText(emp.getDept());
                         proj.setText(emp.getProject());
                     }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });*/

        });
    }
}

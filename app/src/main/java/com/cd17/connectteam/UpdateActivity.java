package com.cd17.connectteam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class UpdateActivity extends AppCompatActivity
{

    private EditText empid;
    private EditText fname;
    private EditText lname;
    private EditText empMail;
    private EditText phone;
    private EditText city;
    private EditText doj;
    private EditText gender;
    private EditText age;
    private EditText quali;
    private EditText domain;
    private EditText yoe;
    private EditText role;
    private EditText salary;
    private EditText leaves;
    private EditText dept;
    private EditText proj;
    private Button updateButton;

    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        empid = findViewById(R.id.empid);
        fname = findViewById(R.id.name_fname);
        lname = findViewById(R.id.name_lname);
        empMail = findViewById(R.id.emp_email);
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
        updateButton = findViewById(R.id.updateButton);

        Intent intent = getIntent();
        String s = intent.getStringExtra("key");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

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
                        empid.setText(snap.getString("id"));
                        fname.setText(snap.getString("fname"));
                        lname.setText(snap.getString("lname"));
                        empMail.setText(snap.getString("mail"));
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

        updateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txtId = empid.getText().toString();
                String txtFname = fname.getText().toString();
                String txtLname = lname.getText().toString();
                String txtEmail = empMail.getText().toString();
                String txtPhone = phone.getText().toString();
                String txtCity = city.getText().toString();
                String txtDoj = doj.getText().toString();
                String txtGender = gender.getText().toString();
                String txtAge = age.getText().toString();
                String txtQuali = quali.getText().toString();
                String txtDomain = domain.getText().toString();
                String txtYoe = yoe.getText().toString();
                String txtRole = role.getText().toString();
                String txtSalary = salary.getText().toString();
                String txtLeaves = leaves.getText().toString();
                String txtDept = dept.getText().toString();
                String txtProj = proj.getText().toString();

                if(TextUtils.isEmpty(txtId) || TextUtils.isEmpty(txtFname) || TextUtils.isEmpty(txtLname) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtCity) || TextUtils.isEmpty(txtDoj) || TextUtils.isEmpty(txtGender) || TextUtils.isEmpty(txtAge) || TextUtils.isEmpty(txtQuali) || TextUtils.isEmpty(txtDomain) || TextUtils.isEmpty(txtYoe) || TextUtils.isEmpty(txtRole) || TextUtils.isEmpty(txtSalary) || TextUtils.isEmpty(txtLeaves) || TextUtils.isEmpty(txtDept) || TextUtils.isEmpty(txtProj)){
                    Toast.makeText(UpdateActivity.this, "Fill All Credentials", Toast.LENGTH_SHORT).show();
                }else{

                    DocumentReference ref = FirebaseFirestore.getInstance().collection("Employees").document("Emp"+s);
                    // HERE WE'RE NOT UPDATNG ID ALSO WE HAVE DISABLED EDING IN XML
                    ref.update("id",txtId);
                    ref.update("fname",txtFname);
                    ref.update("lname",txtLname);
                    ref.update("mail",txtEmail);
                    ref.update("phone",txtPhone);
                    ref.update("city",txtCity);
                    ref.update("doj",txtDoj);
                    ref.update("gender",txtGender);
                    ref.update("age",txtAge);
                    ref.update("qualification",txtQuali);
                    ref.update("domain",txtDomain);
                    ref.update("yoe",txtYoe);
                    ref.update("role",txtRole);
                    ref.update("salary",txtSalary);
                    ref.update("leaves",txtLeaves);
                    ref.update("dept",txtDept);
                    ref.update("project",txtProj).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            Toast.makeText(UpdateActivity.this, "Employee Info Updated", Toast.LENGTH_SHORT).show();
                        }
                    });

                    /*HashMap<String, Object> map = new HashMap<>();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Employees").document("Emp" + txtId).set(map).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            Toast.makeText(UpdateActivity.this, "Employee Info Updated", Toast.LENGTH_SHORT).show();
                        }
                    });*/

                    //Toast.makeText(UpdateActivity.this, "Employee Info Updated", Toast.LENGTH_SHORT).show();
                    //FirebaseDatabase.getInstance().getReference().child("Employees").child("Emp" + txtId).updateChildren(map);

                    Intent intent = new Intent(UpdateActivity.this , MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        //startActivity(new Intent(UpdateActivity.this , UpdateFragment.class));
        //finish();
    }
}

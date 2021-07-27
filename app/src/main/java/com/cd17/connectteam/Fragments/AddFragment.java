package com.cd17.connectteam.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd17.connectteam.MainActivity;
import com.cd17.connectteam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddFragment extends Fragment
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
    private Button addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        empid = view.findViewById(R.id.empid);
        fname = view.findViewById(R.id.name_fname);
        lname = view.findViewById(R.id.name_lname);
        empMail = view.findViewById(R.id.emp_email);
        phone = view.findViewById(R.id.phone);
        city = view.findViewById(R.id.city);
        doj = view.findViewById(R.id.date);
        gender = view.findViewById(R.id.gender);
        age = view.findViewById(R.id.age);
        quali = view.findViewById(R.id.qualification);
        domain = view.findViewById(R.id.domain);
        yoe = view.findViewById(R.id.yoe);
        role = view.findViewById(R.id.role);
        salary = view.findViewById(R.id.salary);
        leaves = view.findViewById(R.id.leaves);
        dept = view.findViewById(R.id.dept);
        proj = view.findViewById(R.id.proj);
        addButton = view.findViewById(R.id.addButton);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtId = empid.getText().toString().trim();
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

                if(TextUtils.isEmpty(txtId) || TextUtils.isEmpty(txtFname) || TextUtils.isEmpty(txtLname) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtCity) || TextUtils.isEmpty(txtDoj) || TextUtils.isEmpty(txtGender) || TextUtils.isEmpty(txtAge) || TextUtils.isEmpty(txtQuali) || TextUtils.isEmpty(txtDomain) || TextUtils.isEmpty(txtYoe) || TextUtils.isEmpty(txtRole) || TextUtils.isEmpty(txtSalary) || TextUtils.isEmpty(txtLeaves) || TextUtils.isEmpty(txtDept) || TextUtils.isEmpty(txtProj))
                {
                    Toast.makeText(getActivity(),"Empty Credentials!",Toast.LENGTH_SHORT).show();
                }
                else {

                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    Map<String, Object> map = new HashMap<>();
                    map.put("id",txtId);
                    map.put("fname",txtFname);
                    map.put("lname",txtLname);
                    map.put("mail",txtEmail);
                    map.put("phone",txtPhone);
                    map.put("city",txtCity);
                    map.put("doj",txtDoj);
                    map.put("gender",txtGender);
                    map.put("age",txtAge);
                    map.put("qualification",txtQuali);
                    map.put("domain",txtDomain);
                    map.put("yoe",txtYoe);
                    map.put("role",txtRole);
                    map.put("salary",txtSalary);
                    map.put("leaves",txtLeaves);
                    map.put("dept",txtDept);
                    map.put("project",txtProj);

                    //  FirebaseDatabase.getInstance().getReference().child("Employees").child("Emp" + txtId).updateChildren(map);
                    db.collection("Employees").document("Emp" + txtId).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(),"Employee is added successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Intent intent = new Intent(getActivity() , MainActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });

        return view;
    }

}
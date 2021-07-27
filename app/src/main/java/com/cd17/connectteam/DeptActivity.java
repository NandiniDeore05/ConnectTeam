package com.cd17.connectteam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DeptActivity extends AppCompatActivity
{
    private EditText inputDept;
    private Button deptFilter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);

        inputDept = findViewById(R.id.inputDept);
        deptFilter = findViewById(R.id.deptFilter);

        deptFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_dept = inputDept.getText().toString();

                if(TextUtils.isEmpty(txt_dept))
                    Toast.makeText(DeptActivity.this, "Enter Department", Toast.LENGTH_SHORT).show();
                else
                {
                    listView = findViewById(R.id.listView);
                    ArrayList<String> list = new ArrayList<>();
                    ArrayAdapter adapter = new ArrayAdapter<String>(DeptActivity.this, R.layout.list_item,list);
                    listView.setAdapter(adapter);


                    FirebaseFirestore db =FirebaseFirestore.getInstance();
                    db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>()
                    {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
                        {
                            list.clear();
                            for(DocumentSnapshot snap : value)
                            {
                                System.out.println(snap.getString("id")+" " +txt_dept.equals(snap.getString("id")) + txt_dept + "   ############");
                                if( txt_dept.equalsIgnoreCase(snap.getString("dept")) )
                                {
                                    String txt = snap.getString("id") + ": " + snap.getString("fname") + " " + snap.getString("lname");
                                    list.add(txt);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            //position=position+1;
                            String[] strings = list.get(position).split(":");
                            System.out.println(strings[0]+"########");
                            String txt_id = String.valueOf(strings[0]);
                            Intent intent = new Intent(DeptActivity.this, DisplayActivity.class);
                            intent.putExtra("key" , txt_id);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}
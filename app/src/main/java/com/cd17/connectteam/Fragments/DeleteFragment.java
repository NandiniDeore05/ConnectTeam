package com.cd17.connectteam.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd17.connectteam.Employee;
import com.cd17.connectteam.MainActivity;
import com.cd17.connectteam.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class DeleteFragment extends Fragment
{
    private EditText id;
    private Button DeleteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_delete, container, false);

        id = rootView.findViewById(R.id.empid);
        DeleteButton = rootView.findViewById(R.id.delete_btn);

        DeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_id = id.getText().toString();
                if(TextUtils.isEmpty(txt_id))
                    Toast.makeText(getActivity(), "ENTER ID TO DELETE", Toast.LENGTH_SHORT).show();
                else
                {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            for(DocumentSnapshot snap : value) {
                                Employee emp = snap.toObject(Employee.class);
                                if(Integer.parseInt(txt_id) == Integer.parseInt(emp.getId()) ) {
                                    AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                            .setTitle("Delete")
                                            .setMessage("Want to delete an employee")
                                            .setPositiveButton("Yes", null)
                                            .setNegativeButton("No", null)
                                            .show();

                                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                    positiveButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            snap.getReference().delete();
                                            Toast.makeText(getActivity(), "Employee Deleted", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            getActivity().startActivity(intent);
                                        }
                                    });
                                    Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                                    negativeButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            }
                        }
                    });
                   /* DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");
                    reference.addValueEventListener(new ValueEventListener()
>>>>>>> 87206a16c34af8f0d9a547bc38c3024be056d379
                    {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
                        {
                            for(DocumentSnapshot snap : value)
                            {
                                if(txt_id.equals( snap.getString("id") ))
                                {
                                    AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                            .setTitle("Delete")
                                            .setMessage("Want to delete an employee")
                                            .setPositiveButton("Yes" , null)
                                            .setNegativeButton("No" , null)
                                            .show();

                                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                    positiveButton.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v)
                                        {
                                            snap.getReference().delete();
                                            Toast.makeText(getActivity(), "Employee Deleted", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                            Intent intent = new Intent(getActivity() , MainActivity.class);
                                            getActivity().startActivity(intent);
                                        }
                                    });
                                    Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                                    negativeButton.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v)
                                        {
                                            dialog.dismiss();
                                        }
                                    });
                                }
<<<<<<< HEAD
                            }
                        }
                    });
=======
                                else {
                                    Toast.makeText(getActivity(), "NOT FOUND", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/
                }

            }
        });
        return rootView;
    }
}
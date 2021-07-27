package com.cd17.connectteam.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd17.connectteam.R;
import com.cd17.connectteam.UpdateActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateFragment extends Fragment
{
    private EditText empid;
    private Button update;

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);
        context = rootView.getContext();
        empid = rootView.findViewById(R.id.empid);
        update = rootView.findViewById(R.id.update_btn);

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_id = empid.getText().toString().trim();

                if(TextUtils.isEmpty(txt_id))
                    Toast.makeText(context, "ENTER ID TO UPDATE", Toast.LENGTH_SHORT).show();
                else
                {
                    CollectionReference ref = FirebaseFirestore.getInstance().collection("Employees");

                    Intent intent = new Intent(getActivity() , UpdateActivity.class);
                    intent.putExtra("key" , txt_id);
                    getActivity().startActivity(intent);

                }
            }
        });

        return rootView;
    }
}

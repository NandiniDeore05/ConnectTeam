package com.cd17.connectteam.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cd17.connectteam.CityActivity;
import com.cd17.connectteam.DeptActivity;
import com.cd17.connectteam.R;
import com.cd17.connectteam.RoleActivity;
import com.cd17.connectteam.YoeActivity;

public class FilterFragment extends Fragment
{
    private ListView lv;
    String items[] = new String [] {"Year of Experience","City","Department","Role"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_filter, container, false);

        lv = v.findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(), YoeActivity.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(view.getContext(), CityActivity.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(view.getContext(), DeptActivity.class);
                    startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(view.getContext(), RoleActivity.class);
                    startActivity(intent);
                }
            }
        });

        return v;
    }
}
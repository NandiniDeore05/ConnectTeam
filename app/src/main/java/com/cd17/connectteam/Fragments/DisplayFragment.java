package com.cd17.connectteam.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cd17.connectteam.Employee;
import com.cd17.connectteam.R;

import com.cd17.connectteam.DisplayActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;



public class DisplayFragment extends Fragment
{
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        listView = view.findViewById(R.id.listView);
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item,list);
        listView.setAdapter(adapter);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
     /*   CollectionReference col = db.collection("Employees");
        Query query = col.orderBy("id");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    list.clear();
                    for(QueryDocumentSnapshot snap:task.getResult()){
                        Employee em = snap.toObject(Employee.class);
                        String txt = em.getId() + ": " + em.getFname() + " " + em.getLname();

                        list.add(txt);
                }
                    adapter.notifyDataSetChanged();
            }
        }});*/


       db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
                list.clear();
                for(DocumentSnapshot snap : documentSnapshots){
                    Employee em = snap.toObject(Employee.class);
                    String txt = em.getId() + ": " + em.getFname() + " " + em.getLname();

                    list.add(txt);
            }
                adapter.notifyDataSetChanged();
                //Collections.sort(list);
                //list.sort(Comparator.naturalOrder());
                //list.stream().sorted().collect(Collectors.toList());
               // Collections.sort(list.subList(1, list.size()));
                //list.sort(Sortbyid());
               // list.sort(Comparator.comparingInt(Employee::getId));
               /* ArrayList<Integer> newList = new ArrayList<Integer>(id.size());
                for(String myInt : list){
                    newList.add(Integer.valueOf(myInt));
                }
                Collections.sort(newList);*/
               // Collections.sort(list,Collections.reverseOrder());

       }});


       /* DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");
        reference.addValueEventListener(new ValueEventListener() {
>>>>>>> 87206a16c34af8f0d9a547bc38c3024be056d379
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
            {
                list.clear();
<<<<<<< HEAD
                for(DocumentSnapshot snap : value)
                {
                    String txt = snap.getString("id") + ": " + snap.getString("fname") + " " + snap.getString("lname");
=======
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Employee em = snap.getValue(Employee.class);
                    String txt = em.getId() + ": " + em.getFname() + " " + em.getLname();
>>>>>>> 87206a16c34af8f0d9a547bc38c3024be056d379
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }
<<<<<<< HEAD
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position=position+1;
                String[] strings = list.get(position).split(":");
                System.out.println(strings[0]+"########");
=======

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               // Log.e(TAG, "Failed to read value.", databaseError.toException());

            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String[] strings = list.get(position).split(":");
                System.out.println(strings[0]+"####");
                String txt_id = String.valueOf(strings[0]);
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra("key" , txt_id);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}

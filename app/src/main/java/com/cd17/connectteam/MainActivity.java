package com.cd17.connectteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cd17.connectteam.Fragments.AddFragment;
import com.cd17.connectteam.Fragments.DeleteFragment;
import com.cd17.connectteam.Fragments.DisplayFragment;
import com.cd17.connectteam.Fragments.FilterFragment;
import com.cd17.connectteam.Fragments.UpdateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    //private Button logout;

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String txt_email = currentUser.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged Out..!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this , StartActivity.class));
                finish();
            }
        });*/


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.nav_display:
                        selectorFragment = new DisplayFragment();
                        break;

                    case R.id.nav_add:
                        selectorFragment = new AddFragment();
                        break;

                    case R.id.nav_update:
                        selectorFragment = new UpdateFragment();
                        break;

                    case R.id.nav_delete:
                        selectorFragment = new DeleteFragment();
                        break;

                    case R.id.nav_filter:
                        selectorFragment = new FilterFragment();
                        break;
                }

                if(selectorFragment != null)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectorFragment).commit();
                }
                return true;
            }
        });
        // INITALLY DISPLAY FRAGMENT SHOULD BE VISIBLE
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new DisplayFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){

            case R.id.profile:
                        Intent intent = new Intent(MainActivity.this , ProfileActivity.class);
                        intent.putExtra("email" , currentUser.getEmail());
                        startActivity(intent);
                break;

            case R.id.logout:
                AlertDialog();
                break;
        }
        return true;
    }
    public void AlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Exit..!!");
        builder.setIcon(R.drawable.ic_exit);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity.this, "Logged Out..!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, StartActivity.class));
                        finish();

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

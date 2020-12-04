package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, endpage;
    Button btnAddNew;

    DatabaseReference reference;
    RecyclerView ourdoes;
    ArrayList<MyDoes> list;
    DoesAdaptar doesAdaptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage =  (TextView) findViewById(R.id.titlepage);
        endpage = (TextView) findViewById(R.id.endpage);


        //import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Saira-Medium.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/Saira-Bold.ttf");

        // customize fonts
        titlepage.setTypeface(MMedium);
        endpage.setTypeface(MLight);


        /*
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, NewTaskAct.class);
                startActivity(a);
            }
        });
        */
        //working with data
        ourdoes = (RecyclerView) findViewById(R.id.ourdoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyDoes>();

        // get data from firebase

        reference = FirebaseDatabase.getInstance().getReference().child("BoxDoese" + Build.MODEL + Build.DEVICE);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // set code to retrive date and replace layout
                list.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        MyDoes p = dataSnapshot1.getValue(MyDoes.class);
                        list.add(p);
                    }
                if (list.size() == 0) {
                    MainActivity.super.finish();
                    Intent a = new Intent(MainActivity.this, NewTaskAct.class);
                    a.putExtra("firstdoes", "true");
                    a.putExtra("keydoes", "0");
                    startActivity(a);
                }
                    doesAdaptar = new DoesAdaptar(MainActivity.this, list);
                    ourdoes.setAdapter(doesAdaptar);
                    doesAdaptar.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // set code to show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlepage, newtask;
    EditText  titledoes, descdoes;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    String keydoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titlepage = (TextView) findViewById(R.id.titlepage);
        newtask = (TextView) findViewById(R.id.newtask);


        titledoes = (EditText) findViewById(R.id.titledoes);
        descdoes = (EditText) findViewById(R.id.descdoes);

        keydoes = getIntent().getStringExtra("keydoes");

        btnSaveTask = (Button) findViewById(R.id.btnSaveTask);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference()
                        .child("BoxDoese" + Build.MODEL + Build.DEVICE)
                        .child("Does" + keydoes);
                reference.getRef().child("titledoes").setValue(titledoes.getText().toString());
                reference.getRef().child("descdoes").setValue(descdoes.getText().toString());
                reference.getRef().child("confirmdoes").setValue("false");
                reference.getRef().child("keydoes").setValue(keydoes);

                if(getIntent().getStringExtra("firstdoes").equals("true")){
                    reference = FirebaseDatabase.getInstance().getReference()
                            .child("BoxDoese" + Build.MODEL + Build.DEVICE)
                            .child("DoesINDEX");
                    reference.getRef().child("titledoes").setValue("");
                    reference.getRef().child("descdoes").setValue("");
                    reference.getRef().child("confirmdoes").setValue("true");
                    reference.getRef().child("keydoes").setValue("INDEX");
                }

                Intent a = new Intent(NewTaskAct.this, MainActivity.class);
                startActivity(a);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(NewTaskAct.this, MainActivity.class);
                startActivity(a);
            }
        });

        //import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");

        // customize fonts
        titlepage.setTypeface(MMedium);
        newtask.setTypeface(MMedium);

        titledoes.setTypeface(MMedium);
        descdoes.setTypeface(MLight);

        btnSaveTask.setTypeface(MMedium);
        btnCancel.setTypeface(MMedium);
    }
}

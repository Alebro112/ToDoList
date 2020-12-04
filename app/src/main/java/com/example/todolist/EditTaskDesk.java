package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    TextView titlepage, descdoes;
    EditText titledoes;
    Button btnSaveUpdate, btnDelete;
    String confirmdoes;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titlepage = (TextView) findViewById(R.id.titlepage);

        titledoes = (EditText) findViewById(R.id.titledoes);
        descdoes = (EditText) findViewById(R.id.descdoes);

        btnSaveUpdate = (Button) findViewById(R.id.btnSaveUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        titledoes.setText(getIntent().getStringExtra("titledoes"));
        descdoes.setText(getIntent().getStringExtra("descdoes"));
        confirmdoes = getIntent().getStringExtra("confirmdoes");


        String keydoes = getIntent().getStringExtra("keydoes");

        // event for button

        reference = FirebaseDatabase.getInstance().getReference()
                .child("BoxDoese" + Build.MODEL + Build.DEVICE)
                .child("Does" + keydoes);

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.getRef().child("titledoes").setValue(titledoes.getText().toString());
                reference.getRef().child("descdoes").setValue(descdoes.getText().toString());
                reference.getRef().child("keydoes").setValue(keydoes);
                reference.getRef().child("confirmdoes").setValue(confirmdoes);

                Intent a = new Intent(EditTaskDesk.this, SeeTask.class);
                a.putExtra("titledoes", titledoes.getText().toString());
                a.putExtra("descdoes", descdoes.getText().toString());
                a.putExtra("keydoes", keydoes);
                a.putExtra("confirmdoes", confirmdoes);
                startActivity(a);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete( Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent a = new Intent(EditTaskDesk.this, MainActivity.class);
                            EditTaskDesk.super.finish();
                            startActivity(a);
                        } else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}


package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SeeTask extends AppCompatActivity {

    TextView titledose, descdoes, titlepage;
    Button btnEdit, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_task);

        titlepage = (TextView) findViewById(R.id.titlepage);
        titledose = (TextView) findViewById(R.id.titledoes);
        descdoes = (TextView) findViewById(R.id.descdoes);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnBack = (Button) findViewById(R.id.btnCancel);

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Saira-Medium.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/Saira-Bold.ttf");

        titledose.setTypeface(MMedium);
        descdoes.setTypeface(MLight);
        titlepage.setTypeface(MMedium);

        titledose.setText(getIntent().getStringExtra("titledoes"));
        descdoes.setText(getIntent().getStringExtra("descdoes"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(SeeTask.this, EditTaskDesk.class);
                edit.putExtra("titledoes", titledose.getText().toString());
                edit.putExtra("descdoes", descdoes.getText().toString());
                edit.putExtra("keydoes", getIntent().getStringExtra("keydoes"));
                edit.putExtra("confirmdoes", getIntent().getStringExtra("confirmdoes"));
                startActivity(edit);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SeeTask.this, MainActivity.class);
                startActivity(back);
            }
        });


    }
}
package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import java.util.ArrayList;

public class DoesAdaptar extends RecyclerView.Adapter<DoesAdaptar.MyViewHolder>  {

    Context context;
    ArrayList<MyDoes> myDoes;

    DatabaseReference reference;

    public DoesAdaptar(Context c, ArrayList<MyDoes> p) {
        context  = c;
        myDoes = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final String getKeyDoes = myDoes.get(position).getKeydoes();


        Typeface MLight = Typeface.createFromAsset(context.getAssets(), "fonts/Saira-Medium.ttf");
        Typeface MMedium = Typeface.createFromAsset(context.getAssets(), "fonts/Saira-Bold.ttf");

        if (getKeyDoes.equals("INDEX")) {
            holder.doesBG.setBackgroundResource(R.drawable.bgindextask);
            holder.confirmdoesMarker.setVisibility(View.GONE);

            holder.titledoes.setText("+");
            holder.titledoes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //holder.titledoes.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            holder.titledoes.setTypeface(MMedium);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, -22, 0, 0);
            holder.titledoes.setTextSize(48);
            holder.titledoes.setLayoutParams(lp);
            holder.titledoes.setTextColor(context.getColor(R.color.doesMainColor));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newDoes = new Intent(context, NewTaskAct.class);
                    newDoes.putExtra("keydoes", Integer.toString(myDoes.size() - 1));
                    context.startActivity(newDoes);
                }
            });
        } else {
            final String getTitleDoes = myDoes.get(position).getTitledoes();
            final String getConfirmDoes = myDoes.get(position).getConfirmdoes();
            final String getDescDoes = myDoes.get(position).getDescdoes();

            holder.titledoes.setText(getTitleDoes);
            holder.titledoes.setTypeface(MMedium);

            if (getConfirmDoes.equals("true")) {
                holder.confirmdoesMarker.setImageResource(R.drawable.confirm);
                holder.titledoes.setPaintFlags(holder.titledoes.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    reference = FirebaseDatabase.getInstance().getReference()
                            .child("BoxDoese" + Build.MODEL + Build.DEVICE)
                            .child("Does" + Integer.toString(position));
                    if (getConfirmDoes.equals("true")) {
                        reference.getRef().child("confirmdoes").setValue("false");
                    } else {
                        reference.getRef().child("confirmdoes").setValue("true");
                        Toast.makeText(context.getApplicationContext(), "Ты молодец!", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent aa = new Intent(context, SeeTask.class);
                    aa.putExtra("titledoes", getTitleDoes);
                    aa.putExtra("descdoes", getDescDoes);
                    aa.putExtra("keydoes", Integer.toString(position));
                    aa.putExtra("confirmdoes", getConfirmDoes);
                    context.startActivity(aa);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }


    class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView titledoes;
        ImageView confirmdoesMarker;
        LinearLayout doesBG;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = (TextView) itemView.findViewById(R.id.titledoes);
            confirmdoesMarker = (ImageView) itemView.findViewById(R.id.confirmdoesMarker);
            doesBG = (LinearLayout) itemView.findViewById(R.id.doesBG);
        }
    }

}



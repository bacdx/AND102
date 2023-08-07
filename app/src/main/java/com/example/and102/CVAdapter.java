package com.example.and102;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CVAdapter extends RecyclerView.Adapter<CVAdapter.Holder> {

    FirebaseFirestore database;
    Context context;
    ArrayList<Todo> list;

    public CVAdapter(FirebaseFirestore database, Context context, ArrayList<Todo> list) {
        this.database = database;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CVAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,null);
        return new Holder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CVAdapter.Holder holder, int position) {
        Todo todo=list.get(position);

        holder.title.setText(todo.getTitle());
        holder.content.setText(todo.getContent());
        holder.status.setText(todo.getStatus());
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder=new AlertDialog.Builder(context);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("huy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }

            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        Button btnDelete,btnUpdate;
        TextView title,content,status;
        public Holder(@NonNull View itemView) {
            super(itemView);
            btnUpdate=itemView.findViewById(R.id.btnupdate);
            btnDelete=itemView.findViewById(R.id.btndelete);
        }
    }
}

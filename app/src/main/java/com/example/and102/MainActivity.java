package com.example.and102;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore database ;

    EditText title,content,status;
    Todo todo;
    Button btnadd;
    CVAdapter adapter;

    RecyclerView recyclerView;
    ArrayList<Todo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rcview);
        title=findViewById(R.id.edtitle);
        content=findViewById(R.id.edcontent);
        status=findViewById(R.id.edstatus);
        list=new ArrayList<>();
        LinearLayoutManager manager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        database=FirebaseFirestore.getInstance();
        adapter=new CVAdapter(database,this,list);
        ListenFirebaseFirestrore();


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String id= UUID.randomUUID().toString();
                todo.setId(id);
                todo.setTitle(title.getText().toString());
                todo.setContent(content.getText().toString());
                todo.setStatus(status.getText().toString());
                todo.setDate("111111");
                database.collection("congviec").document(id).set(todo.converHashMap())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });


    }

    private  void ListenFirebaseFirestrore(){
        database.collection("congviec").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null){
                    for(DocumentChange dc :value.getDocumentChanges() ){
                        if(dc.getType()==DocumentChange.Type.ADDED){
                            dc.getDocument().toObject(Todo.class);
                            adapter.notifyItemInserted(list.size()-1);
                        }
                    }
                }
            }
        });
        database.collection("congviec").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null){
                    for(DocumentChange dc :value.getDocumentChanges() ){
                        if(dc.getType()==DocumentChange.Type.MODIFIED){
                            Todo updateTodo=dc.getDocument().toObject(Todo.class);

                            if(dc.getOldIndex()==dc.getNewIndex()){
                                list.set(dc.getOldIndex(),updateTodo);
                                adapter.notifyItemChanged(dc.getOldIndex());
                            }else {
                                list.remove(dc.getOldIndex());
                                list.add(updateTodo);
                                adapter.notifyItemMoved(dc.getOldIndex(),dc.getNewIndex());
                            }
                        }
                    }
                }
            }
        });
    }




}
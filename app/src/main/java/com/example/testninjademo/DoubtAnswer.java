package com.example.testninjademo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoubtAnswer extends AppCompatActivity {
    TextView title,description,subject;
    EditText getanswer;
    Button submitbtn;
    DatabaseReference mRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doubtanswer);
        mRef = FirebaseDatabase.getInstance().getReference("doubts");

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        subject = findViewById(R.id.subject);
        submitbtn = findViewById(R.id.btnsubmitanswer);

        Intent intent = getIntent();

        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        subject.setText(intent.getStringExtra("subject"));
        String key = intent.getStringExtra("key");


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getanswer = findViewById(R.id.getanswer);
                String manswer =  getanswer.getText().toString();
                mRef.child(key).child("answer").setValue(manswer)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DoubtAnswer.this,"Answered",Toast.LENGTH_SHORT).show();
                        getanswer.setText("");
                    }
                });
            }
        });
    }
}

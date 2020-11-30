package com.example.testninjademo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button doubtBtn,solveDoubtBtn;
    Spinner subspinner;
    String item;
    EditText title,description;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRef = FirebaseDatabase.getInstance().getReference("doubts");
        spinner();
        askDoubt();
        solveDoubt();
    }

    private void solveDoubt() {
        solveDoubtBtn = findViewById(R.id.btnSolveDoubt);
        solveDoubtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent solveDoubtIntent = new Intent(MainActivity.this,SolveDoubt.class);
                startActivity(solveDoubtIntent);
            }
        });
    }

    public void spinner(){
        subspinner = findViewById(R.id.subSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.subjects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subspinner.setAdapter(adapter);
        subspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void askDoubt(){
        title = findViewById(R.id.etTitle);
        description = findViewById(R.id.etDescription);
        doubtBtn = findViewById(R.id.btnAsk);
        title.getText().toString();
        doubtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sTitle = title.getText().toString();
                String sDescription = description.getText().toString();
                if(sTitle.isEmpty() || sDescription.isEmpty()){
                    Toast.makeText(MainActivity.this,"All fields are neccessary",Toast.LENGTH_SHORT).show();
                }else{
                    Doubt doubt = new Doubt(title.getText().toString(),
                            description.getText().toString(),
                            item, "");
                    mRef.push().setValue(doubt).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            title.setText("");
                            description.setText("");
                        }
                    });
                }
            }
        });
    }

}
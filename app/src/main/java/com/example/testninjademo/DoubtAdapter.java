package com.example.testninjademo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class DoubtAdapter extends FirebaseRecyclerAdapter<Doubt, DoubtAdapter.DoubtViewHolder> {
    Context context;
    public DoubtAdapter(@NonNull FirebaseRecyclerOptions options, android.content.Context applicationContext) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoubtViewHolder holder, int position, @NonNull Doubt doubt) {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("doubts");

        String key = getRef(position).getKey();
        holder.title.setText(doubt.getTitle());
        holder.description.setText(doubt.getDescription());
        holder.subject.setText(doubt.getSubject());
        holder.answer.setText(doubt.getAnswer());
        holder.answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(context, DoubtAnswer.class);
                    intent.putExtra("title", doubt.getTitle().toString());
                    intent.putExtra("description", doubt.getDescription().toString());
                    intent.putExtra("subject", doubt.getSubject().toString());
                    intent.putExtra("key", key);
                    context.startActivity(intent);
                }

        });

    }

    @NonNull
    @Override
    public DoubtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doubt,parent,false);

        context = parent.getContext();
        return new DoubtViewHolder(view);
    }


    class DoubtViewHolder extends RecyclerView.ViewHolder{
        TextView title,subject,description,answer;
        Button answerBtn;
        public DoubtViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            subject = itemView.findViewById(R.id.tvDescription);
            description = itemView.findViewById(R.id.tvSubject);
            answer = itemView.findViewById(R.id.tvAnswer);
            answerBtn = itemView.findViewById(R.id.submit);
        }
    }
}



package com.example.myapplicationactivityandintent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView subjectTextView = findViewById(R.id.subjectTextView);
        TextView messageTextView = findViewById(R.id.messageTextView);

        String subject = getIntent().getStringExtra("subject");
        String message = getIntent().getStringExtra("message");

        if (subject != null) {
            subjectTextView.setText(subject);
        } else {
            subjectTextView.setText("Тема отсутствует");
        }

        if (message != null) {
            messageTextView.setText(message);
        } else {
            messageTextView.setText("Сообщение отсутствует");
        }
    }
}
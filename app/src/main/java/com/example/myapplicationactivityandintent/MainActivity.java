package com.example.myapplicationactivityandintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button sendMessageButton = findViewById(R.id.sendmessage);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Тема сообщения";
                String message = "Это текст сообщения, который отправляется через Gmail.";

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                emailIntent.setPackage("com.google.android.gm");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Отправить через Gmail"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Установите Gmail для отправки сообщения", Toast.LENGTH_SHORT).show();
                }

                openSecondActivity(subject, message);
            }
        });

    }

    private void openSecondActivity(String subject, String message) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("subject", subject);
        intent.putExtra("message", message);
        startActivity(intent);
    }
}
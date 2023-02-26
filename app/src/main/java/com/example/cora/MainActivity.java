package com.example.cora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cora.userInterface.Chat;
import com.example.cora.userInterface.CodeGenerator;
import com.example.cora.userInterface.CodeEditor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button codeAPI = findViewById(R.id.generateBtn);
        Button textAPI = findViewById(R.id.chatBtn);
        Button editAPI = findViewById(R.id.syntaxBtn);

        editAPI.setOnClickListener(view->{
            Intent intent = new Intent(this, CodeEditor.class);
            startActivity(intent);

        });
        codeAPI.setOnClickListener(view->{
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);

        });

        codeAPI.setOnClickListener(view->{
            Intent intent = new Intent(this, CodeGenerator.class);
            startActivity(intent);

        });


    }
}
package com.example.cora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cora.userInterface.Chat;
import com.example.cora.userInterface.CodeEditor;
import com.example.cora.userInterface.EditActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button codeAPI = findViewById(R.id.codeBtn);
        Button textAPI = findViewById(R.id.textBtn);
        Button editAPI = findViewById(R.id.editBtn);

        editAPI.setOnClickListener(view->{
            Intent intent = new Intent(this, EditActivity.class);
            startActivity(intent);

        });
        codeAPI.setOnClickListener(view->{
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);

        });

        codeAPI.setOnClickListener(view->{
            Intent intent = new Intent(this, CodeEditor.class);
            startActivity(intent);

        });


    }
}
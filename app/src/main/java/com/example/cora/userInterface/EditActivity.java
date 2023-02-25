package com.example.cora.userInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cora.api.edits;

import com.example.cora.R;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        AssetManager assetManager = getAssets();

        TextView responsePane = findViewById(R.id.responsePane);
        responsePane.setMovementMethod(new ScrollingMovementMethod());

        EditText instruction = findViewById(R.id.instructionET);
        EditText prompt = findViewById(R.id.promptET);

        Button submitButton = findViewById(R.id.button);

        submitButton.setOnClickListener(view -> {
            edits.setCodeEditText(responsePane,
                    assetManager,
                    prompt.getText().toString(),
                    instruction.getText().toString());
        });
    }
}
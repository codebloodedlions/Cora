package com.example.cora.userInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cora.api.edits;

import com.example.cora.R;

public class CodeEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_editor);
        AssetManager assetManager = getAssets();

        TextView responsePane = findViewById(R.id.responsePane);
        responsePane.setMovementMethod(new ScrollingMovementMethod());

        EditText instruction = findViewById(R.id.instructionET);
        EditText prompt = findViewById(R.id.promptET);

        Button submitButton = findViewById(R.id.button);

        submitButton.setOnClickListener(view -> {
            String instructionStr = instruction.getText().toString();
            String promptStr = prompt.getText().toString();

            InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            Toast.makeText(CodeEditor.this, "Thinking...", Toast.LENGTH_LONG).show();
            responsePane.post(()->{
                edits.setCodeEditText(responsePane,
                        assetManager,
                        promptStr,
                        instructionStr);
                instruction.setText("");
                instruction.setHint("Previous Task: " +instructionStr);

                prompt.setText("");
                prompt.setHint("Previous Input: \n" + promptStr);
            });
        });
    }
}
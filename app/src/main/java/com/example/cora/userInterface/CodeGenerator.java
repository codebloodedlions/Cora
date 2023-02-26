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

import com.example.cora.api.completions;

import com.example.cora.R;


public class CodeGenerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_generator);
        AssetManager assetManager = getAssets();

        String completionType = "code-simple";
        TextView responsePane = findViewById(R.id.responsePane);
        responsePane.setMovementMethod(new ScrollingMovementMethod());
        EditText prompt = findViewById(R.id.editPrompt);
        Button submitPrompt = findViewById(R.id.submitButton);

        submitPrompt.setOnClickListener(view-> {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            Toast.makeText(CodeGenerator.this, "Thinking...", Toast.LENGTH_LONG).show();
            try {
                completions.setCodeCompleteText(responsePane,
                        assetManager,
                        prompt.getText().toString(),
                        completionType);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            prompt.setHint("Previous: " + prompt.getText().toString());
            prompt.setText("");
        });
    }
}
package com.example.cora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.cora.api.completions;
import com.example.cora.api.edits;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();

        String completionsPrompt = "create binary search algorithm in java";

        String editPrompt = "System.out,println(\"Hello World\"";
        String editInstruction = "Fix the syntax mistakes";

        // set OpenAI model to use -- use 'code-complex', 'code-simple', or 'text'
        String completionType = "code-simple";

        TextView mainText = findViewById(R.id.main_text);
        mainText.setMovementMethod(new ScrollingMovementMethod());

//        try {
//            completions.setCodeCompleteText(mainText, assetManager, completionsPrompt, completionType);
//            //edits.setCodeEditText(mainText, assetManager, editPrompt, editInstruction);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
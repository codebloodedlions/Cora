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

        String completionsPrompt = "    function binarySearch(arr, target) {\n" +
                "      let left = 0;\n" +
                "      let right = arr.length - 1;\n" +
                "      let middle = Math.floor((left + right) / 2);\n" +
                "    \n" +
                "      while (arr[middle] !== target && left <= right) {\n" +
                "        if (target < arr[middle]) {\n" +
                "          right = middle - 1;\n" +
                "        } else {\n" +
                "          left = middle + 1;\n" +
                "        }\n" +
                "        middle = Math.floor((left + right) / 2);\n" +
                "      }\n" +
                "    \n" +
                "      return arr[middle] === target ? middle : -1;\n" +
                "    }" +
                "\"\"\"What does this do?";

        String editPrompt = "System.out,println(\"Hello World\"";
        String editInstruction = "Fix the syntax mistakes";

        // set OpenAI model to use -- use 'code-complex', 'code-simple', or 'text'
        String completionType = "code-simple";

        TextView mainText = findViewById(R.id.main_text);
        mainText.setMovementMethod(new ScrollingMovementMethod());

        try {
            //completions.setCodeCompleteText(mainText, assetManager, completionsPrompt, completionType);
            edits.setCodeEditText(mainText, assetManager, editPrompt, editInstruction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
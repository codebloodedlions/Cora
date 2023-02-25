package com.example.cora.api;

import android.content.res.AssetManager;
import android.util.Log;
import android.widget.TextView;

import com.example.cora.api.Models.edits.eRoot;
import com.example.cora.file.keyRetrieval;
import com.example.cora.json.ResponseParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class edits {
    //get entire data
    public static eRoot getAPI_codeEditData(AssetManager assetManager, String prompt, String instruction)  {
        return gptAPI(assetManager, prompt, instruction);
    }

    // set text via UI thread
    public static void setCodeEditText(TextView textView, AssetManager assetManager, String prompt, String instruction) {
        textView.post(() -> {
            try {
                String temp = codeEditText(assetManager, prompt, instruction);
                //Log.i("[API-ADD-TEXT]", temp);
                textView.setText(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    //==============================================================================================

    // returns API response -- uses custom model, so it's easy to access each attribute
    private static eRoot gptAPI(AssetManager assetManager, String prompt, String instruction)  {
        StringBuilder response = new StringBuilder();
        eRoot responseData = null;

        try {
            Log.i("[API]", "[API] Beginning API Task(s)...");

            // set endpoint and open connection
            URL url = new URL("https://api.openai.com/v1/edits");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // set request method property
            connection.setRequestMethod("POST");

            // formatting
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // to use api key, create a file called 'API_KEY.txt' in 'main' and paste your API key on the very first line
            // "fileHandler" should automatically pick up the file and extract the key.
            connection.setRequestProperty("Authorization", "Bearer " + keyRetrieval.getKey(assetManager));

            // connect ensure -- can't write to connection if disabled or false
            connection.setDoOutput(true);

            /*
              Notes:
              - This is used for inputting text/code and returning a edited version
             */
            // json POST
            Log.i("[API]", "[EDIT-API] Made it past URL");

            JSONObject postBody = new JSONObject();
            postBody.put("model", "code-davinci-edit-001");
            postBody.put("input", prompt);
            postBody.put("instruction", instruction);
            postBody.put("temperature", 0.0);
            postBody.put("top_p", 1);

            Log.i("[API]", "[EDIT-API] Made it past POST");

            // post jsonObj to endpoint
            try (OutputStreamWriter osWriter = new OutputStreamWriter(connection.getOutputStream())) {
                Log.i("[API]", "[EDIT-API-POST] " + postBody);
                osWriter.write(postBody.toString());
            }
            Log.i("[API]", "[EDIT-API] Made it past POSTJSON2BYTEARRAY");

            // store response as string and print raw response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String jsonResponseString;
                while ((jsonResponseString = br.readLine()) != null) {
                    response.append(jsonResponseString.trim());
                }
                Log.i("[API]", "[EDIT-API] Made it past STRINGSTORE");
            }
            catch (Exception ex){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                Log.e("[EDIT-API]", "[EDIT-API-HTTP-ERR] " + connection.getResponseMessage());
                while (br.readLine() != null) {
                    Log.e("[API]", "[EDIT-API-HTTP-ERR]" + br.readLine());
                }
                System.exit(connection.getResponseCode());
            }

            Log.i("[API]", "[API-RESPONSE-TEST-PROMPT] >> " + prompt);

            // parse raw json string and store easily accessible data structure
            responseData = ResponseParser.parseEditJSON(String.valueOf(response));

            // leave arrayList element at 0 || get text is the generated response from the API
            Log.i("[API]", "[EDIT-TEST-PROMPT-ANSWER] >> " + responseData.choices.get(0).getText());

//            System.out,println("Hello World!");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        Log.i("[API]", "[API] API Task(s) Completed");

        return responseData;
    }

    private static String codeEditText(AssetManager assetManager, String prompt, String instruction) throws InterruptedException {
        AtomicReference<String> response = new AtomicReference<>();

        //noinspection CodeBlock2Expr
        Thread thread = new Thread(() -> {
            response.set(gptAPI(assetManager, prompt, instruction).choices.get(0).getText());
        });
        thread.start();
        thread.join();

        return response.get();
    }
}



package com.example.cora.userInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cora.R;
import com.example.cora.api.completions;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    private RecyclerView messageRV;
    private EditText chatEV;
    private ImageView sendIV;

    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private MessageAdapter messageAdapter;
    private ArrayList<Message> messageArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageRV = findViewById(R.id.chatRV);
        chatEV = findViewById(R.id.messageBox);
        sendIV = findViewById(R.id.sendIV);
        messageArrayList = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messageArrayList);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        messageRV.setLayoutManager(manager);
        messageRV.setAdapter(messageAdapter);

        sendIV.setOnClickListener(view -> {
            if(chatEV.getText().toString().isEmpty()) {
                Toast.makeText(Chat.this, "Please enter a message", Toast.LENGTH_LONG).show();
                return;
            }
            getResponse(chatEV.getText().toString());
            chatEV.setText("");
        });
    }

    private void getResponse(String message) {
        AssetManager assetManager = getAssets();
//        message = message.replaceAll("^[\n\n\r]", "");
//        Log.i("MESSAGE", "[MESSAGE] >> " + message);
        String completionType = "text";

        messageArrayList.add(new Message(message,USER_KEY));
        messageAdapter.notifyDataSetChanged();

        try {
            completions.addMsgArrResponse(messageArrayList, BOT_KEY, assetManager, message, completionType);
            messageAdapter.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package com.example.cora.userInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        sendIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chatEV.getText().toString().isEmpty()) {
                    Toast.makeText(Chat.this, "Please enter a message", Toast.LENGTH_LONG).show();
                    return;
                }
                getResponse(chatEV.getText().toString());
                chatEV.setText("");
            }
        });
    }

    private void getResponse(String message) {
        messageArrayList.add(new Message(message,USER_KEY));
        messageAdapter.notifyDataSetChanged();

        AssetManager assetManager = getAssets();

        String completionType = "text";
        TextView response = findViewById(R.id.receiveTxt);
        //Log.i("Chat", response.toString());
        response.setMovementMethod(new ScrollingMovementMethod());

        sendIV.setOnClickListener(view-> {
            try {
                completions.setCodeCompleteText(response,
                        assetManager,
                        message,
                        completionType);

                //messageArrayList.add(new Message());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
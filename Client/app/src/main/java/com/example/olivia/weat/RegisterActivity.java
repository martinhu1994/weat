package com.example.olivia.weat;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button register;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
                sendRegister();
           }
        });
    }

    public void sendRegister(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        new Thread(new RegisterRunnable()).start();
    }

    public class RegisterRunnable implements Runnable{
        @Override
        public void run(){
            String user = username.toString();
            String pass = password.toString();
            Map<String, String> newUser = new HashMap<>();
            newUser.put(user, pass);
            final boolean success = WebService.executeHTTPGetRegister(newUser);
            handler.post(new Runnable(){
                @Override
                public void run(){
                    if(success){
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getBaseContext(), "unsuccessfully register", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
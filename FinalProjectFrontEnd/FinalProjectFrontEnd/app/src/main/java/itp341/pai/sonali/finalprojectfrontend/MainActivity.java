package itp341.pai.sonali.finalprojectfrontend;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.net.URL;

import itp341.pai.sonali.finalprojectfrontend.model.GET_HTTP;
import itp341.pai.sonali.finalprojectfrontend.model.POST_HTTP;
import itp341.pai.sonali.finalprojectfrontend.model.User;

public class MainActivity extends AppCompatActivity {

    //declare widgets
    private EditText usernameInput;
    private EditText passwordInput;
    private Button signUpButton;
    private Button signInButton;

    //private data members
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Window window = this.getWindow();

        //change color of status bar
        window.setStatusBarColor(getResources().getColor(android.R.color.black));

        usernameInput = (EditText) findViewById(R.id.usernameInputField);
        passwordInput = (EditText) findViewById(R.id.passwordInputField);
        signInButton = (Button) findViewById(R.id.signInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        username = "";
        password = "";

        //listener for sign In button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                //TO DO: send these to data base to verify if user exists
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // get connection to the "SignIn" Servlet
                            URL url = new URL("http://localhost:8080/FinalProject/SignIn");
                            POST_HTTP post_http = new POST_HTTP(url);
                            post_http.addParameters("username", username);
                            post_http.addParameters("password", password);
                            post_http.connect();

                            // return user JSON as the response Text. If the user does not exist, or
                            //if the user's credentials are not correct - return an "ERROR" string


                            String userJson = post_http.getResponse();

                            if (userJson.equals("ERROR"))
                            {
                                //To-Do: show error messages



                            }
                            else
                            {
                                SharedPreferences.edit();


                            }

                            String storedHash = null;
                        }catch (Exception e)
                        {
                            Log.d("Exception", e.toString());
                    }
                    }
                });







                //if exists, launch an intent to the listview page of all toilets

            }
        });

        //listener for sign Up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                User user = new User(username, password);
                //send this user to database

                //launch an intent to the listview page of all toilets
            }
        });


    }
}

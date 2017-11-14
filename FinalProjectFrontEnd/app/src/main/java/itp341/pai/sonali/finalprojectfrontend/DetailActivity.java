package itp341.pai.sonali.finalprojectfrontend;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import itp341.pai.sonali.finalprojectfrontend.model.Comment;
import itp341.pai.sonali.finalprojectfrontend.model.GET_HTTP;
import itp341.pai.sonali.finalprojectfrontend.model.Toilet;

/**
 * Created by Sonali Pai on 11/10/2017.
 */

public class DetailActivity extends AppCompatActivity {

    private int bathroomId = -1;
    private TextView bathroomNameView;
    private TextView bathroomDescView;
    private ListView commentsView;
    private Toilet bathroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Window window = this.getWindow();

        //change color of status bar
        window.setStatusBarColor(getResources().getColor(android.R.color.black));

        Bundle extras = this.getIntent().getExtras();

        bathroomId = getIntent().getIntExtra("bathroomId", -1);

        try {
            URL url_getBathroom = new URL("http://localhost:8080/BathroomServlet?bathroomId=" + bathroomId);
            GET_HTTP getBathroomHTTP = new GET_HTTP(url_getBathroom);
            String bathroomJson = getBathroomHTTP.getResponse();
            Gson gson = new Gson();
            bathroom = gson.fromJson(bathroomJson, Toilet.class);
        } catch (MalformedURLException mue) {
            mue.getStackTrace();
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }

        bathroomNameView = (TextView) findViewById(R.id.toilet_name);
        bathroomNameView.setText(bathroom.getNameOfLocation());
       // bathroomDescView = (TextView) findViewById(R.id.toilet_description);
        bathroomNameView.setText(bathroom.getDescription());
        commentsView = (ListView) findViewById(R.id.comments);
        List<Comment> comments = bathroom.getComments();
        ArrayAdapter<Comment> commentsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, comments);
        commentsView.setAdapter(commentsAdapter);

    }
}

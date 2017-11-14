package itp341.pai.sonali.finalprojectfrontend;

/**
 * Created by Sonali Pai on 11/10/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.stream.Location;

import itp341.pai.sonali.finalprojectfrontend.model.GET_HTTP;
import itp341.pai.sonali.finalprojectfrontend.model.Toilet;
import sun.applet.Main;


public class ListActivity {
    //declare widgets
    private ListView toiletList;
    private Button addButton;
    private ArrayAdapter<Toilet> adapter;
    private Toilet toilet;
    private List<Toilet> toilets;
    static final int DETAIL_INTENT_CONSTANT = 0;
    static final int ADD_TOILET_INTENT_CONSTANT = 1;
    public ListActivity() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_toilet_list_fragment, container, false);
        final Context context = v.getContext();

        //find views
        addButton = (Button) v.findViewById(R.id.button_add);
        toiletList = (ListView)v.findViewById(R.id.toiletList);

        //get all toilets close to location and load into the adapter
        //Loction location = CURRENT LOCATION OF USER USING GOOGLE MAPS API
        Location location = null; //comment this line out
        try {
            URL url = new URL("http://localhost:8080/FinalProject/BathroomGPSServlet?location=" + location);
            GET_HTTP get_http = new GET_HTTP(url);
            String toiletList = get_http.getResponse();
            Gson gson = new Gson();
            toilets = (List<Toilet>) gson.fromJson(toiletList, Toilet.class);
            adapter = new ToiletListAdapter(v.getContext(), R.layout.activity_listtoilets, toilets);
        }
        catch(IOException ioe){
            System.out.println("ioe in list activity: " + ioe.getMessage());
        }
        toiletList.setAdapter(adapter);


        //toilet list item click listener
        toiletList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //get the clicked bathroom's ID and pass it over to the detail activity in an intent
                Intent detailIntent = new Intent(context, DetailActivity.class);
                long id = toilests.get(position).getBathroomId();
                detailIntent.putExtra("bathroomId", id);
                startActivityForResult(detailIntent, DETAIL_INTENT_CONSTANT);
            }
        });


        //add button listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addToiletIntent = new Intent(context, AddToiletActivity.class);
                startActivityForResult(addToiletIntent, ADD_TOILET_INTENT_CONSTANT);
            }
        });

        return v;
    }


    private class ToiletListAdapter extends ArrayAdapter<Toilet> {
        int pos = 0;

        //default constructor
        public ToiletListAdapter(Context c, int resId, List<Toilet> movies) {
            super(c, resId, movies);
        }

        //getview generates A SINGLE ROW
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //step 1 -- inflate view (row) if necessary
            if (convertView == null) {      //this means row has NEVER been created
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.activity_toilet_list_fragment,
                        null
                );
            }
            //step 2 -- get references to XML views in the row
            TextView toiletName = (TextView) convertView.findViewById(R.id.toilet_name);
            ImageView toiletImage = (ImageView) convertView.findViewById(R.id.imageView);

            //step 3 -- get the new itp341.pai.sonali.a9.model data
            toilet = getItem(position);      //getting the specified movie FROM the adapter

            //step 4 -- load the data from the itp341.pai.sonali.a9.model to the view
            toiletName.setText(toilet.getNameOfLocation());
            //SET THE IMAGE TO AN IMAGE FROM GOOGLE MAPS
            //toiletImage.setImageResource(IMAGE RESOURCE);

            return convertView;
        }
    }

    //refresh method
    public void refresh(){
        adapter.notifyDataSetChanged();
    }

}























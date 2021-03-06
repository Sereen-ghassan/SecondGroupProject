package com.example.secondgroupproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Locality> subjectsList;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.Adapter adapter;
    private Button location ;

    private String HTTP_JSON_URL = "http://192.168.1.191:81/localityDB.php";

    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        subjectsList = new ArrayList<Locality>();
        recyclerView = findViewById(R.id.recyclerView1);
        location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, findLocation.class);
                MainActivity.context.startActivity(intent);
            }
        });

        JSON_DATA_WEB_CALL();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new RecyclerViewCardViewAdapter(getApplicationContext(), subjectsList);
        recyclerView.setAdapter(adapter);

    }

    public void JSON_DATA_WEB_CALL() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(HTTP_JSON_URL,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject localitiesJsonObject = response.getJSONObject(i);
                        Locality localitiesObjects = new Locality();
                        localitiesObjects.setPrice(localitiesJsonObject.getString("price"));
                        localitiesObjects.setLocalityType(localitiesJsonObject.getString("localityType"));
                        localitiesObjects.setSurface(localitiesJsonObject.getString("surface"));
                        localitiesObjects.setImageID(localitiesJsonObject.getString("imageID"));
                        localitiesObjects.setRooms(localitiesJsonObject.getString("rooms"));
                        localitiesObjects.setDescription(localitiesJsonObject.getString("description"));
                        localitiesObjects.setLocation(localitiesJsonObject.getString("location"));
                        subjectsList.add(localitiesObjects);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}

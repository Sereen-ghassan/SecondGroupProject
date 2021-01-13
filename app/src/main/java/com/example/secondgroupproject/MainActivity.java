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
    static ArrayList<Car> subjectsList;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.Adapter adapter;
    private Button location ;

    private String HTTP_JSON_URL = "http://192.168.1.191:81/carDB.php";

    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        subjectsList = new ArrayList<Car>();
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
                        JSONObject carsJsonObject = response.getJSONObject(i);
                        Car carsObjects = new Car();
                        carsObjects.setNameCar(carsJsonObject.getString("name"));
                        carsObjects.setColor(carsJsonObject.getString("color"));
                        carsObjects.setManufactorer(carsJsonObject.getString("Manufactorer"));
                        carsObjects.setImageID(carsJsonObject.getString("imageID"));
                        carsObjects.setYear(carsJsonObject.getString("Year"));
                        carsObjects.setId(Integer.parseInt(carsJsonObject.getString("id")));
                        carsObjects.setLocation(carsJsonObject.getString("location"));
                        subjectsList.add(carsObjects);
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

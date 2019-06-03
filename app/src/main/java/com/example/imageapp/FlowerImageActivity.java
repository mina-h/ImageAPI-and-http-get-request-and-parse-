package com.example.imageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlowerImageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mImageAdapter;
    private ArrayList<ImageItem> mImageList; //to save json data in array list
    private RequestQueue mRequestQueue; //request queue we need for volly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_image);

        mRecyclerView = findViewById(R.id.recycler_view_flower);
        mRecyclerView.setHasFixedSize(true );
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this) ); //items in linear way (vertical)

        mImageList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }
    private void parseJSON(){
        String url = "https://pixabay.com/api/?key=12623977-e13d4681b6bedcc2c963961cc&q=flower&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject hit =jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int likes = hit.getInt("likes");

                                mImageList.add(new ImageItem(imageUrl, creatorName, likes));
                            }

                            mImageAdapter = new ImageAdapter(FlowerImageActivity.this, mImageList);
                            mRecyclerView.setAdapter(mImageAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }
}

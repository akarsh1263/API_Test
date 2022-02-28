package application.example.api_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{
    ArrayList<String> mvs;
    Request request;
    Response response;
    JSONObject jo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void work(View view) {
        Button t = findViewById(view.getId());
        String s = t.getText().toString();
        mvs = new ArrayList<String>();
        mvs.add(s);

        OkHttpClient client = new OkHttpClient();
        request = new Request.Builder()
                    .url("https://data-imdb1.p.rapidapi.com/movie/byGen/" + s + "/?page_size=50")
                    .get()
                    .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "bb52f42a46msh7ef18ec6b02c03bp1b7f06jsnaa281212f91e")
                    .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast toast = Toast.makeText(MainActivity.this,"error occured "+e.getMessage().toString(), Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = "";

                    res = response.body().string();

                    try {
                        jo = new JSONObject(res);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray jar = null;
                    try {
                        jar = jo.getJSONArray("results");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    int n=jar.length();
                    for (int i = 0; i < n; i++) {
                        JSONObject jor = null;
                        try {
                            jor = jar.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            mvs.add(jor.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                            Intent intent = new Intent(MainActivity.this, Test.class);
                            intent.putStringArrayListExtra("mvs", mvs);
                            startActivity(intent);
                }
            }
        });
    }
    }
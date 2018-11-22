package com.shia.atm7;

/*import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TransActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        Request request = new Request.Builder()
                .url("http://atm201605.appspot.com/h")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d("OKHTTP", json);
//                Toast.makeText(TransActivity.this,json,Toast.LENGTH_LONG).show();
                //解析JSON
//                parseJSON(json);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                //告知使用者連線失敗
            }
        });
//        new TransTask().execute("http://atm201605.appspot.com/h");
    }
}*/

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class TransActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);


        Request request = new Request.Builder().url("http://atm201605.appspot.com/h").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d("json",json);
                parsJSON(json);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    void setRecycleView(List list){
        RecyclerView recyclerView = findViewById(R.id.recycle);
        TransactionAdapter adapter =new TransactionAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void parsJSON(String s){
        final ArrayList trans = new ArrayList();
        try {
            JSONArray array = new JSONArray(s);
            for (int i=0;i<array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                String account = obj.getString("account");
                String date = obj.getString("date");
                int amount =obj.getInt("amount");
                int type = obj.getInt("type");
                Log.d("json",account+"/"+date+"/"+amount+"/"+type);
                Transaction t = new Transaction(account,date,amount,type);
                trans.add(t);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setRecycleView(trans);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}


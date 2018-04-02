package com.example.andresim.jsonprueba;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private List<String> lista = new ArrayList<>();
    private ListView lvCiudades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCiudades=(ListView) findViewById(R.id.lvCiudades);
        findViewById(R.id.okButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TareaAsincrona tareaAsincrona = new TareaAsincrona();
                tareaAsincrona.execute();
            }
        });
    }

    private class TareaAsincrona extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            String lista ="[{'ciudad':'Cartagena','pais':'España'},{'ciudad':'Madrid','pais':'España'},{'ciudad':'Sevilla','pais':'España'}]";
            JSONObject json=new JSONObject();
            try {
                JSONArray array = new JSONArray(lista);
                for (int i=0; i<array.length();i++) {
                    json.put("array"+i,array.get(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                for (int i=0; i<jsonObject.length();i++) {
                    lista.add(jsonObject.getJSONObject("array"+i).getString("ciudad"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ArrayAdapter<String> adp=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,lista);
            lvCiudades.setAdapter(adp);

        }
    }
}

package com.example.volley_insertar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.BlockingDeque;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Rey";
    private EditText etcodigo,etdescripcion,etprecio;
    private RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etcodigo=findViewById(R.id.etcodigo);
        etdescripcion=findViewById(R.id.etdescripcion);
        etprecio=findViewById(R.id.etprecio);
        rq= Volley.newRequestQueue(this);

    }

    public void notificar(View view){
        Log.d(TAG, "notificar: ");
    }
    
    public void agregar(View view){
        //    {"codigo":"999","descripcion":"capa","precio":"999"}
        String url="https://datamoviles.tk/volley/insertar3.php";
        JSONObject parametros = new JSONObject();
        try {
            parametros.put("codigo",etcodigo.getText().toString());
            parametros.put("descripcion",etdescripcion.getText().toString());
            parametros.put("precio",etprecio.getText().toString());
        }catch (Exception e){e.printStackTrace();}
        Log.d(TAG, "JSON: "+parametros);
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.POST,
                url,
                parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("respuesta").toString().equals("ok")){
                                Toast.makeText(MainActivity.this,"Datos agregados ok",Toast.LENGTH_SHORT).show();
                                etcodigo.setText("");
                                etdescripcion.setText("");
                                etprecio.setText("");
                            }else Toast.makeText(MainActivity.this,"Rey Error:"+response.get("respuesta").toString(),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Problemas de insertar en el servidor: "+ error.toString(),Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorResponse: "+error.toString());
            }
        });
    rq.add(requerimiento);
    }
}
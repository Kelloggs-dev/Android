package src;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.f1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import src.classe.C_CODE;
import src.classe.C_USER_CONNECTER;
import src.classe.URLAPI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnRacing = (ImageView) findViewById(R.id.Racing);

        btnRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Racing.class);
                startActivity(intent);
            }
        });

        ImageView btnDrivers = (ImageView) findViewById(R.id.Standings);

        btnDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Drivers.class);
                startActivity(intent);
            }
        });

        ImageView btnCompte = (ImageView) findViewById(R.id.Compte);

        btnCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C_USER_CONNECTER User = C_USER_CONNECTER.getInstance();
                Intent intent;

                if(!User.getConnecter()) {
                    intent = new Intent(MainActivity.this, Compte.class);

                    startActivity(intent);
                }else{

                    DecodeJeton();

                }


            }
        });
    }

    private void DecodeJeton(){
        C_USER_CONNECTER User = C_USER_CONNECTER.getInstance();
        final String Jeton = User.getJeton();
        Log.i("e","1");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLAPI.DECODEJETON,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String reponse) {
                        Log.i("e","2");
                        try {
                            Log.i("e","3");
                            JSONObject jsonObject = new JSONObject(reponse);
                            String ReponseData = jsonObject.getString("Code");
                            C_CODE Code = new C_CODE();
                            Code.setCode(ReponseData);
                            String Results = Code.getCode();
                            Log.i("e","4");

                            if(Results.equals("200")){


                                ReponseData = jsonObject.getString("Jeton");
                                User.setJeton(ReponseData);


                                ReponseData = jsonObject.getString("Id_Contrat");
                                User.setId_Contrat(ReponseData);


                                if(User.getId_Contrat().equals("500")){
                                    Intent intent = new Intent(MainActivity.this,Compte_Admin.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(MainActivity.this,Compte_Connecter.class);
                                    startActivity(intent);
                                }



                            }else {
                                String ReponseMessage = jsonObject.getString("Message");
                                Log.i("e","6");
                                Toast.makeText(getApplicationContext(), ReponseMessage, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Log.i("e","8");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("e","7");
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Jeton", Jeton);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}
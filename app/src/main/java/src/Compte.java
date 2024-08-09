package src;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.f1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import src.classe.C_CODE;
import src.classe.C_RACING;
import src.classe.C_USER_CONNECTER;
import src.classe.RacingAdapter;
import src.classe.URLAPI;

public class Compte extends AppCompatActivity {

    EditText MAIL;
    EditText PASSWORD;
    Button CONNEXION;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnReour = (ImageView) findViewById(R.id.retour);

        btnReour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compte.this, MainActivity.class);
                startActivity(intent);
            }
        });

        MAIL = (EditText)findViewById(R.id.Email);
        PASSWORD = (EditText)findViewById(R.id.Password);
        CONNEXION = (Button)findViewById(R.id.btn_login);

        Button btnConnexion = (Button) findViewById(R.id.btn_login);

        btnConnexion.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                    Connexion();
            }
            
        });

    }

    private void Connexion(){

        final String Mail = MAIL.getText().toString().trim();
        final String Mdp = PASSWORD.getText().toString().trim();
        Log.i("e","1");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLAPI.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String reponse) {
                        Log.i("e","2");
                        try {
                            Log.i("e","3");
                            JSONObject jsonObject = new JSONObject(reponse);
                            String ReponseData = jsonObject.getString("Code");
                            String ReponseMessage = jsonObject.getString("Message");
                            C_CODE Code = new C_CODE();
                            Code.setCode(ReponseData);
                            String Results = Code.getCode();
                            Log.i("e","4");

                            if(Results.equals("200")){
                                Log.i("e","10");
                                JSONObject Data = jsonObject.getJSONObject("table");
                                C_USER_CONNECTER User = C_USER_CONNECTER.getInstance();
                                User.setNom(Data.getString("Nom"));
                                User.setPrenom(Data.getString("Prenom"));
                                User.setJeton(Data.getString("Jeton"));
                                User.setConnecter(true);
                                Log.i("e",User.getConnecter().toString());

                                Intent intent = new Intent(Compte.this, MainActivity.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), ReponseMessage, Toast.LENGTH_SHORT).show();

                            }else {
                                Log.i("e","6");
                                Toast.makeText(getApplicationContext(), ReponseMessage, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("e","8");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.getMessage();
                        Log.i("e","7");
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Login", Mail);
                params.put("Password", Mdp);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
package src;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.classe.C_CODE;
import src.classe.C_DRIVER;
import src.classe.C_USER;
import src.classe.C_USER_CONNECTER;
import src.classe.DriverAdapter;
import src.classe.URLAPI;
import src.classe.UserAdapter;

public class Delete extends AppCompatActivity {

    C_USER User = new C_USER();
    C_USER_CONNECTER User_Co = C_USER_CONNECTER.getInstance();

    private ListView List_User_;
    private UserAdapter adapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List_User_ = findViewById(R.id.list_user);
        adapter_ = new UserAdapter(this, R.layout.list_item_user, new ArrayList<C_USER>());
        List_User_.setAdapter(adapter_);

        Affiche_List();



        ImageView btnReour = (ImageView) findViewById(R.id.retour3);

        btnReour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delete.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnDelete = (Button) findViewById(R.id.Valide_Delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Supprime();

            }
        });


        List_User_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                C_USER User8 = (C_USER) adapter_.getItem(position);
                User.setId(User8.getId());
                Toast.makeText(getApplicationContext(),String.valueOf(User.getId()),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Affiche_List(){


        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest arrayRequest = new JsonObjectRequest(

                Request.Method.GET,
                URLAPI.LIST_TABLE_PERSONNE,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject reponse) {

                        try {

                            JSONArray Data = reponse.getJSONArray("table");
                            String ObjectReponse = reponse.getString("code");
                            C_CODE code = new C_CODE();
                            code.setCode(ObjectReponse);
                            String Results = code.getCode();

                            if(Results.equals("200")) {
                                for (int count = 0; count < Data.length(); count++) {
                                    JSONObject Object = new JSONObject(Data.getString(count));
                                    C_USER User_ = new C_USER();
                                    User_.setId(Object.getInt("Id"));
                                    User_.setNom(Object.getString("Nom"));
                                    User_.setPrenom(Object.getString("Prenom"));


                                    adapter_.add(User_);
                                }
                            }else {
                                String Message = "Une erreur est survenue. Veuillez réessayer.";
                                Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
                                Log.i("Erreur",Message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.getMessage();
                    }
                }

        );
        requestQueue.add(arrayRequest);
        List_User_.setAdapter(adapter_);


    }
    public void Supprime(){
        Log.i("e","1");

        int Id = getIntent().getIntExtra("idPersonne",User.getId());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLAPI.DELETE_ACCOUNTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String reponse) {
                        Log.i("e","2");
                        try {

                            JSONObject jsonObject = new JSONObject(reponse);
                            String ReponseData = jsonObject.getString("Code");
                            String ReponseMessage = jsonObject.getString("Message");
                            C_CODE Code = new C_CODE();
                            Code.setCode(ReponseData);
                            String Results = Code.getCode();

                            if(Results.equals("200")) {
                                adapter_.clear();
                                Affiche_List();
                                Toast.makeText(getApplicationContext(),ReponseMessage,Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(),ReponseMessage,Toast.LENGTH_SHORT).show();
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
                params.put("Jeton", User_Co.getJeton());
                params.put("idPersonne",String.valueOf(User.getId()));

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
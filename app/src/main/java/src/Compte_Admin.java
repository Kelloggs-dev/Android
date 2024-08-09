package src;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.f1.R;

import src.classe.C_USER_CONNECTER;

public class Compte_Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compte_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnReour = (ImageView) findViewById(R.id.retour2);

        btnReour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compte_Admin.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnDeconnexion = (Button) findViewById(R.id.Deconnexion);

        btnDeconnexion.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Deconnexion();
            }

        });

        Button btnTicket = (Button) findViewById(R.id.Info_Ticket);

        btnTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btnDelete =  (Button) findViewById(R.id.Delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compte_Admin.this, Delete.class);
                startActivity(intent);
            }
        });
    }

    private void Deconnexion() {
        C_USER_CONNECTER User = C_USER_CONNECTER.getInstance();
        User.setNom(null);
        User.setPrenom(null);
        User.setJeton(null);
        User.setConnecter(false);

        Intent intent = new Intent(Compte_Admin.this, MainActivity.class);
        startActivity(intent);
    }
}
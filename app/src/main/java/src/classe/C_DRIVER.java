package src.classe;

import android.icu.lang.UProperty;

import java.util.Properties;

public class C_DRIVER {

    private String Nom;
    private String Prenom;
    private String pointChampionat;
    private String nomConstructor;

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getpointChampionat() {
        return pointChampionat;
    }

    public String getnomConstructor() {
        return nomConstructor;
    }

    public void setNom(String _nom) {
        Nom = _nom;
    }

    public void setPrenom(String _Prenom) {
        Prenom = _Prenom;
    }

    public void setpointChampionat(String _pointChampionat) {
        pointChampionat = _pointChampionat;
    }

    public void setnomConstructor(String _nomConstructor) {
        nomConstructor = _nomConstructor;
    }
}

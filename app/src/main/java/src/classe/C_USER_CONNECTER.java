package src.classe;

public class C_USER_CONNECTER {

    private static C_USER_CONNECTER _Instance = null;
    public static C_USER_CONNECTER getInstance() {
        if (_Instance == null) {
            _Instance = new C_USER_CONNECTER();
        }
        return _Instance;
    }
    private C_USER_CONNECTER() {

    }

    private String Nom;
    private String Prenom;
    private String Jeton;
    private String Id_Contrat;
    private Boolean Connecter = false;

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getJeton() {
        return Jeton;
    }
    public String getId_Contrat() {
        return Id_Contrat;
    }
    public Boolean getConnecter() {
        return Connecter;
    }


    public void setNom(String _nom) {
        Nom = _nom;
    }

    public void setPrenom(String _Prenom) {
        Prenom = _Prenom;
    }

    public void setJeton(String _Jeton) {
        Jeton = _Jeton;
    }
    public void setId_Contrat(String _Id_Contrat) {
        Id_Contrat = _Id_Contrat;
    }
    public void setConnecter(Boolean _Connecter) {
        Connecter = _Connecter;
    }

}

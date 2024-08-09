package src.classe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.f1.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter {

    private int resource;
    private List<C_USER> users;
    public UserAdapter(@NonNull Context context, int resource, @NonNull List<C_USER> users) {
        super(context, resource, users);
        this.resource = resource;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;

        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.list_item_user, parent, false);
        }

        C_USER User = (C_USER) getItem(position);

        // Assigner aux textView les valeurs obtenues par les get de la classe Film
        TextView Id = (TextView) result.findViewById(R.id.Id);
        Id.setText(String.valueOf(User.getId()));

        TextView Nom = (TextView) result.findViewById(R.id.Nom);
        Nom.setText(String.valueOf(User.getNom()));

        TextView Prenom = (TextView) result.findViewById(R.id.Prenom);
        Prenom.setText(String.valueOf(User.getPrenom()));




        return result;
    }
}

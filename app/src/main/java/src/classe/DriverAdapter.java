package src.classe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.f1.R;

public class DriverAdapter extends ArrayAdapter {

    public DriverAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;

        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.list_item_driver, parent, false);
        }

        C_DRIVER Driver = (C_DRIVER) getItem(position);

        // Assigner aux textView les valeurs obtenues par les get de la classe Film
        TextView Nom = (TextView) result.findViewById(R.id.Nom);
        Nom.setText(String.valueOf(Driver.getNom()));

        TextView Prenom = (TextView) result.findViewById(R.id.Prenom);
        Prenom.setText(String.valueOf(Driver.getPrenom()));

        TextView pointChampionat = (TextView) result.findViewById(R.id.pointChampionat);
        pointChampionat.setText(String.valueOf(Driver.getpointChampionat()));

        TextView nomConstructor = (TextView) result.findViewById(R.id.nomConstructor);
        nomConstructor.setText(String.valueOf(Driver.getnomConstructor()));




        return result;
    }
}


package src.classe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.f1.R;

public class RacingAdapter extends ArrayAdapter {

    public RacingAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;

        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.list_item_racing, parent, false);
        }

        C_RACING Racing = (C_RACING) getItem(position);

        // Assigner aux textView les valeurs obtenues par les get de la classe Film
        TextView Date = (TextView) result.findViewById(R.id.dateRacing);
        Date.setText(String.valueOf(Racing.getDateRacing()));

        TextView Lieu = (TextView) result.findViewById(R.id.Lieu);
        Lieu.setText(String.valueOf(Racing.getLieu()));




        return result;
    }
}

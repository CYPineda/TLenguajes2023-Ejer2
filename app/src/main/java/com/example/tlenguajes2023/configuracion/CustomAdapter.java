package com.example.tlenguajes2023.configuracion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tlenguajes2023.ActivityIngresar;
import com.example.tlenguajes2023.Activitydetalle;
import com.example.tlenguajes2023.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<personas> implements View.OnClickListener
{
    private List<personas> DataSet;
    private Context context;
    private int layoutResourceId;

    private static class ViewHolder
    {
        TextView txtnombres;
        TextView txtdescripcion;
        ImageView imageperson;
        LinearLayout linear;
    }

    public CustomAdapter(List<personas> data, @NonNull Context context, int resource) {
       // super(context, R.layout.row_personas);
        super(context, resource, data);
        this.DataSet = data;
        this.context = context;
        this.layoutResourceId = resource;
    }

    @Override
    public void onClick(View view) {

    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        personas dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_personas, parent, false); // Usa el id del recurso del diseño proporcionado
           // convertView = inflater.inflate(layoutResourceId, parent, false); // Usa el id del recurso del diseño proporcionado
           //convertView = inflater.inflate(R.layout.row_personas, parent, false);
            viewHolder.linear = convertView.findViewById(R.id.linear);
            viewHolder.txtnombres = convertView.findViewById(R.id.PersonName);
            viewHolder.txtdescripcion =  convertView.findViewById(R.id.PersonDescripcion);
            viewHolder.imageperson =  convertView.findViewById(R.id.ImagePerson);
            viewHolder.linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent nuevo = new Intent(context, Activitydetalle.class);
                    nuevo.putExtra("id",""+dataModel.getId());
                    nuevo.putExtra("nombre",""+dataModel.getNombres());
                    nuevo.putExtra("foto",""+dataModel.getFoto());
                    nuevo.putExtra("descripcion",""+dataModel.getDescripcion());
                    context.startActivity(nuevo);
                   // Toast.makeText(context, "Id: "+dataModel.getId()+" Nombre "+dataModel.getNombres()+" Apellido: "+dataModel.getDescripcion(), Toast.LENGTH_LONG).show();
                }
            });
           /* viewHolder.txtnombres = (TextView) convertView.findViewById(R.id.PersonName);
            viewHolder.txtapellidos = (TextView) convertView.findViewById(R.id.PersonApellido);
            viewHolder.imageperson = (ImageView) convertView.findViewById(R.id.ImagePerson);*/
         //   result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
         //   result=convertView;
        }

        viewHolder.txtnombres.setText(dataModel.getNombres());
        viewHolder.txtdescripcion.setText(dataModel.getDescripcion());
        byte[] decodedString = Base64.decode(dataModel.getFoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        viewHolder.imageperson.setImageBitmap(decodedByte);
        return convertView;
    }


    private Bitmap ConvertBase64toImage(String Base64String) {
        if (Base64String == null || Base64String.isEmpty()) {
            return null;
        }
        String[] parts = Base64String.split(",");
        if (parts.length < 2) {
            return null;
        }
        String base64Image = parts[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

}

package com.example.tlenguajes2023;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tlenguajes2023.configuracion.ConfigDB;
import com.example.tlenguajes2023.configuracion.SQLiteConnection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Activitydetalle extends AppCompatActivity {

    ImageView fotoview;
    EditText id, nombres, descripcion, foto;
    Button btningresar, btnfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        id = (EditText) findViewById(R.id.txtid);
        nombres = (EditText) findViewById(R.id.txtnomb);
        descripcion = (EditText) findViewById(R.id.txtdescrip);
        foto=(EditText) findViewById(R.id.txtfoto);
        fotoview = (ImageView) findViewById(R.id.imageView);

        Bundle param = this.getIntent().getExtras();
        if(param != null){
            String bids = String.valueOf(param.getString("id"));
            String bfoto = String.valueOf(param.getString("foto"));
            String bnombre = String.valueOf(param.getString("nombre"));
            String bdescripcion = String.valueOf(param.getString("descripcion"));

            id.setText(""+bids);
            nombres.setText(bnombre);
            descripcion.setText(bdescripcion);
            foto.setText(bfoto);
            byte[] decodedString = Base64.decode(bfoto, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            fotoview.setImageBitmap(decodedByte);


        }

    }

}
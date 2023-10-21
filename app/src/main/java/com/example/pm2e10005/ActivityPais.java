package com.example.pm2e10005;

import com.example.pm2e10005.configuracion.SQLiteConexion1;
import com.example.pm2e10005.configuracion.TransaccionesPais;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityPais extends AppCompatActivity {

    EditText nombrePais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);
        Button btnGuardar=(Button) findViewById(R.id.btnSalvarPais);
        Button btnAtras2=(Button) findViewById(R.id.btnAtras2);

        nombrePais=(EditText) findViewById(R.id.txtNombrePais);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarPais();

            }
        });
        btnAtras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }



    private void agregarPais() {
        SQLiteConexion1 conexion= new SQLiteConexion1(this, TransaccionesPais.NameDatabase, null, 1);
        SQLiteDatabase db= conexion.getWritableDatabase();

        ContentValues valores =new ContentValues();
        valores.put(TransaccionesPais.nombrePais, nombrePais.getText().toString());


        Long resultado= db.insert(TransaccionesPais.tablaPais, TransaccionesPais.id, valores);

        Toast.makeText(getApplicationContext(),"Registro ingresado : codigo : "+ resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        limpiarPantalla();
    }


    private void limpiarPantalla() {
        nombrePais.setText("");
        //area.setText("");
    }
}
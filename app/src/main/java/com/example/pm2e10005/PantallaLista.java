package com.example.pm2e10005;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.pm2e10005.configuracion.SQLiteConexion;
import com.example.pm2e10005.configuracion.Transacciones;
import com.example.pm2e10005.tablas.Contacto;

public class PantallaLista extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView listausuarios;
    ArrayList<Contacto> lista;
    ArrayList<String> ArregloUsuarios;
    EditText pasar, id,nombre, telefono,nota, buscar, txtSeleccion, txtSeleccion1, txtSeleccion2;
    Spinner pais;

    public String global = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_lista);

        Button btnatras = (Button)findViewById(R.id.btnatras);
        Button btneliminar = (Button)findViewById(R.id.btneliminar);
        Button btnactualizar = (Button)findViewById(R.id.btnactualizar);
        Button btnllamar = (Button)findViewById(R.id.btnllamar);
        //nombre=(EditText) findViewById(R.id.txtbusqueda);


        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        listausuarios = (ListView)findViewById(R.id.listausuarios);

        ObtenerListaUsuarios();

        buscar = (EditText)findViewById(R.id.txtbusqueda);
        txtSeleccion=(EditText) findViewById(R.id.txtIdC);
        txtSeleccion1=(EditText) findViewById(R.id.txtNombreC);
        txtSeleccion2=(EditText) findViewById(R.id.txtTelefonoC);
        nombre=(EditText) findViewById(R.id.txtNpais);
        nota=(EditText) findViewById(R.id.txtNotaA);

        listausuarios = (ListView)findViewById(R.id.listausuarios);

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloUsuarios);
        listausuarios.setAdapter(adp);

        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adp.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listausuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txtSeleccion.setText(lista.get(i).getId().toString());
                txtSeleccion1.setText(lista.get(i).getNombres());
               txtSeleccion2.setText(lista.get(i).getTelefono());
               nombre.setText(lista.get(i).getPais());
               nota.setText(lista.get(i).getNota());


            }
        });



        btnllamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();


            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();


            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();

            }
        });


        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void ObtenerListaUsuarios() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        Contacto listUsuarios = null;
        lista = new ArrayList<Contacto>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaContacto, null);
        while (cursor.moveToNext()){
            listUsuarios = new Contacto();
            listUsuarios.setId(cursor.getInt(0));
            listUsuarios.setPais(cursor.getString(1));
            listUsuarios.setNombres(cursor.getString(2));
            listUsuarios.setTelefono(cursor.getString(3));
            listUsuarios.setNota(cursor.getString(4));


            lista.add(listUsuarios);
        }
        cursor.close();
        fillList();
    }

    private void fillList() {
        ArregloUsuarios = new ArrayList<String>();
        for (int i = 0; i<lista.size(); i++){
            ArregloUsuarios.add(lista.get(i).getId() +" | "+
                    lista.get(i).getPais() +" | "+
                    lista.get(i).getNombres() +" | "+
                    lista.get(i).getTelefono()+" | "+
                    lista.get(i).getNota());
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setTitle("Accion");
        myBuild.setMessage("Desea realizar la llamada?");
        myBuild.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pasar = (EditText) findViewById(R.id.txtTelefonoC);
                Intent intent = new Intent(getApplicationContext(), ActivityLlamada.class);
                intent.putExtra("dato",pasar.getText().toString());
                startActivity(intent);
            }
        });
        myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();
    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {txtSeleccion.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablaContacto, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato Eliminado", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {txtSeleccion.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txtSeleccion1.getText().toString());
        valores.put(Transacciones.telefono, txtSeleccion2.getText().toString());
        valores.put(Transacciones.pais, nombre.getText().toString());
        valores.put(Transacciones.nota, nota.getText().toString());

        db.update(Transacciones.tablaContacto, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_LONG).show();
        ClearScreen();

    }

    private void ClearScreen() {
        nombre.setText("");
        txtSeleccion.setText("");
        txtSeleccion1.setText("");
        txtSeleccion2.setText("");
        nota.setText("");

    }

}
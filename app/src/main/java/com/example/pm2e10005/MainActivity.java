package com.example.pm2e10005;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.pm2e10005.configuracion.SQLiteConexion;
import com.example.pm2e10005.configuracion.SQLiteConexion1;
import com.example.pm2e10005.configuracion.Transacciones;
import com.example.pm2e10005.configuracion.TransaccionesPais;
import com.example.pm2e10005.tablas.pais;


public class MainActivity extends AppCompatActivity {

    EditText nombres, telefono, nota;
    //Spinner pais;

    SQLiteConexion1 conexion;
    Spinner comboContacto;

    ArrayList <String> listaContactos;
    ArrayList <pais> lista;

    //private Spinner comboBox;
    //private String[] arregloComboBox;
    //private AdaptarCombox adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnIngresar=(Button) findViewById(R.id.btnSalvarPais);
        ImageButton btnAgregar=(ImageButton) findViewById(R.id.btnAgregar);
        Button btnContacto=(Button) findViewById(R.id.btnContacto);

       conexion =new SQLiteConexion1(this, TransaccionesPais.NameDatabase, null, 1);
        comboContacto=(Spinner) findViewById(R.id.combobox);



       //pais = (Spinner) findViewById(R.id.combobox);
        nombres=(EditText) findViewById(R.id.txtNombrePais);
        telefono=(EditText) findViewById(R.id.txtArea);
        nota=(EditText) findViewById(R.id.txtNombrePais);
        ObtenerListaPais();

        ArrayAdapter <CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaContactos );
        comboContacto.setAdapter(adp);



        //comboBox = findViewById(R.id.combobox);
        //arregloComboBox= new String[] {"Guatemala (502)","El salvador (503)", "Honduras (504)", "Nicaragua (505)", "Costa Rica (506)"};
        //adaptador =new AdaptarCombox(this, arregloComboBox);
        //comboBox.setAdapter(adaptador);





        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityPais.class);
                startActivity(intent);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarContacto();
            }
        });

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PantallaLista.class);
                startActivity(intent);
            }
        });

    }


        private void ObtenerListaPais(){
            SQLiteDatabase db =conexion.getReadableDatabase();
            pais lista_contactos=null;
            lista =new ArrayList<pais>();

            Cursor cursor=db.rawQuery("SELECT * FROM " + TransaccionesPais.tablaPais, null);

            while (cursor.moveToNext()){
                lista_contactos=new pais();
                lista_contactos.setNombrePais(cursor.getString(1));
                //lista_contactos.setArea(cursor.getString(2));

                lista.add(lista_contactos);


            }
            cursor.close();
            fillCombo();
        }
        private void fillCombo(){
            listaContactos =new ArrayList<String>();
            for(int i=0; i<lista.size(); i++){
                listaContactos.add(lista.get(i).getNombrePais()
                        );

            }
        }



    private void AgregarContacto() {
        SQLiteConexion conexion= new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db= conexion.getWritableDatabase();

        ContentValues valores =new ContentValues();
        valores.put(Transacciones.pais, comboContacto.getSelectedItem().toString());
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.telefono, telefono.getText().toString());
        valores.put(Transacciones.nota, nota.getText().toString());

        if(validar()==true){
            Long resultado= db.insert(Transacciones.tablaContacto, Transacciones.nombres, valores);

            Toast.makeText(getApplicationContext(),"Registro ingresado : codigo : "+ resultado.toString(),Toast.LENGTH_LONG).show();

            db.close();

            limpiarPantalla();
        }

    }

    public boolean validar() {
        boolean retorno = true;

        String nom = nombres.getText().toString();
        String tel = telefono.getText().toString();
        String nt = nota.getText().toString();

        if (nom.isEmpty() || tel.isEmpty() || nt.isEmpty()) {
            nombres.setError("Ingrese su nombre");
            retorno = false;
        } if (tel.isEmpty()) {
            telefono.setError("Un numero telefonico Valido");
            retorno = false;
        } if (nt.isEmpty()) {
            nota.setError("Ingrese una nombre");
            retorno = false;
        }
        return retorno;
    }

    private void limpiarPantalla() {
        nombres.setText("");
        telefono.setText("");
        nota.setText("");
    }
}
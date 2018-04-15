package com.drunker.drunkers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class comida extends AppCompatActivity {

    RecyclerView listdatos;
    ArrayList<datoscomida>lista;
    adaptadorcomida adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida);
        listdatos = (RecyclerView)findViewById(R.id.lstdatos);
        LinearLayoutManager linearlayoutmanager= new LinearLayoutManager(this);
        linearlayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        listdatos.setLayoutManager(linearlayoutmanager);
        cargardatos();
        adaptador = new adaptadorcomida(this,lista);
        listdatos.setAdapter(adaptador);


        }
        public void cargardatos (){
            lista = new ArrayList<datoscomida>();

            lista.add(new datoscomida(1,"Papas BBQ",2000,R.drawable.bbq ));
            lista.add(new datoscomida(2,"Margarita Clasica",1800,R.drawable.clasica ));
            lista.add(new datoscomida(3,"Crokantes",2300,R.drawable.bbq ));
            lista.add(new datoscomida(4,"Margarita Limon",2000,R.drawable.bbq ));
            lista.add(new datoscomida(5,"Minichps",2000,R.drawable.bbq ));
            lista.add(new datoscomida(6,"De todido mix",2600,R.drawable.bbq ));
            lista.add(new datoscomida(7,"Margarita Natural",2000,R.drawable.bbq ));
            lista.add(new datoscomida(8,"Perro Caliente",9000,R.drawable.bbq ));
            lista.add(new datoscomida(9,"Picada DRUNKER",15000,R.drawable.bbq ));
            lista.add(new datoscomida(10,"Pizza DRUNKER",6000,R.drawable.bbq ));
            lista.add(new datoscomida(11,"Margarita Pollo",2000,R.drawable.bbq ));
            lista.add(new datoscomida(12,"Pringles",5000,R.drawable.bbq ));
            lista.add(new datoscomida(13,"Salchipapas",9000,R.drawable.bbq ));
            lista.add(new datoscomida(14,"Todo Rico BBQ",2500,R.drawable.bbq ));




        }
}

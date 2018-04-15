package com.drunker.drunkers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class carritoCompra extends AppCompatActivity {
    RecyclerView listdatos2;
    ArrayList<datoscomida> lista2;
    adaptadorcomida adaptador2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);

        listdatos2 = (RecyclerView)findViewById(R.id.lstdatos);
        LinearLayoutManager linearlayoutmanager= new LinearLayoutManager(this);
        linearlayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        listdatos2.setLayoutManager(linearlayoutmanager);
        cargardatos();
        adaptador2 = new adaptadorcomida(this,lista2);
        listdatos2.setAdapter(adaptador2);
    }

    public void cargardatos () {
        lista2 = new ArrayList<datoscomida>();

    }

}

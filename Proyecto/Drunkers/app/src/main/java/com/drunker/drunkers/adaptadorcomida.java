package com.drunker.drunkers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adaptadorcomida extends RecyclerView.Adapter<adaptadorcomida.ViewHolder> {

    Context contexto;
    List<datoscomida>listacomida;

    public adaptadorcomida(Context contexto, List<datoscomida> listacomida) {
        this.contexto = contexto;
        this.listacomida = listacomida;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemlistview = LayoutInflater.from(contexto).inflate(R.layout.itemlistview,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemlistview);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.titulo.setText(listacomida.get(position).getTitulo());
      holder. valor.setText((int) listacomida.get(position).getValor());
      //holder.imagen.setImageResource(listacomida.get(position).getImagen());

    }

    @Override
    public int getItemCount() {
        return listacomida.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView titulo,valor;
       //ImageView imagen;
       Button comprar;

       public ViewHolder (View vista) {
           super(vista);

           // imagen = (ImageView) vista.findViewById(R.id.imagen);
            titulo = (TextView) vista.findViewById(R.id.titulo);
           valor = (TextView) vista.findViewById(R.id.valor);
             comprar= (Button) vista.findViewById(R.id.comprar);


       }

    }



   /* public int getCount() {
        return listacomida.size();//retorna cantidad de elementos en la lista
    }

    @Override
    public Object getItem(int position) {
        return listacomida.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listacomida.get(position).getDatos();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vista = view;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista=inflate.inflate(R.layout.itemlistview,null);




        titulo.setText(listacomida.get(position).getTitulo().toString());
        valor.setText((int) listacomida.get(position).getValor());
        imagen.setImageResource(listacomida.get(position).getImagen());

        return vista;*/







}

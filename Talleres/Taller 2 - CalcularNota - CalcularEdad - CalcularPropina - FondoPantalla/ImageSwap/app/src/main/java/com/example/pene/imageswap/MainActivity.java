package com.example.pene.imageswap;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //se instancian los view, boton y recuadro de imagen.
    private ImageView imageView ;
    private  Button button, button2, button3;
    //Arreglo de imagenes a cambiar.
    private static int[] imagenes = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};
    //El contador sera quien de una poscicion en el areglo y asi mismo mostrara la imagen en ese poscicion.
    private int cont=0;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se ejecuta lafuncion cuando se clicea el boton
        buttonClick();

        buttonClickW();
       buttonClickr();
    }

    public void buttonClickW(){

        imageView = (ImageView)findViewById(R.id.iv1);
        button3 = (Button) findViewById(R.id.btn0);
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                        try{

                            myWallpaperManager.setResource(imagenes[cont]);
                            Toast.makeText(getApplicationContext(),"Se cambio el fondo de pantalla",Toast.LENGTH_SHORT).show();
                        }catch(Exception e){


                        }
                    }
                }
        );
    }


    public void buttonClickr(){

        imageView = (ImageView)findViewById(R.id.iv1);
        button2 = (Button) findViewById(R.id.bt2);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(cont==0) {
                            cont = 4;
                            imageView.setImageResource(imagenes[cont]);

                        } else{
                            cont--;
                        imageView.setImageResource(imagenes[cont]);
                        }
                    }
                }
        );
    }
    //Evento
    public void buttonClick(){
        imageView = (ImageView)findViewById(R.id.iv1);
        button = (Button) findViewById(R.id.bt1);
        button.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cada vez que se ahce clic el contador suma 1.
                cont++;
                //cuando el contador es 3 se reinicia sacando el modulo de este.
                cont = cont % imagenes.length;
                //ponemos en el image view la imagen con su contador para que vaya cambiando.
                imageView.setImageResource(imagenes[cont]);
            }
        }
);
}
}


package com.drunker.drunkers;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class pantallaCarga extends AppCompatActivity {
    ProgressBar prg;
    int progress=0;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);


        prg = (ProgressBar) findViewById(R.id.progressBar2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    progress += 20;
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            prg.setProgress(progress);
                            if (progress == prg.getMax()) {
                                Intent in = new Intent(getApplicationContext(), Login.class);
                                startActivity(in);

                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        //TODO: handle exception
                    }

                }

            }
        }).start();
    }

}

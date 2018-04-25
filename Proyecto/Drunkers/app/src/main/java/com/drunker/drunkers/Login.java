package com.drunker.drunkers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button btnRegistro;
    private Button btnEntrar;
    private EditText etUsuario, etContraseña;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContraseña = (EditText) findViewById(R.id.etContraseña);

        findViewById(R.id.btnRegistro).setOnClickListener(this);
        findViewById(R.id.BtnEntrar).setOnClickListener(this);


    }

    private void UserLogin() {
        String email = etUsuario.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email vacio
            etUsuario.setError("Introduce un correo valido");
            etUsuario.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            etUsuario.setError("Introduce un correo valido");
            etUsuario.requestFocus();
            return;

        }

        if (TextUtils.isEmpty(contraseña)) {
            //contraseña vacia
            etContraseña.setError("Introduce una contraseña");
            etContraseña.requestFocus();
            return;

        }

        if (contraseña.length() < 6) {
            etContraseña.setError("La contraseña debe tener al menos 6 caracteres");
            etContraseña.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    //finish();
                    Intent intent = new Intent(Login.this, InfoUsuario.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                } else {
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta o el usuario no tiene una contraseña resgistrada", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    /* @Override
     protected void onStart() {
         super.onStart();

         if(mAuth.getCurrentUser()!=null){
             finish();
             startActivity(new Intent(this,Login.class));
         }
     }*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegistro:
                //finish();
                startActivity(new Intent(this, Registro.class));
                break;
            case R.id.BtnEntrar:
                UserLogin();
                break;
        }

    }

}



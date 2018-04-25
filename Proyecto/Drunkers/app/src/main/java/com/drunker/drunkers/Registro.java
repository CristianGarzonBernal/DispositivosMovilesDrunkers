package com.drunker.drunkers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button btnRegistrarse, btnAtras;
    private EditText etCorreo, etContraseña, etRepContraseña;
    private ProgressDialog pgd;
    private FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();


        btnRegistrarse = (Button) findViewById(R.id.btnResgistrarse);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        etRepContraseña = (EditText) findViewById(R.id.etRepContraseña);
        btnAtras = (Button) findViewById(R.id.btnAtras);
        pgd = new ProgressDialog(this);

        btnRegistrarse.setOnClickListener(this);
        //etContraseña.setOnClickListener(this);
        //etCorreo.setOnClickListener(this);
    }

    private void registerUser() {
        String email = etCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();
        String RepContraseña = etContraseña.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            //email vacio
            etCorreo.setError("Introduce un correo valido");
            etCorreo.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            etCorreo.setError("Introduce un correo valido");
            etCorreo.requestFocus();
            return;

        }
        if (RepContraseña.equals(contraseña) && !TextUtils.isEmpty(RepContraseña)) {
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
        } else {
            etContraseña.setError("Las contraseñas no coinciden o el campo esta vacio");
            etContraseña.requestFocus();
            etRepContraseña.setError("");
            etRepContraseña.requestFocus();
            return;
        }

        //validamos
        mAuth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(getApplicationContext(), "El email ya esta en uso", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                    }


                }


            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view == btnRegistrarse) {
            registerUser();

        }
        if (view == btnAtras) {
// abrir login
        }
    }
}

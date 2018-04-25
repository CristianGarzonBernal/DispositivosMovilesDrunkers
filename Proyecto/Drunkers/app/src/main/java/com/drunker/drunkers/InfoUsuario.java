package com.drunker.drunkers;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class InfoUsuario extends AppCompatActivity {


    private static final int CHOOSE_IMAGE = 101;
    private EditText etUsuario, etCelular;
    private ImageView imFoto;
    String url;
    Uri uriImagenPerfil;
    ProgressBar pbFoto;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        mAuth = FirebaseAuth.getInstance();
        imFoto = findViewById(R.id.imFoto);
        etUsuario = findViewById(R.id.etUsuario1);
        etCelular = findViewById(R.id.etCelular);
        pbFoto = findViewById(R.id.pbFoto);

        cargarInformacion();

        imFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenElegida();
            }
        });

        findViewById(R.id.btnGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                guardarInformacion();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, Login.class));
        }
    }

    private void cargarInformacion() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                String photoUrl = user.getPhotoUrl().toString();
                  Glide.with(this).load(user.getPhotoUrl().toString()).into(imFoto);
            }
            if (user.getDisplayName() != null) {
                // String displayName = user.getDisplayName();
                etUsuario.setText(user.getDisplayName());
            }
        }


    }

    private void guardarInformacion() {
        String dUsuario = etUsuario.getText().toString();
        // float dCelular = Float.valueOf(etCelular.getText().toString());
        if (dUsuario.isEmpty()) {
            etUsuario.setError("El campo esta vacio");
            etUsuario.requestFocus();
            return;
        }
       /* if(dCelular>10){
            etUsuario.setError("El campo esta incompleto");
            etUsuario.requestFocus();
            return;
        }*/
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null && url != null) {

            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(dUsuario)
                    //.setDisplayName(String.valueOf(dCelular))
                    .setPhotoUri(Uri.parse(url))
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Perfil Actualizado", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {


            uriImagenPerfil = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImagenPerfil);
                imFoto.setImageBitmap(bitmap);
                cargarImagen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarImagen() {
        final StorageReference profileImageRef =
                FirebaseStorage.getInstance().getReference("fotoPerfil/" + System.currentTimeMillis() + ".jpg");

        if (uriImagenPerfil != null) {
            pbFoto.setVisibility(View.VISIBLE);
            profileImageRef.putFile(uriImagenPerfil).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pbFoto.setVisibility(View.GONE);

                    url = taskSnapshot.getDownloadUrl().toString();


                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pbFoto.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Ups...a ocurrido un error", Toast.LENGTH_SHORT).show();

                        }
                    });

        }

    }


    private void imagenElegida() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona tu imagen de perfil"), CHOOSE_IMAGE);
    }
}

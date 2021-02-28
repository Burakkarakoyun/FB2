package com.example.fb2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class MainActivity3 extends AppCompatActivity {
    ImageView resimsecmeresmi;
    EditText isimyeri;
    Button resimsecmebuton;
    Bitmap secilenResim;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        resimsecmeresmi = findViewById(R.id.resimsecmeresmi);
        isimyeri = findViewById(R.id.isimyeri);
        resimsecmebuton = findViewById(R.id.resimsecmebuton);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
    }
    public void resimsec(View view){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        else{
            Intent intentgaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentgaleri, 2 );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentgaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentgaleri, 2 );

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == 2 && resultCode == RESULT_OK && data!= null){
            Uri resimverisi = data.getData();
            try {
                if(Build.VERSION.SDK_INT>= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), resimverisi);
                    secilenResim = ImageDecoder.decodeBitmap(source);
                    resimsecmeresmi.setImageBitmap(secilenResim);
                }
                else {
                    secilenResim = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resimverisi);
                    resimsecmeresmi.setImageBitmap(secilenResim);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void kaydet(View view){
        String bilgi = isimyeri.getText().toString();

        finish();
    }
}
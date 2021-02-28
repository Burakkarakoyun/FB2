package com.example.fb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Button resimsecme ;
    Button gosterme;
    TextView textView;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        resimsecme = findViewById(R.id.resimsecmebuton);
        gosterme = findViewById(R.id.gosterme);
        textView = findViewById(R.id.kullanıcıgosterme);
        firebaseAuth = FirebaseAuth.getInstance();
        String mail = firebaseAuth.getCurrentUser().getEmail();
        textView.setText(mail);


    }
    public void resimsec(View view){
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(intent);
    }
    public void goster (View view){
        Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
    }
}
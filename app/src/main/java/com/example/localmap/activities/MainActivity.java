package com.example.localmap.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.localmap.R;

public class MainActivity extends AppCompatActivity {

    private Button botaoLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoLogar = findViewById(R.id.buttonLogar);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainInicio = new Intent(getApplicationContext(), InicioActivity.class);
                startActivity(mainInicio);
            }
        });
    }
}
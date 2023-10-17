package com.link.logovanje;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etNovoKorisnickoIme, etNovaSifra;
    private Button btnRegistracija;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etNovoKorisnickoIme = findViewById(R.id.etNovoKorisnickoIme);
        etNovaSifra = findViewById(R.id.etNovaSifra);
        btnRegistracija = findViewById(R.id.btnRegistracija);

        databaseHelper = new DatabaseHelper(this);

        btnRegistracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoKorisnickoIme = etNovoKorisnickoIme.getText().toString();
                String novaSifra = etNovaSifra.getText().toString();

                long result = databaseHelper.dodajKorisnika(novoKorisnickoIme, novaSifra);
                if (result != -1) {
                    Toast.makeText(RegistrationActivity.this, R.string.registration_successful, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, R.string.registration_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

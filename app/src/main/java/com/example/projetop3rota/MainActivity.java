package com.example.projetop3rota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner Origem_Cidades, Destino_Cidades;
    Button pesquisar;
    Button AddRota;


    String origem, destino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Origem_Cidades = findViewById(R.id.spinner_Origem); //Ponto Origem
        Destino_Cidades = findViewById(R.id.spinner_Destino); //Ponto Destino
        pesquisar = findViewById(R.id.ButtonPesquisar);
        AddRota = findViewById(R.id.ButtonAddRota);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Cidades_Rotas, android.R.layout.simple_spinner_item);

        Origem_Cidades.setAdapter(adapter);
        Destino_Cidades.setAdapter(adapter);

        pesquisar.setOnClickListener(this);
        AddRota.setOnClickListener(this);
        Origem_Cidades.setOnItemSelectedListener(this);
        Destino_Cidades.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ButtonPesquisar:
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("Origem",origem = Origem_Cidades.getSelectedItem().toString());
                i.putExtra("Destino",destino = Destino_Cidades.getSelectedItem().toString());
                startActivity(i);
                break;
            case R.id.ButtonAddRota:
                Intent AddRota = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(AddRota);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.spinner_Origem:
                this.origem = Origem_Cidades.getSelectedItem().toString();
                break;
            case R.id.spinner_Destino:
                this.destino = Destino_Cidades.getSelectedItem().toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.example.projetop3rota;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.MainThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main3Activity extends AppCompatActivity {

    Spinner Spinner1, Spinner2;
    EditText NVeiculos,Horario;
    Button Adicionar;
    
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void verificarPermissoes(Main3Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity, PERMISSONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
    }

    private void grava(String o, String d, String nv, String hr) throws IOException {
        verificarPermissoes(Main3Activity.this);
        File root = new File(Environment.getExternalStorageDirectory(), "/Trajetos/");
        File file = new File(root, "cadastroOnibus.txt");
        if (!file.exists())
        {
            root.mkdirs();
            root.createNewFile();
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(o + ";" + d + ";" + nv + ";" + hr + "\n");
            bw.close();
            fw.close();

            AlertDialog.Builder dlg = new AlertDialog.Builder(Main3Activity.this);
            dlg.setTitle("Cadastro");
            dlg.setNeutralButton("OK", null);
            dlg.setMessage("Cadastro Efetuado!");
            dlg.show();
        } catch (IOException e) {
            e.printStackTrace();
            AlertDialog.Builder dlg = new AlertDialog.Builder(Main3Activity.this);
            dlg.setTitle("Cadastro");
            dlg.setNeutralButton("OK", null);
            dlg.setMessage("Não foi possível cadastrar!");
            dlg.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Spinner1 = findViewById(R.id.spinner1);
        Spinner2 = findViewById(R.id.spinner2);
        NVeiculos = findViewById(R.id.Nveiculo);
        Horario = findViewById(R.id.Horario);
        Adicionar = findViewById(R.id.Adicionar);

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Cidades_Rotas, android.R.layout.simple_spinner_item);
        Spinner1.setAdapter(adapter);
        Spinner2.setAdapter(adapter);

        /*NVeiculos.setOnClickListener((View.OnClickListener) this);
        Horario.setOnClickListener((View.OnClickListener) this);
        Adicionar.setOnClickListener((View.OnClickListener) this);
        Spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        Spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);*/


        Adicionar.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String origem = Spinner1.getSelectedItem().toString(); //it Works
                String destino = Spinner2.getSelectedItem().toString();
                String numVeiculo = NVeiculos.getText().toString();
                String hrSaida = Horario.getText().toString();
                try {
                    grava(origem, destino, numVeiculo, hrSaida);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

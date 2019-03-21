package com.example.projetop3rota;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    protected  String origem, destino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();

        origem = i.getStringExtra("Origem"); // it works
        destino = i.getStringExtra("Destino");

        /*AlertDialog.Builder dlg = new AlertDialog.Builder(Main2Activity.this);
        dlg.setTitle("Cadastro leitura");
        dlg.setNeutralButton("OK", null);
        dlg.setMessage(origem);
        dlg.show();*/

        ListView lista = findViewById(R.id.IvRotas);
        ArrayAdapter adapter = null;
        try {
            adapter = new RotasAdapter(this, adidicionarRota());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        lista.setAdapter(adapter);
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void verificarPermissoes(Main2Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity, PERMISSONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
    }

    private ArrayList<Rotas> adidicionarRota() throws FileNotFoundException {

        ArrayList<Rotas> rotas = new ArrayList<>();

        verificarPermissoes(Main2Activity.this);
            File root = new File(Environment.getExternalStorageDirectory(), "/Trajetos/");
            File file = root;
            FileReader fr = new FileReader(root);
            BufferedReader br = new BufferedReader(fr);
            try
            {
                while(br.ready())
                {
                    String trajeto[] = br.readLine().split(";");
                    String orig = trajeto[0];
                    String dest = trajeto[1];
                    String numVeiculo = trajeto[2];
                    String hrPartida = trajeto[3];
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Main2Activity.this);
                    dlg.setTitle("Cadastro leitura");
                    dlg.setNeutralButton("OK", null);
                    dlg.setMessage(orig);
                    dlg.show();

                    if (1 == 1) {
                        rotas.add(new Rotas(orig, dest, Float.parseFloat(numVeiculo), hrPartida));
                    } else {
                        continue;
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
                AlertDialog.Builder dlg = new AlertDialog.Builder(Main2Activity.this);
                dlg.setTitle("Cadastro leitura");
                dlg.setNeutralButton("OK", null);
                dlg.setMessage("Não consegui resolver");
                dlg.show();
            }
        return rotas;
    }
}

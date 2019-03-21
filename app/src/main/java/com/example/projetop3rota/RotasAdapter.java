package com.example.projetop3rota;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RotasAdapter extends ArrayAdapter<Rotas> {
    private final Context context;
    private final ArrayList<Rotas> elementos;

    public RotasAdapter(Context context, ArrayList<Rotas> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView nomeOrigem =  rowView.findViewById(R.id.Origem_viagem);
        TextView nomeDestino = rowView.findViewById(R.id.Destino_viagem);
        TextView numeroVeiculo = rowView.findViewById(R.id.Numeracao_veiculo);
        TextView horarioPartida = rowView.findViewById(R.id.Horario_Partida);

        nomeOrigem.setText(elementos.get(position).getNomeOrigemViagem());
        nomeDestino.setText(elementos.get(position).getNomeDestinoViagem());
        numeroVeiculo.setText(String.valueOf(elementos.get(position).getNumerodoVeiculo()));
        horarioPartida.setText(elementos.get(position).getHorarioPartida());

        return rowView;
    }
}

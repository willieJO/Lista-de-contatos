package br.edu.ifsp.dmos5.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.ifsp.dmos5.model.Telefone;
import br.edu.ifsp.dmos5.model.Usuario;

public class ContatoSpinnerAdapter extends ArrayAdapter<Telefone> {
    public ContatoSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Telefone> values) {
        super(context, resource, values);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.BLACK);
        if(getItem(position) == null){
            textView.setText("");
        }else {
            textView.setText(getItem(position).getApelido());
        }
        return textView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.BLACK);
        if(getItem(position) == null){
            textView.setText("");
        }else {
            String apelido = getItem(position).getApelido();
            String tituloApelido = "Apelido:";
            String tituloNome = "Nome:";
            String mome = getItem(position).getNome();

            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(tituloApelido).append(" ");
            builder.append(apelido, new StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append("\n");
            builder.append(tituloNome).append(" ");
            builder.append(mome, new StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            textView.setText(builder);

        }
        textView.setPadding(8, 8, 8, 8);
        return textView;
    }
}

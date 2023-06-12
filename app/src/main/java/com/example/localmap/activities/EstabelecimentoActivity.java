package com.example.localmap.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.google.android.material.imageview.ShapeableImageView;

public class EstabelecimentoActivity extends AppCompatActivity {

    private ImageView btnFavoritar;
    private boolean favorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        // Obtem o objeto estabelecimento da intent
        Estabelecimento estabelecimento = (Estabelecimento) getIntent().getSerializableExtra("estabelecimento");
        // Exibe os dados do estabelecimento nos campos correspondentes
        ShapeableImageView fotoEstabelecimentoShapeableImageView = findViewById(R.id.fotoEstabelecimentoShapeableImageView);
        fotoEstabelecimentoShapeableImageView.setImageResource(estabelecimento.getImagem());
        TextView nomeTextView = findViewById(R.id.nomeTextView);
        nomeTextView.setText(estabelecimento.getNome());
        TextView avaliacaoTextView = findViewById(R.id.avaliacaoTextView);
        avaliacaoTextView.setText(String.format("%.1f", estabelecimento.getAvaliacao()));
        TextView categoriaTextView = findViewById(R.id.categoriaTextView);
        categoriaTextView.setText(estabelecimento.getCategoria().getNome());
        TextView contatoTextView = findViewById(R.id.contatoTextView);
        contatoTextView.setText(estabelecimento.getTelefoneContato());
        TextView enderecoTextView = findViewById(R.id.enderecoTextView);
        enderecoTextView.setText(estabelecimento.getEndereco());
        // Acima, falta definir Favorito e Rota

        // Muda o estado do botão favoritar
        btnFavoritar = findViewById(R.id.favButtonImageView);
        btnFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!favorito){
                    favorito = true;
                    btnFavoritar.setImageDrawable(getDrawable(R.drawable.favorito_cheio));
                }
                else{
                    btnFavoritar.setImageDrawable(getDrawable(R.drawable.favorito_vazio));
                    favorito = false;
                }
            }
        });
    }
}
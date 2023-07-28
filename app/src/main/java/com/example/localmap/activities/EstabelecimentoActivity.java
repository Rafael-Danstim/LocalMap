package com.example.localmap.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.example.localmap.recycler_view_classes.Usuario;
import com.example.localmap.recycler_view_classes.UsuarioEstabelecimentoFavoritado;
import com.example.localmap.retrofit.RetrofitService;
import com.example.localmap.retrofit.UsuarioEstabelecimentoFavoritadoApi;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstabelecimentoActivity extends AppCompatActivity {

    private ImageView favoritarImageView;
    private Estabelecimento estabelecimento;
    private Usuario usuario;
    private boolean isFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        // Obtain the logged-in Google account
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            // Create a new Usuario object using the Google account information
            usuario = new Usuario();
            usuario.setId(1); // Set the user ID (you may need to use a unique ID for each user)
            usuario.setNome(account.getDisplayName());
            usuario.setEmail(account.getEmail());
            // Add other relevant user information if available
        } else {
            // Handle the case when the user is not logged in
            // For example, show a message or redirect to the login screen
        }

        // Obtem o objeto estabelecimento da intent
        estabelecimento = (Estabelecimento) getIntent().getSerializableExtra("estabelecimento");

        favoritarImageView = findViewById(R.id.favButtonImageView);

        verificarFavorito();

        favoritarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorito();
            }
        });

        // Exibe os dados do estabelecimento nos campos correspondentes
        ShapeableImageView fotoEstabelecimentoShapeableImageView = findViewById(R.id.fotoEstabelecimentoShapeableImageView);

        Glide.with(this)
                .load(estabelecimento.getImagem())
                .into(fotoEstabelecimentoShapeableImageView);

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
        // Acima, falta definir Rota

        //imagem de favoritado: res/drawable-v24/favorito_cheio.png
        //imagem de não favoritado: res/drawable-v24/favorito_vazio.png
    }

    private void favoriteEstabelecimento() {
        RetrofitService retrofitService = new RetrofitService();
        UsuarioEstabelecimentoFavoritadoApi api = retrofitService.getUsuarioEstabelecimentoFavoritadoApi();

        // Obter o ID do usuário armazenado nas preferências compartilhadas
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        if (userId != -1) {
            UsuarioEstabelecimentoFavoritado usuarioEstabelecimentoFavoritado = new UsuarioEstabelecimentoFavoritado();
            Usuario usuario = new Usuario();
            usuario.setId(userId);
            usuarioEstabelecimentoFavoritado.setUsuario(usuario);
            usuarioEstabelecimentoFavoritado.setEstabelecimento(estabelecimento);
            usuarioEstabelecimentoFavoritado.setFavoritado(true); // Define o status de "favoritado" como verdadeiro

            Call<UsuarioEstabelecimentoFavoritado> call = api.favoritarEstabelecimento(usuarioEstabelecimentoFavoritado);
            call.enqueue(new Callback<UsuarioEstabelecimentoFavoritado>() {
                @Override
                public void onResponse(Call<UsuarioEstabelecimentoFavoritado> call, Response<UsuarioEstabelecimentoFavoritado> response) {
                    if (response.isSuccessful()) {
                        // Atualizar a imagem do botão para o coração cheio
                        favoritarImageView.setImageResource(R.drawable.favorito_cheio);
                        isFavorito = true;
                    } else {
                        // Tratar falha ao favoritar estabelecimento
                    }
                }

                @Override
                public void onFailure(Call<UsuarioEstabelecimentoFavoritado> call, Throwable t) {
                    // Tratar falha na requisição ao servidor
                }
            });
        } else {
            // O ID do usuário não foi obtido corretamente das preferências compartilhadas
            // Tratar o cenário em que o ID do usuário não está disponível
        }
    }

    private void unfavoriteEstabelecimento() {
        RetrofitService retrofitService = new RetrofitService();
        UsuarioEstabelecimentoFavoritadoApi api = retrofitService.getUsuarioEstabelecimentoFavoritadoApi();

        // Obter o ID do usuário armazenado nas preferências compartilhadas
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        if (userId != -1) {
            UsuarioEstabelecimentoFavoritado usuarioEstabelecimentoFavoritado = new UsuarioEstabelecimentoFavoritado();
            Usuario usuario = new Usuario();
            usuario.setId(userId);
            usuarioEstabelecimentoFavoritado.setUsuario(usuario);
            usuarioEstabelecimentoFavoritado.setEstabelecimento(estabelecimento);
            usuarioEstabelecimentoFavoritado.setFavoritado(false); // Definir o status de "favoritado" como falso para desfavoritar

            Call<UsuarioEstabelecimentoFavoritado> call = api.favoritarEstabelecimento(usuarioEstabelecimentoFavoritado);
            call.enqueue(new Callback<UsuarioEstabelecimentoFavoritado>() {
                @Override
                public void onResponse(Call<UsuarioEstabelecimentoFavoritado> call, Response<UsuarioEstabelecimentoFavoritado> response) {
                    if (response.isSuccessful()) {
                        // Atualizar a imagem do botão para o coração vazio
                        favoritarImageView.setImageResource(R.drawable.favorito_vazio);
                        isFavorito = false;
                    } else {
                        // Tratar falha ao desfavoritar estabelecimento
                    }
                }

                @Override
                public void onFailure(Call<UsuarioEstabelecimentoFavoritado> call, Throwable t) {
                    // Tratar falha na requisição ao servidor
                }
            });
        } else {
            // O ID do usuário não foi obtido corretamente das preferências compartilhadas
            // Tratar o cenário em que o ID do usuário não está disponível
        }
    }

    private void toggleFavorito() {
        if (isFavorito) {
            unfavoriteEstabelecimento();
        } else {
            favoriteEstabelecimento();
        }
    }

    private void verificarFavorito() {
        RetrofitService retrofitService = new RetrofitService();
        UsuarioEstabelecimentoFavoritadoApi api = retrofitService.getUsuarioEstabelecimentoFavoritadoApi();

        // Obter o ID do usuário armazenado nas preferências compartilhadas
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        if (userId != -1) {
            Call<UsuarioEstabelecimentoFavoritado> call = api.verificarFavorito(userId, estabelecimento.getId());
            call.enqueue(new Callback<UsuarioEstabelecimentoFavoritado>() {
                @Override
                public void onResponse(Call<UsuarioEstabelecimentoFavoritado> call, Response<UsuarioEstabelecimentoFavoritado> response) {
                    if (response.isSuccessful()) {
                        UsuarioEstabelecimentoFavoritado favoritado = response.body();
                        if (favoritado != null && favoritado.isFavoritado()) {
                            // Atualizar a imagem do botão para o coração cheio
                            favoritarImageView.setImageResource(R.drawable.favorito_cheio);
                            isFavorito = true;
                        } else {
                            // Atualizar a imagem do botão para o coração vazio
                            favoritarImageView.setImageResource(R.drawable.favorito_vazio);
                            isFavorito = false;
                        }
                    } else if (response.code() == 204) {
                        // Não está na tabela de favoritos
                        // Atualizar a imagem do botão para o coração vazio
                        favoritarImageView.setImageResource(R.drawable.favorito_vazio);
                        isFavorito = false;
                    } else {
                        // Tratar falha na resposta do servidor
                    }
                }

                @Override
                public void onFailure(Call<UsuarioEstabelecimentoFavoritado> call, Throwable t) {
                    // Tratar falha na requisição ao servidor
                }
            });
        } else {
            // O ID do usuário não foi obtido corretamente das preferências compartilhadas
            // Tratar o cenário em que o ID do usuário não está disponível
        }
    }
}

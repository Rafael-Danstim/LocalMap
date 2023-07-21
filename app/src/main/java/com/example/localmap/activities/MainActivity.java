package com.example.localmap.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Usuario;
import com.example.localmap.retrofit.RetrofitService;
import com.example.localmap.retrofit.UsuarioApi;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button botaoLogar;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoLogar = findViewById(R.id.buttonLogar);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            navegarParaInicioActivity();
        }

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // Salvando o usuário no servidor
                saveUsuarioToServer(account);
                navegarParaInicioActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Algo deu errado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para salvar o usuário no servidor
    private void saveUsuarioToServer(GoogleSignInAccount account) {
        String nome = account.getDisplayName();
        String email = account.getEmail();
        String contaGoogleId = account.getId(); // Obter o ID da conta Google

        RetrofitService retrofitService = new RetrofitService();
        UsuarioApi usuarioApi = retrofitService.getRetrofit().create(UsuarioApi.class);

        // Crie um objeto Usuario com os dados obtidos da conta Google
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setContaGoogleId(contaGoogleId); // Definindo o ID da conta Google

        // Envie o objeto Usuario para o servidor usando a API Retrofit
        Call<Usuario> call = usuarioApi.save(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    // Sucesso ao salvar o usuário no servidor
                    Usuario savedUser = response.body();
                    // Você pode usar o ID retornado pelo servidor, se necessário
                } else {
                    // Tratar falha ao salvar o usuário no servidor
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Tratar falha na requisição ao servidor
            }
        });
    }

    void navegarParaInicioActivity(){
        finish();
        Intent i1 = new Intent(MainActivity.this, InicioActivity.class);
        startActivity(i1);
    }
}
package com.example.localmap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.localmap.R;
import com.example.localmap.databinding.ActivityInicioBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class InicioActivity extends AppCompatActivity {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityInicioBinding binding;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainInicio.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil, R.id.nav_favoritos, R.id.nav_map, R.id.nav_pesquisa)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_inicio);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);

        TextView nomeUsuario = headerView.findViewById(R.id.usuario);
        TextView emailUsuario = headerView.findViewById(R.id.email);
        ImageView imageView = headerView.findViewById(R.id.imageView);
        ImageView botaoSair = headerView.findViewById(R.id.sair);

        //Define os textos de nome e email de acordo com a última conta que fez login
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){
            String nomeContaGoogle = account.getDisplayName();
            String emailContaGoogle = account.getEmail();
            nomeUsuario.setText(nomeContaGoogle);
            emailUsuario.setText(emailContaGoogle);

            // Obter URL da foto de perfil do usuário
            if (account.getPhotoUrl() != null) {
                String photoUrl = account.getPhotoUrl().toString();

                // Carregar imagem usando a biblioteca Picasso
                Picasso.get()
                        .load(photoUrl)
                        .placeholder(R.drawable.icone_google) // Imagem temporária enquanto carrega
                        .error(R.drawable.local_map_logo02) // Imagem de erro, caso haja falha no carregamento
                        .into(imageView);
            }
        }

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        //Abaixo, menu inferior.
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //Abaixo, ocultar o menu inferior quando perfil ou favoritos for selecionado.
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_perfil || destination.getId() == R.id.nav_favoritos) {
                bottomNavigationView.setVisibility(View.GONE);
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_inicio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent configuracoes = new Intent(getApplicationContext(), ConfiguracaoActivity.class);
                startActivity(configuracoes);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_inicio);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void signOut(){
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
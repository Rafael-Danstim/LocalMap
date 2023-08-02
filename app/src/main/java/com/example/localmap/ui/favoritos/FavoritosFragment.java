package com.example.localmap.ui.favoritos;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.adapters.EstabelecimentoAdapter;
import com.example.localmap.databinding.FragmentFavoritosBinding;
import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.example.localmap.recycler_view_classes.UsuarioEstabelecimentoFavoritado;
import com.example.localmap.retrofit.RetrofitService;
import com.example.localmap.retrofit.UsuarioEstabelecimentoFavoritadoApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritosFragment extends Fragment {

    private FragmentFavoritosBinding binding;
    private EstabelecimentoAdapter estabelecimentoAdapter;
    private List<Estabelecimento> listaEstabelecimentosFavoritos;

    @Override
    public void onResume() {
        super.onResume();
        // Chame o método para obter a lista de estabelecimentos favoritados e atualize o adapter
        getListaEstabelecimentosFavoritados();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar a lista de estabelecimentos favoritados
        listaEstabelecimentosFavoritos = new ArrayList<>();

        // Configurar o adapter e a RecyclerView para os estabelecimentos favoritados
        estabelecimentoAdapter = new EstabelecimentoAdapter(listaEstabelecimentosFavoritos);
        binding.favoritadosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favoritadosRecyclerView.setAdapter(estabelecimentoAdapter);

        // Chame o método para obter a lista de estabelecimentos favoritados e atualize o adapter
        getListaEstabelecimentosFavoritados();

        return root;
    }

    private void getListaEstabelecimentosFavoritados() {
        RetrofitService retrofitService = new RetrofitService();
        UsuarioEstabelecimentoFavoritadoApi api = retrofitService.getUsuarioEstabelecimentoFavoritadoApi();

        // Obter o ID do usuário armazenado nas preferências compartilhadas
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        if (userId != -1) {
            Call<List<UsuarioEstabelecimentoFavoritado>> call = api.getEstabelecimentosFavoritados(userId);
            call.enqueue(new Callback<List<UsuarioEstabelecimentoFavoritado>>() {
                @Override
                public void onResponse(Call<List<UsuarioEstabelecimentoFavoritado>> call, Response<List<UsuarioEstabelecimentoFavoritado>> response) {
                    if (response.isSuccessful()) {
                        List<UsuarioEstabelecimentoFavoritado> favoritados = response.body();
                        if (favoritados != null) {
                            // Limpar a lista de estabelecimentos favoritados existente
                            listaEstabelecimentosFavoritos.clear();

                            // Adicionar os estabelecimentos favoritados à lista, apenas se o atributo "favoritado" for verdadeiro
                            for (UsuarioEstabelecimentoFavoritado favoritado : favoritados) {
                                if (favoritado.isFavoritado()) {
                                    listaEstabelecimentosFavoritos.add(favoritado.getEstabelecimento());
                                }
                            }

                            // Atualizar o adapter com a nova lista de estabelecimentos favoritados
                            estabelecimentoAdapter.setListaEstabelecimentos(listaEstabelecimentosFavoritos);
                        }
                    } else {
                        // Tratar falha na resposta do servidor
                    }
                }

                @Override
                public void onFailure(Call<List<UsuarioEstabelecimentoFavoritado>> call, Throwable t) {
                    // Tratar falha na requisição ao servidor
                }
            });
        } else {
            // O ID do usuário não foi obtido corretamente das preferências compartilhadas
            // Tratar o cenário em que o ID do usuário não está disponível
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
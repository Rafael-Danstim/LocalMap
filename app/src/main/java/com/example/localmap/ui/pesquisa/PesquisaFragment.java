package com.example.localmap.ui.pesquisa;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.localmap.R;
import com.example.localmap.activities.PesquisaActivity;
import com.example.localmap.databinding.FragmentPesquisaBinding;

public class PesquisaFragment extends Fragment {

    private FragmentPesquisaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPesquisaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView abrirPesquisa = root.findViewById(R.id.abrirPesquisa);

        //--> Abaixo, abrir perquisa.
        abrirPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirPesquisa = new Intent(getContext(), PesquisaActivity.class);
                startActivity(abrirPesquisa);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
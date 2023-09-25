package com.example.localmap.ui.configuracao;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.localmap.R;
import com.example.localmap.databinding.FragmentConfiguracaoBinding;

public class ConfiguracaoFragment extends Fragment {

    private FragmentConfiguracaoBinding binding;

    private Switch mudarTema;
    private static final String PREF_THEME = "theme_preference";
    private static final String THEME_LIGHT = "light";
    private static final String THEME_DARK = "dark";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentConfiguracaoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configura o tema com base na preferência salva
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("app_prefs", AppCompatActivity.MODE_PRIVATE);
        String savedTheme = sharedPreferences.getString(PREF_THEME, THEME_LIGHT);

        if (savedTheme.equals(THEME_DARK)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        mudarTema = root.findViewById(R.id.mudarTema);

        // Define o estado do switch com base na preferência de tema salvo
        mudarTema.setChecked(savedTheme.equals(THEME_DARK));

        mudarTema.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Salva a preferência do tema
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_THEME, isChecked ? THEME_DARK : THEME_LIGHT);
                editor.apply();

                // Alterna entre os modos de tema claro e escuro
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                // Recria a atividade para aplicar o novo tema
                requireActivity().recreate();
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
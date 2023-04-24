package com.formacaoandroidstudio.fragments.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.formacaoandroidstudio.fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversasFragment extends Fragment {

    private TextView textConversa;

    public ConversasFragment() {
        // Required empty public constructor
    }

    /* Este método pega o fragment_conversas e converte em uma View e retorna exibindo para o usuário. */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);

        textConversa = view.findViewById(R.id.textConversas);
        textConversa.setText("Fragment Conversa");

        return view;
    }
}

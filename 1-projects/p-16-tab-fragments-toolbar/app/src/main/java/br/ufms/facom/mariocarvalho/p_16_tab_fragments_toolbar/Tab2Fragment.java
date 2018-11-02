package br.ufms.facom.mariocarvalho.p_16_tab_fragments_toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Mário Carvalho on 30/10/2018.
 */

public class Tab2Fragment extends Fragment {
    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);
        btnTEST = view.findViewById(R.id.btnTest1);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Testando o click do botão 2",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

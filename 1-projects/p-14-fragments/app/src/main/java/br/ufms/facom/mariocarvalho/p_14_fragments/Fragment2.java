package br.ufms.facom.mariocarvalho.p_14_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, null);
        TextView tv = view.findViewById(R.id.tvFragment2);
        tv.setText("My Fragment 2");

        return view;
    }

    public void changeText(String text) {
        TextView tv = getView().findViewById(R.id.tvFragment2);
        tv.setText(text);
    }
}

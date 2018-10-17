package br.ufms.facom.mariocarvalho.p_14_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, null);
        TextView tv = view.findViewById(R.id.tvFragment3);
        tv.setText("My Fragment 3");

        return view;
    }

    public void changeText(String text) {
        TextView tv = getView().findViewById(R.id.tvFragment3);
        tv.setText(text);
    }
}

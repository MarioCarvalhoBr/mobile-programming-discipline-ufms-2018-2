package br.ufms.facom.mariocarvalho.p_14_fragments;
// Lefyt Poopy
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView lvList;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        if (savedInstanceState == null){
            fragment = new Fragment1();

            if (fragment != null) {
                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.layoutRight, fragment)
                        .commit();
            }
        }
        final String[] list = new String[]{"Fragment 1", "Alterar texto Fragment1", "Fragment 2", "Fragment 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, list);
        lvList = findViewById(R.id.lvList);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Toast.makeText(MainActivity.this, ""+list[position], Toast.LENGTH_SHORT).show();
                if (position == 0){
                    fragment = new Fragment1();

                    if (fragment != null) {
                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.layoutRight, fragment, "frag1")
                                .commit();
                    }
                }else if (position == 1){
                    // TODO
                }else if (position == 2){
                    fragment = new Fragment2();

                    if (fragment != null) {
                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.layoutRight, fragment)
                                .commit();
                    }
                }else if (position == 3){
                    fragment = new Fragment3();

                    if (fragment != null) {
                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.layoutRight, fragment)
                                .commit();
                    }
                }
            }
        });

    }
}

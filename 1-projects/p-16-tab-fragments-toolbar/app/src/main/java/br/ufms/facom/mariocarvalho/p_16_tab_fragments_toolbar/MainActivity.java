package br.ufms.facom.mariocarvalho.p_16_tab_fragments_toolbar;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Iniciando...");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Configurando o ViewPager com o adaptador de seções.
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Lorem Ipsum");
        adapter.addFragment(new Tab2Fragment(), "História");
        adapter.addFragment(new Tab3Fragment(), "Uso");
        viewPager.setAdapter(adapter);
    }

}

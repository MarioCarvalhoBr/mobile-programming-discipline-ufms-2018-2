package br.ufms.facom.mariocarvalho.bookkeeping.view.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;

import br.ufms.facom.mariocarvalho.bookkeeping.util.Util;
import br.ufms.facom.mariocarvalho.bookkeeping.util.search_tools.MaterialSearchView;
import br.ufms.facom.mariocarvalho.bookkeeping.view.fragments.ItensFavoritosFragment;
import br.ufms.facom.mariocarvalho.bookkeeping.view.fragments.ItensFragment;
import br.ufms.facom.mariocarvalho.bookkeeping.R;

public class MainNavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialSearchView mToolbarSearch;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation_drawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.app_name));//Carrega o nome do app do <code>string.xml</code>
        setSupportActionBar(mToolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow();
            w.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            w.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
        }else{
            //noinspection deprecation
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        mToolbarSearch = (MaterialSearchView)findViewById(R.id.search_view);
        mToolbarSearch.setVoiceSearch(true);
        mToolbarSearch.setCursorDrawable(R.drawable.color_cursor_white);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = null;
        fragment = new ItensFragment();

        if (fragment != null) {
            // Insert the fragment by replacing any existing fragment
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mToolbarSearch.isSearchOpen()) {
            mToolbarSearch.closeSearch();
            return;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    mToolbarSearch.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_itens) {
            fragment = new ItensFragment();
        } else if (id == R.id.nav_favoritos) {
            fragment = new ItensFavoritosFragment();
        } else if (id == R.id.nav_share) {
            
            String texto = getResources().getString(R.string.text_share_app);
            
            Util.compartilhar(MainNavigationDrawerActivity.this, texto);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        } else if (id == R.id.nav_sobre) {
            Util.startIntent(MainNavigationDrawerActivity.this,new SobreActivity());
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }else if (id == R.id.nav_sair) {
          finish();
        }

        if (fragment != null) {
            // Insert the fragment by replacing any existing fragment
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

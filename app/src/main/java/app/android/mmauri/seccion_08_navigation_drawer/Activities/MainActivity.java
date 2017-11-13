package app.android.mmauri.seccion_08_navigation_drawer.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import app.android.mmauri.seccion_08_navigation_drawer.Fragments.AlertsFragment;
import app.android.mmauri.seccion_08_navigation_drawer.Fragments.EmailFragment;
import app.android.mmauri.seccion_08_navigation_drawer.Fragments.InfoFragment;
import app.android.mmauri.seccion_08_navigation_drawer.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);

        setFragmentByDefault();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTx = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_mail:
                        fragment = new EmailFragment();
                        fragmentTx = true;
                        break;
                    case R.id.menu_alert:
                        fragment = new AlertsFragment();
                        fragmentTx = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTx = true;
                        break;
                    case R.id.menu_option_1:
                        Toast.makeText(getApplicationContext(), "Has clickeado en la opcion 1",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_option_2:
                        Toast.makeText(getApplicationContext(), "Has clickeado en la opcion 2",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

                if (fragmentTx == true) {
                    changeFragment(fragment, item);
                }

                return true;
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault() {
        // El item Email en el menu ocupa la posicion (index) 0
        changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Marca el item como clickeado
        item.setChecked(true);
        // Actualizamos el titulo del toolbar
        getSupportActionBar().setTitle(item.getTitle());
        // Cerramos el menu
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

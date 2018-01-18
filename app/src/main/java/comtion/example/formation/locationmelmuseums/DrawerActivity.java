package comtion.example.formation.locationmelmuseums;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import comtion.example.formation.locationmelmuseums.model.Museum;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Museum museum;

    private TextView museumNameTextView;
    private TextView museumAddressTextView;
    private TextView museumSiteTextView;

    private NavigationView navigationView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Référence au textView dans l'en-tête de navigation
        View headerView = ((NavigationView)navigationView.findViewById(R.id.nav_view))
                .getHeaderView(0);
        museumNameTextView = headerView.findViewById(R.id.headerMuseumName);
        museumAddressTextView = headerView.findViewById(R.id.headerMuseumAddress);
        museumSiteTextView = headerView.findViewById(R.id.headerMuseumSite);

        //Instanciation du musée
        this.museum = new Museum();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_musée) {
            navigateToFragment(new MuseumFragment());
        } /**else if (id == R.id.nav_gallery) {
           Intent mapIntention = new Intent(this, MapsActivity.class);
           startActivity(mapIntention);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Affichage du fragment passé en argument à la place
    //du composant identifié comme fragmentContainer
    private void navigateToFragment(MuseumFragment museumFragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, museumFragment)
                .commit();
    }

    //Naviguer vers MuseumFragment
    public void gotoMuseumFragment() {navigateToFragment(new MuseumFragment());}

}

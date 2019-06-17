package com.XTEC.carpoolingtec;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.util.ArrayList;

import Data.Auto;
import Data.Usuario;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Register.OnFragmentInteractionListener, fb.OnFragmentInteractionListener, Login.OnFragmentInteractionListener, Inicio.OnFragmentInteractionListener,
        Canjeo.OnFragmentInteractionListener, Autos.OnFragmentInteractionListener, addAuto.OnFragmentInteractionListener, Dashboards.OnFragmentInteractionListener, Inv_viaje.OnFragmentInteractionListener,
        n_viaje.OnFragmentInteractionListener, Amigos.OnFragmentInteractionListener, Perfil.OnFragmentInteractionListener

{

    public NavigationView navigationView;
    private Fragment fragment;
    public  DrawerLayout drawer;
    private View headerView;
    private String usrName, usrLName, usrid;

    public Usuario usuario = new Usuario();
    public Dialogs dialogs = new Dialogs();
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);

        usuarioData();

        fragment = new Login();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragment).disallowAddToBackStack().commit();

      }

    public View hview(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        return headerView;
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the LoginFB/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void usuarioData(){
        usuario.setNombre("Gerardo Zeledon");
        usuario.setCorreo("g.zeledon@estudiantec.cr");
        usuario.setContraseña("Gerardozm1408.");
        usuario.setCedula(304960671);
        usuario.setTelefono(71790955);

        //Autos
        usuario.addAuto(new Auto("Toyota", "Corolla", "BCD145",5));
        usuario.addAuto(new Auto("Nissan", "Sentra", "FGC758", 5));
        usuario.addAuto(new Auto("Hyundai", "Accen Blue", "ERT1256", 5));
        usuario.addAuto(new Auto("Toyota", "Corolla", "CFD356", 5));
        usuario.addAuto(new Auto("Toyota", "Rav4", "QWE459", 5));

        //Amigos
        usuario.addAmigo(new Usuario("Roberto Rodriguez",71892657));
        usuario.addAmigo(new Usuario("Maria Cortez",85783695));
        usuario.addAmigo(new Usuario("Mario Perez",75142863));
        usuario.addAmigo(new Usuario("Juan Pereira",92653896));
        usuario.addAmigo(new Usuario("Emmanuel Martinez",78523642));
        usuario.addAmigo(new Usuario("Olman Zeledon",86243685));

        //Solicitudes
        usuario.addSolicitud(new Usuario("Roberto Rodriguez",71892657));
        usuario.addSolicitud(new Usuario("Maria Cortez",85783695));
        usuario.addSolicitud(new Usuario("Mario Perez",75142863));
        usuario.addSolicitud(new Usuario("Juan Pereira",92653896));
        usuario.addSolicitud(new Usuario("Emmanuel Martinez",78523642));
        usuario.addSolicitud(new Usuario("Olman Zeledon",86243685));

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragMenu = null;
        boolean FragmentSelect = false;
        boolean logout = false;


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Profile) {
            fragMenu = new Perfil();
            FragmentSelect = true;
        } else if (id == R.id.nav_home) {
            fragMenu = new Inicio();
            FragmentSelect = true;
        } else if (id == R.id.canjear) {
            fragMenu = new Canjeo();
            FragmentSelect = true;
        } else if (id == R.id.autos) {
            fragMenu = new Autos();
            FragmentSelect = true;
        }else if (id == R.id.amigos) {
            fragMenu = new Amigos();
            FragmentSelect = true;
        }else if (id == R.id.nav_logout) {
            fragMenu = fragment;
            LoginManager.getInstance().logOut();
            FragmentManager fm = getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            FragmentSelect = true;
        } else if(id == R.id.nav_remove){
            fragMenu = fragment;
            logout = true;
            FragmentSelect = true;
        }

        if (FragmentSelect){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_main,fragMenu).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}

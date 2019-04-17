package com.XTEC.carpoolingtec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Profile.OnFragmentInteractionListener {

    private LoginButton login_button;
    private SharedPreferences sharedpreferences;
    private CircleImageView circleImageView;
    private TextView txtName, txtEmail;
    private Bundle bundle;
    private String first_name;
    private String last_name ;
    private String id ;
    private String email;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        //
        first_name = "";
        last_name = "";
        id = "";
        email = "Sin Correo";

        //Bundle para fragments
        sharedpreferences = getApplicationContext().getSharedPreferences("Mypref1",MODE_PRIVATE);
        bundle = new Bundle();

        //Declaracion de Variables
        login_button = (LoginButton)findViewById(R.id.login_button);
        txtName =  headerView.findViewById(R.id.txtName);
        txtEmail =  headerView.findViewById(R.id.txtEmail);
        circleImageView = (CircleImageView) headerView.findViewById(R.id.profile_image);

        callbackManager = CallbackManager.Factory.create();
        login_button.setReadPermissions(Arrays.asList("email","public_profile","user_friends"));
        checklogin();


        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }


            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {

        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                txtName.setText(" ");
                txtEmail.setText(" ");
                circleImageView.setImageResource(0);
                Toast.makeText(MainActivity.this, "Sin inicio de Sesion", Toast.LENGTH_SHORT).show();
            }
            else {
                loaduserProfile(currentAccessToken);
            }
        }
    };

    private void loaduserProfile(AccessToken newAccessToken){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try{
                    first_name = object.getString("first_name");
                }catch (JSONException e){}
                try{
                    last_name = object.getString("last_name");
                }catch (JSONException e){}
                try{
                    id = object.getString("id");
                }catch (JSONException e){}
                try{
                    email = object.getString("email");
                }catch (JSONException e){ }

                try {
                    bundle.putString("Name",first_name);
                    bundle.putString("LName",last_name);
                    Fragment fragment = new Profile();
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().add(R.id.content_main,fragment).commit();
                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                    txtName.setText(first_name + " " + last_name);
                    txtEmail.setText(email);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();
                    Glide.with(MainActivity.this).load(image_url).into(circleImageView);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

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
        Fragment fragment = null;
        boolean FragmentSelect = false;


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Profile) {
            fragment = new Profile();
            FragmentSelect = true;
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if (FragmentSelect){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.content_main,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void checklogin(){
        if(AccessToken.getCurrentAccessToken()!= null){
            loaduserProfile(AccessToken.getCurrentAccessToken());

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

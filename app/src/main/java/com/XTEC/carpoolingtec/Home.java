package com.XTEC.carpoolingtec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentInteractionListener mListener;

    //Login con Facebook
    private LoginButton login_button;
    private CallbackManager callbackManager;

    //Variables Globales;
    private View headerview;
    private CircleImageView circleImageView;
    private TextView txtName, txtEmail;
    private EditText error;
    private Bundle bundle;
    private String first_name = "";
    private String last_name  = "";
    private String id = "";
    private String email = "Sin email";



    public Home() {
        // Required empty public constructor
    }
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View principal para los elementos de la pantalla principal
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        error = view.findViewById(R.id.error);
        //Iniciacion de los elementos del header
        try {
            txtName = (TextView) headerview.findViewById(R.id.txtName);
            txtEmail = (TextView) headerview.findViewById(R.id.txtEmail);
            circleImageView = (CircleImageView) headerview.findViewById(R.id.profile_image);
        }catch (Exception e){
            error.setText(e.getMessage());
        }

        //Iniciacion del boton de login
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton) view.findViewById(R.id.login_button);
        login_button.setReadPermissions(Arrays.asList("email", "public_profile"));
        login_button.setFragment(this);
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Login canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), "Login error", Toast.LENGTH_SHORT).show();
            }
        });

        checklogin();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                txtName.setText("Nombre");
                txtName.setText("example@android.com");
                circleImageView.setImageResource(R.mipmap.ic_launcher);
                Toast.makeText(getContext(), "Sesion cerrada", Toast.LENGTH_SHORT).show();
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
                    Bundle bundle = new Bundle();

                    bundle.putString("Name", first_name);
                    bundle.putString("LName",last_name);
                    bundle.putString("id",id);

                    /*AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this, "CarPoolinTEC", null ,1);
                    SQLiteDatabase db = admin.getWritableDatabase();

                    Cursor fila = db.rawQuery("SELECT idFB FROM Usuario WHERE idFB = "+id,null);
                    if (!fila.moveToFirst()){
                        Fragment fragment = new Register();
                        fragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().add(R.id.content_main,fragment).commit();
                        Toast.makeText(MainActivity.this,"Por favor complete el perfil para el registro",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"perfil no existe",Toast.LENGTH_LONG).show();
                    }*/

                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                    txtName.setText(first_name + " " + last_name);
                    txtEmail.setText(email);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();
                    Glide.with(getActivity()).load(image_url).into(circleImageView);

                }catch (Exception e){
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }
    public void checklogin(){
        try {
            if (AccessToken.getCurrentAccessToken() != null) {
                loaduserProfile(AccessToken.getCurrentAccessToken());

            }
        }catch (Exception e){
            error.setText(e.getMessage());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void logout(){
        LoginManager.getInstance().logOut();
    }

    //Geters and setters
    public void setHeaderview(View v){
        this.headerview = v;
    }

    public String getid() {
        return this.id;
    }
    public String getFirst_name(){
        return this.first_name;
    }
    public String getLast_name(){
        return this.last_name;
    }

}

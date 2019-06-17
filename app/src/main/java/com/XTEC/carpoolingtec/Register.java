package com.XTEC.carpoolingtec;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import Data.Usuario;
import connection.Post;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    private Button btnSave;
    private EditText nombre, apellidos, correo, cedula, carne, contraseña, contraseñaConf, telefono;
    private Post post;
    private static final String ip = "https://carpooling-xtec.herokuapp.com";


    public Register() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regiister, container, false);

        //Instancias de los elementos
        btnSave = (Button) view.findViewById(R.id.save_btn);
        nombre = (EditText) view.findViewById(R.id.txtName);
        apellidos = (EditText) view.findViewById(R.id.txtLastName);
        correo = (EditText) view.findViewById(R.id.txtMail);
        cedula =(EditText) view.findViewById(R.id.txtCedula);
        carne = (EditText) view.findViewById(R.id.txtId);
        contraseña = (EditText) view.findViewById(R.id.txtPass);
        contraseñaConf = (EditText) view.findViewById(R.id.txtPassConf);
        telefono = (EditText) view.findViewById(R.id.txtPhone);
        post = new Post();

        //Accion de los botones
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar(v);
            }
        });

        return  view;
    }

    //Metodo para agregar el ucuario{
    public void Registrar(View view) {

        boolean vacio = checkEmpty();
        if(!vacio){
            Toast.makeText(getContext(), "Por favor llene todos los espacios",Toast.LENGTH_SHORT).show();
        }else if (!contraseña.getText().toString().equals(contraseñaConf.getText().toString())){
            Toast.makeText(getContext(), "Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }else {
            Fragment fragment = new fb();
            ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();
        }

        //new HTTPAsyncTask().execute(ip+"/api/registrar");
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private boolean checkEmpty(){
        if(nombre.getText().toString().isEmpty()){
            return false;
        }else if(apellidos.getText().toString().isEmpty()){
            return false;
        }else if(correo.getText().toString().isEmpty()){
            return false;
        }else if(cedula.getText().toString().isEmpty()){
            return false;
        }else if(carne.getText().toString().isEmpty()){
            return false;
        }else if(contraseña.getText().toString().isEmpty()){
            return false;
        }else if(contraseñaConf.getText().toString().isEmpty()){
            return false;
        }else if(telefono.getText().toString().isEmpty()){
            return false;
        }else{
            return  true;
        }
    }

    private JSONObject buildJSONObject() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("CEDULA", Integer.parseInt(cedula.getText().toString()));
            jsonObject.put("CARNET", Integer.parseInt(carne.getText().toString()));
            jsonObject.put("NOMBRE", nombre.getText().toString());
            jsonObject.put("APELLIDO", apellidos.getText().toString());
            jsonObject.put("CORREO", correo.getText().toString());
            jsonObject.put("CONTRASEÑA", contraseña.getText().toString());
        } catch (JSONException e) {
            e.getMessage();
        }

        return jsonObject;
    }


    private class HTTPAsyncTask extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Usuario result) {
            super.onPostExecute(result);

        }
    }
}

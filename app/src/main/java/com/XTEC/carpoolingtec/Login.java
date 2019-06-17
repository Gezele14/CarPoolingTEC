package com.XTEC.carpoolingtec;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import Data.Auto;
import Data.Usuario;
import Data.solicitud;
import connection.Get;
import connection.Post;



public class Login extends Fragment {
    private EditText Usuario, Passwarod;
    private Button Login, register;
    private Post post;
    private Get get;
    private String t="";


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((MainActivity) getContext()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        Usuario = (EditText) view.findViewById(R.id.User);
        Passwarod = (EditText) view.findViewById(R.id.pass);

        Login = (Button) view.findViewById(R.id.Login_btn);
        register = (Button) view.findViewById(R.id.Register_btn);

        post = new Post();
        get = new Get();


        //Dar funcion a botones
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro(v);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
        return view;
    }

    //Metodo para ir a agregar el ucuario{
    public void registro(View view) {
        Fragment fragment = new Register();
        ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();
    }

    //Metodo para iniciar sesion
    public void login(View view) {
        boolean vacio = checkEmpty();
        if(!vacio){
            Toast.makeText(getContext(), "Por favor llene todos los espacios",Toast.LENGTH_SHORT).show();
        } else {
            try {
                if(Usuario.getText().toString().length() == 9){
                    t = "0";
                    new HTTPAsyncTask().execute("https://app-carpoolingtec.herokuapp.com/api/ingresar/"+t);
                }else if(Usuario.getText().toString().length() == 10){
                    t = "1";
                    new HTTPAsyncTask().execute("https://app-carpoolingtec.herokuapp.com/api/ingresar/"+t);
                }else{
                    Toast.makeText(getContext(), "Contraseña o carnet no validos",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private boolean checkEmpty(){
        if(Usuario.getText().toString().isEmpty()){
            return false;
        }else if(Passwarod.getText().toString().isEmpty()){
            return false;
        }else{
            return  true;
        }
    }

    private JSONObject buildJSONObject() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("coc", Integer.parseInt(Usuario.getText().toString()));
            jsonObject.put("CONTRASENA", Passwarod .getText().toString());

        } catch (JSONException e) {
            e.getMessage();
        }

        return jsonObject;
    }

    private class HTTPAsyncTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String json = post.httpPost(params[0], buildJSONObject());

                return json;

            } catch (JSONException e) {
                return null;
            } catch (IOException e) {
                ArrayList<String> error = new ArrayList<String>();
                error.add(e.getLocalizedMessage());
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject objFromServer = new JSONObject(result);

                if(result == null){
                    Toast.makeText(getContext(), "Error de conexion",Toast.LENGTH_SHORT).show();
                }else if(objFromServer.has("status")){
                    if(objFromServer.getInt("status") == 404){
                        Toast.makeText(getContext(), "El carnet ingresado no pertenece a la institución",Toast.LENGTH_SHORT).show();
                    }else if(objFromServer.getInt("status") == 400){
                        Toast.makeText(getContext(), "El carnet o la cedula ya estan asociados a una cuenta",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Error del Servidor",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    ((MainActivity)getContext()).usuario.setNombre(objFromServer.getString("NOMBRE") +" "+ objFromServer.getString("APELLIDO"));
                    ((MainActivity)getContext()).usuario.setId(objFromServer.getInt("IdPasajero"));
                    ((MainActivity)getContext()).usuario.setCorreo(objFromServer.getString("CORREO"));
                    ((MainActivity)getContext()).usuario.setCedula(objFromServer.getInt("CEDULA"));
                    ((MainActivity)getContext()).usuario.setCarnet(objFromServer.getInt("CARNET"));
                    Fragment fragment = new fb();
                    ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();
                    String id = Integer.toString(((MainActivity)getContext()).usuario.getId());
                    new getAutosServer().execute("https://app-carpoolingtec.herokuapp.com/api/autos/"+id);
                    new getAmigosServer().execute("https://app-carpoolingtec.herokuapp.com/api/amigos/"+id);
                    new getSoliServer().execute("https://app-carpoolingtec.herokuapp.com/api/listasolicitudes/"+id);
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }
    }

    private class getAutosServer extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String json = get.httpGet(params[0]);

            return json;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject objFromServer = new JSONObject(result);

                if(result == null){
                    Toast.makeText(getContext(), "Error de conexion",Toast.LENGTH_SHORT).show();
                }else if(objFromServer.has("status")){
                    if(objFromServer.getInt("status") == 404){
                        Toast.makeText(getContext(), "El carnet ingresado no pertenece a la institución",Toast.LENGTH_SHORT).show();
                    }else if(objFromServer.getInt("status") == 400){
                        Toast.makeText(getContext(), "El carnet o la cedula ya estan asociados a una cuenta",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Error del Servidor",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    int cantAutos = objFromServer.getJSONArray("autos").length();
                    ArrayList<Auto> listacarros = new ArrayList<>();
                    for(int i=0; i<=cantAutos-1; i++){
                        JSONObject temp = objFromServer.getJSONArray("autos").getJSONObject(i);
                        listacarros.add(new Auto(
                                temp.getInt("IdAuto"),
                                temp.getString("Marca"),
                                temp.getString("Modelos"),
                                temp.getString("Placa"),
                                temp.getInt("Capacidad")));
                    }
                    ((MainActivity)getContext()).usuario.setListaAutos(listacarros);
                    ((MainActivity)getContext()).usuario.setListaAutosOriginal(listacarros);
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }
    }
    private class getAmigosServer extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String json = get.httpGet(params[0]);

            return json;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject objFromServer = new JSONObject(result);

                if(result == null){
                    Toast.makeText(getContext(), "Error de conexion",Toast.LENGTH_SHORT).show();
                }else if(objFromServer.has("status")){
                    if(objFromServer.getInt("status") == 404){
                        Toast.makeText(getContext(), "El carnet ingresado no pertenece a la institución",Toast.LENGTH_SHORT).show();
                    }else if(objFromServer.getInt("status") == 400){
                        Toast.makeText(getContext(), "El carnet o la cedula ya estan asociados a una cuenta",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Error del Servidor",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    int cantAutos = objFromServer.getJSONArray("amigos").length();
                    ArrayList<Usuario> listaAmigo = new ArrayList<>();
                    for(int i=0; i<=cantAutos-1; i++){
                        JSONObject temp = objFromServer.getJSONArray("amigos").getJSONObject(i);
                        listaAmigo.add(new Usuario(
                                temp.getInt("IdAmigo"),
                                temp.getString("Nombre")+" "+ temp.getString("Apellido")));
                    }
                    ((MainActivity)getContext()).usuario.setListaAmigos(listaAmigo);
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }
    }

    private class getSoliServer extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String json = get.httpGet(params[0]);

            return json;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject objFromServer = new JSONObject(result);

                if(result == null){
                    Toast.makeText(getContext(), "Error de conexion",Toast.LENGTH_SHORT).show();
                }else if(objFromServer.has("status")){
                    if(objFromServer.getInt("status") == 404){
                        Toast.makeText(getContext(), "El carnet ingresado no pertenece a la institución",Toast.LENGTH_SHORT).show();
                    }else if(objFromServer.getInt("status") == 400){
                        Toast.makeText(getContext(), "El carnet o la cedula ya estan asociados a una cuenta",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Error del Servidor",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    int cantAutos = objFromServer.getJSONArray("solicitudes").length();
                    ArrayList<solicitud> listaSoli = new ArrayList<>();
                    for(int i=0; i<=cantAutos-1; i++){
                        JSONObject temp = objFromServer.getJSONArray("solicitudes").getJSONObject(i);
                        listaSoli.add(new solicitud(
                                temp.getString("Nombre")+" "+ temp.getString("Apellido"),
                                temp.getInt("IdAmigo")));
                    }
                    ((MainActivity)getContext()).usuario.setListaSolicitudes(listaSoli);
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }
    }

}




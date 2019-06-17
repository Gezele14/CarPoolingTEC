package com.XTEC.carpoolingtec;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import connection.Post;


public class addAuto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText Placa,marca, modelo, cantPers;
    private Button addAuto;
    private Post post;

    private OnFragmentInteractionListener mListener;

    public addAuto() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static addAuto newInstance(String param1, String param2) {
        addAuto fragment = new addAuto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_auto, container, false);

        //Intancias de los elementos
        marca = (EditText) view.findViewById(R.id.txtMarca);
        modelo = (EditText) view.findViewById(R.id.txtModelo);
        Placa = (EditText) view.findViewById(R.id.txtPlaca);
        cantPers = (EditText) view.findViewById(R.id.txtcantPers);

        post = new Post();

        addAuto = (Button) view.findViewById(R.id.save_btn);

        //Accion de los botones
        addAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addauto(v);
            }
        });

        return view;
    }

    private void addauto(View view){
        boolean vacio = checkEmpty();
        if(!vacio){
            Toast.makeText(getContext(), "Por favor llene todos los espacios",Toast.LENGTH_SHORT).show();
        }else{
            new HTTPAsyncTask().execute("https://app-carpoolingtec.herokuapp.com/api/r_auto/"+
                    Integer.toString(((MainActivity)getContext()).usuario.getId()));
        }
    }

    private boolean checkEmpty(){
        if(marca.getText().toString().isEmpty()){
            return false;
        }else if(Placa.getText().toString().isEmpty()){
            return false;
        }else if(modelo.getText().toString().isEmpty()){
            return false;
        }else if(cantPers.getText().toString().isEmpty()){
            return false;
        }else{
            return  true;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    private JSONObject buildJSONObject() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("PLACA", Placa.getText().toString());
            jsonObject.put("MARCA", marca.getText().toString());
            jsonObject.put("MODELO", modelo.getText().toString());
            jsonObject.put("CAPACIDAD", Integer.parseInt(cantPers.getText().toString()));
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
                        Toast.makeText(getContext(), "El carnet o la cedula ya estan asociad0s a una cuenta",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Error del Servidor",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Auto temp = new Auto(
                            objFromServer.getInt("IdAuto"),
                            objFromServer.getString("MARCA"),
                            objFromServer.getString("MODELO"),
                            objFromServer.getString("PLACA"),
                            objFromServer.getInt("CAPACIDAD"));
                    ((MainActivity)getContext()).usuario.addAuto(temp);
                    Dialogs dialogs = new Dialogs();
                    dialogs.Alert(getContext(),"","Auto agregado correctamente");
                    ((MainActivity)getContext()).getSupportFragmentManager().popBackStack();
                    ((MainActivity)getContext()).getSupportFragmentManager().popBackStack();
                    Fragment fragment = new Autos();
                    ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();

                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }
    }
}

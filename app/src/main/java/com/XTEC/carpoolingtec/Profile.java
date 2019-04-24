package com.XTEC.carpoolingtec;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Profile extends Fragment {

    private Spinner spiner1;
    private EditText Name,LName,usrName,usrId;
    private String sName,sLName,id;
    private Button guardar, cancelar;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sName = getArguments().getString("Name","");
            sLName = getArguments().getString("LName","");
            id = getArguments().getString("id","");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Name = (EditText) view.findViewById(R.id.ProfileName);
        LName = (EditText) view.findViewById(R.id.ProfileLName);
        usrName = (EditText) view.findViewById(R.id.ProfileusrName);
        usrId = (EditText) view.findViewById(R.id.profileID);
        spiner1 = (Spinner) view.findViewById(R.id.spinner);
        guardar = (Button) view.findViewById(R.id.btnSave);
        cancelar = (Button) view.findViewById(R.id.btnCancel);



        //Llenado del spinner
        String [] Categorias = {"Pasagero", "Conductor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_milayout,Categorias);
        spiner1.setAdapter(adapter);

        //Funciones botones
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(v);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelar(v);
            }
        });


       // Name.setEnabled(false);
        LName.setEnabled(false);
        usrName.setEnabled(false);
        usrId.setEnabled(false);

        return  view;
    }

    //Metodo para ir atras
    public void cancelar(View view){
        try {
            int g = ((MainActivity)getContext()).getFragmentManager().getBackStackEntryCount();
            Toast.makeText(getContext(), ""+g, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para agregar el ucuario{
    public void editar(View view) {
        Toast.makeText(getContext(),"Boton para registrar",Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void setId(String id){
        this.id = id;
    }
}

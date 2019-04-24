package com.XTEC.carpoolingtec;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    private Spinner spiner1;
    private EditText Name,LName,usrName,usrId;
    private Button btnSave;
    private String sName,sLName,id;


    public Register() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sName = getArguments().getString("Name","");
            sLName = getArguments().getString("LName","");
            id = getArguments().getString("ID","");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regiister, container, false);
        Name = (EditText) view.findViewById(R.id.ProfileNameR);
        LName = (EditText) view.findViewById(R.id.ProfileLNameR);
        usrName = (EditText) view.findViewById(R.id.ProfileusrNameR);
        usrId = (EditText) view.findViewById(R.id.profileIDR);
        spiner1 = (Spinner) view.findViewById(R.id.spinner);
        btnSave = (Button) view.findViewById(R.id.btnSave);

        //Llenado del spinner
        String [] Categorias = {"Pasagero", "Conductor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_milayout,Categorias);
        spiner1.setAdapter(adapter);

        //llenado de valores
        Name.setText(sName);
        Name.setEnabled(false);
        LName.setText(sLName);
        LName.setEnabled(false);
        usrName.setText("Meca14");
        usrId.setText("2016243863");

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
    public void Registrar(View view){
        try {

            String Nombre = Name.getText().toString();
            String Apellidos = LName.getText().toString();
            String UsrName = usrName.getText().toString();
            String idUni = usrId.getText().toString();
            String Categoria = spiner1.getSelectedItem().toString();

            if (!UsrName.isEmpty() && !idUni.isEmpty()) {
                ContentValues values = new ContentValues();
                values.put("idFB", id);
                values.put("Nombre", Nombre);
                values.put("Apellido", Apellidos);
                values.put("idUni", idUni);
                values.put("Categoria", Categoria);

                Toast.makeText(getContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Por favor llenar todos los datos", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            //Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

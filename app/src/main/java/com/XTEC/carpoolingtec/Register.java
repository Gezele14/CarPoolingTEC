package com.XTEC.carpoolingtec;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
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
        btnSave = (Button) view.findViewById(R.id.save_btn);

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
        Fragment fragment = new fb();
        ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

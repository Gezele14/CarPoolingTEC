package com.XTEC.carpoolingtec;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    private EditText Usuario, Passwarod;
    private Button  Login, register;


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login,container,false);

        Usuario = (EditText) view.findViewById(R.id.User);
        Passwarod = (EditText) view.findViewById(R.id.pass);

        Login = (Button) view.findViewById(R.id.Login_btn);
        register = (Button) view.findViewById(R.id.Register_btn);


        //Dar funcion a botones
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro(v);
            }
        });
        return view;
    }

    //Metodo para ir a agregar el ucuario{
    public void registro(View view) {
        Fragment fragment = new Register();
        ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}



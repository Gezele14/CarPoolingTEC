package com.XTEC.carpoolingtec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import Data.Auto;
import Data.solicitud;
import adapters.friendAdapter;
import connection.Get;
import connection.Post;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultSearch.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultSearch extends Fragment implements friendAdapter.Onclick {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView search;
    private friendAdapter adapter;
    private Get get;
    private Post post;
    private JSONObject jsonObject = new JSONObject();


    private OnFragmentInteractionListener mListener;

    public ResultSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultSearch newInstance(String param1, String param2) {
        ResultSearch fragment = new ResultSearch();
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
        View view = inflater.inflate(R.layout.fragment_result_search, container, false);


        search = (RecyclerView) view.findViewById(R.id.SearchRecycler);
        search.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new friendAdapter(((MainActivity)getContext()).usuario.getListaBusqueda(),this);
        search.setAdapter(adapter);

        post = new Post();

        return view;
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

    @Override
    public void onClickfriendAdapter(final int pos) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        try {
                            jsonObject.put("IdReceptor", ((MainActivity)getContext()).usuario.getListaBusqueda().get(pos).getId());
                            jsonObject.put("IdEmisor", ((MainActivity)getContext()).usuario.getId());
                            new HTTPAsyncTask().execute("https://app-carpoolingtec.herokuapp.com/api/c_solicitud");

                        } catch (JSONException e) {
                            e.getMessage();
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("¿Desea enviar la solicitud de amistad?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class HTTPAsyncTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String json = post.httpPost(params[0], jsonObject);

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
                Toast.makeText(getContext(), jsonObject.toString(),Toast.LENGTH_SHORT).show();
                if(result == null){
                    Toast.makeText(getContext(), "Error de conexion",Toast.LENGTH_SHORT).show();
                }else if(objFromServer.has("status")){
                    if(objFromServer.getInt("status") == 404){
                        Toast.makeText(getContext(), "El carnet ingresado no pertenece a la institución",Toast.LENGTH_SHORT).show();
                    }else if(objFromServer.getInt("status") == 400){
                        Toast.makeText(getContext(), "El carnet o la cedula ya estan asociad0s a una cuenta",Toast.LENGTH_SHORT).show();
                    }else if(objFromServer.getInt("status") == 201){
                        Dialogs dialogs = new Dialogs();
                        dialogs.Alert(getContext(),"","Se envio la solicitud con exito");
                    }else{
                        Toast.makeText(getContext(), "Error del Servidor",Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }
    }


}

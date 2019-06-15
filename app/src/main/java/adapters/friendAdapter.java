package adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.XTEC.carpoolingtec.R;

import java.util.ArrayList;

import Data.Usuario;

public class friendAdapter extends RecyclerView.Adapter<friendAdapter.ViewHolderData> {

    private ArrayList<Usuario> friendData;
    private Onclick mOnClick;

    public friendAdapter(ArrayList<Usuario> friendData, Onclick mOnClick) {
        this.friendData = friendData;
        this.mOnClick = mOnClick;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend,null,false);
        return new ViewHolderData(view, mOnClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData viewHolderData, int i) {
        viewHolderData.asignarData(friendData.get(i));
    }

    @Override
    public int getItemCount() {
        return friendData.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView nombre, telefono;
        Onclick onClickListener;

        public ViewHolderData(@NonNull View itemView, Onclick onClickListener) {
            super(itemView);
            nombre = itemView.findViewById(R.id.friend_name);
            telefono = itemView.findViewById(R.id.friend_phone);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        public void asignarData(Usuario s) {
            nombre.setText("Nombre: "+s.getNombre());
            telefono.setText("Telefono: "+s.getTelefono());
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClickfriendAdapter(getAdapterPosition());
        }


    }

    public interface  Onclick{
        void onClickfriendAdapter(int pos);
    }
}

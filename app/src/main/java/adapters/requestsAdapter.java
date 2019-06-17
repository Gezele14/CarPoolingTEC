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
import Data.solicitud;

public class requestsAdapter extends RecyclerView.Adapter<requestsAdapter.ViewHolderData> {

    private ArrayList<solicitud> friendData;
    private requestsAdapter.Onclick mOnClick;

    public requestsAdapter(ArrayList<solicitud> friendData, requestsAdapter.Onclick mOnClick) {
        this.friendData = friendData;
        this.mOnClick = mOnClick;
    }

    public requestsAdapter(ArrayList<solicitud> friendData) {
        this.friendData = friendData;
        this.mOnClick = mOnClick;
    }

    @NonNull
    @Override
    public requestsAdapter.ViewHolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend,null,false);
        return new requestsAdapter.ViewHolderData(view, mOnClick);
    }

    @Override
    public void onBindViewHolder(@NonNull requestsAdapter.ViewHolderData viewHolderData, int i) {
        viewHolderData.asignarData(friendData.get(i));
    }

    @Override
    public int getItemCount() {
        return friendData.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView nombre, telefono;
        requestsAdapter.Onclick onClickListener;

        public ViewHolderData(@NonNull View itemView, requestsAdapter.Onclick onClickListener) {
            super(itemView);
            nombre = itemView.findViewById(R.id.friend_name);
            telefono = itemView.findViewById(R.id.friend_phone);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        public void asignarData(solicitud s) {
            nombre.setText("Nombre: "+s.getNombre());
            telefono.setText("");
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClickrequestAdapter(getAdapterPosition());
        }


    }

    public interface  Onclick{
        void onClickrequestAdapter(int pos);
    }

}

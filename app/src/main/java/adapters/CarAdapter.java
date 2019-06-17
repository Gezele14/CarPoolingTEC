package adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.XTEC.carpoolingtec.R;

import java.util.ArrayList;

import Data.Auto;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolderData> {

    private ArrayList<Auto> carData;
    private Onclick mOnClick;

    public CarAdapter(ArrayList<Auto> carData,Onclick mOnClick) {
        this.carData = carData;
        this.mOnClick =  mOnClick;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_auto,null,false);

        return new ViewHolderData(view, mOnClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData viewHolderData, int i) {
        viewHolderData.asignarData(carData.get(i));
    }

    @Override
    public int getItemCount() {
        return carData.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Marca,Modelo,Placa;
        Onclick onClickListener;

        public ViewHolderData(@NonNull View itemView, Onclick onClickListener) {
            super(itemView);
            Marca = itemView.findViewById(R.id.Marca);
            Modelo = itemView.findViewById(R.id.Modelo);
            Placa = itemView.findViewById(R.id.Placa);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);

        }

        public void asignarData(Auto s) {
            Marca.setText("Marca: "+s.getMarca());
            Modelo.setText("Modelo: "+s.getModelos());
            Placa.setText("Placa: "+s.getPlaca());
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClickcarAdapter(getAdapterPosition());
        }
    }

    public interface  Onclick{
        void onClickcarAdapter(int pos);
    }
}

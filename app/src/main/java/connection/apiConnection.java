package connection;

import android.support.customtabs.PostMessageService;

import java.util.List;

import Data.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiConnection {

    public apiConnection() {
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-carpoolingtec.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService servicio = retrofit.create(apiService.class);
        Call<List<Usuario>> call = servicio.getPost();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                for(Usuario post : response.body()) {
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
            }
        });
    }
}

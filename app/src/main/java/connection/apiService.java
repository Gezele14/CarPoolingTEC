package connection;

import java.util.List;

import Data.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;

public interface apiService {

    String API_ROUTE = "/api/admin/all";

    @GET(API_ROUTE)
    Call< List<Usuario> > getPost();

}

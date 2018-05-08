package upc.pe.edu.parcialproject.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import upc.pe.edu.parcialproject.Model.Post;

public interface NetworkAPI {
    @GET("posts")
    Call<List<Post>> getAllPosts();

}

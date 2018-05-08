package upc.pe.edu.parcialproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import upc.pe.edu.parcialproject.Adapter.RvAdapter;
import upc.pe.edu.parcialproject.Model.Post;
import upc.pe.edu.parcialproject.Network.NetworkAPI;

public class RvActivity extends AppCompatActivity implements Callback<List<Post>> {

    RecyclerView recyclerViewPosts;

    ArrayList<Post> postList = new ArrayList<>();
    private Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private ArrayList<Post>  mPosts;

    private static final String LOGTAG = "LOG GG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        LoadPosts();

        recyclerViewPosts = findViewById(R.id.recyclerAct);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));

        RvAdapter adapter = new RvAdapter(postList);
        recyclerViewPosts.setAdapter(adapter);




        


    }

    private void LoadPosts() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NetworkAPI networkAPI = retrofit.create(NetworkAPI.class);
        Call<List<Post>> call = networkAPI.getAllPosts();
        call.enqueue(this);



    }


    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {


        if(response.isSuccessful()) {
            List<Post> postList1 = response.body();
            for (Post post : postList1) {
                postList.add(post);
            }
            Log.d(LOGTAG, "TAMAÑO POSTLIST1111 asdas" + postList1.size()+ "TAMAÑO POST LISasT 2 " + postList.size());
        }else{
            System.out.println(response.errorBody());
            Log.d(LOGTAG, "LUL");
        }


        //if
        /*List<Post> postList1 = response.body();
            for (Post post : postList1) {
                postList.add(post);
                Log.d(LOGTAG, "ENTRÓ AL ONRESPONSE " + post.getTitle());

            }*/

    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        t.printStackTrace();
        Log.d(LOGTAG, "ENTRÓ AL ONFAIL");
    }
}

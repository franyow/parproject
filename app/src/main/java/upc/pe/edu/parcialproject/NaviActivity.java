package upc.pe.edu.parcialproject;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

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
import upc.pe.edu.parcialproject.Fragments.FirstFragment;
import upc.pe.edu.parcialproject.Fragments.SecondFragment;
import upc.pe.edu.parcialproject.Model.Post;
import upc.pe.edu.parcialproject.Network.NetworkAPI;

public class NaviActivity extends AppCompatActivity implements Callback<List<Post>>, FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;
    private Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private ArrayList<Post>  mPosts;

    RecyclerView recyclerViewPosts;

    ArrayList<Post> postList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment myfragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new FirstFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new SecondFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new FirstFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);



        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().add(R.id.container_main,new FirstFragment()).commit();




        recyclerViewPosts = findViewById(R.id.recyclerList);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RvAdapter adapter = new RvAdapter(postList);
        recyclerViewPosts.setAdapter(adapter);



        LoadPosts();

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
        if(response.isSuccessful()){
            List<Post> postList1 = response.body();
            for (Post post : postList1){
                postList.add(post);
            }
            //asdaadapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        t.printStackTrace();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

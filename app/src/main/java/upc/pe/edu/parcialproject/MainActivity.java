package upc.pe.edu.parcialproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import upc.pe.edu.parcialproject.Adapter.RvAdapter;

import upc.pe.edu.parcialproject.Model.Post;

public class MainActivity extends AppCompatActivity {

    //RecyclerView recyclerViewPosts;

    //ArrayList<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button;
        button = findViewById(R.id.buttonIngr);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RvActivity.class);
                startActivity(intent);
            }
        });

        /*recyclerViewPosts = findViewById(R.id.recyclerList);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));

        RvAdapter adapter = new RvAdapter(postList);
        recyclerViewPosts.setAdapter(adapter);

        Post post;

        post = new Post();
        post.setId("1");
        post.setTitle("Test Title");

        postList.add(post);*/
    }
}

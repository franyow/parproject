package upc.pe.edu.parcialproject.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import upc.pe.edu.parcialproject.Model.Post;
import upc.pe.edu.parcialproject.R;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder>{

    ArrayList<Post> listDatos;

    public RvAdapter(ArrayList<Post> listDatos) {
        this.listDatos = listDatos;
    }

    //enlaza el item con el adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,null,false);

        return new ViewHolder(view);
    }

    //comunica el adapter con el viewholder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(listDatos.get(position).getTitle());
        holder.id.setText(listDatos.get(position).getId());
        Post p  = listDatos.get(position);

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView id;

        public ViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.textTitle);
            id = itemView.findViewById(R.id.textId);
        }
    }
}

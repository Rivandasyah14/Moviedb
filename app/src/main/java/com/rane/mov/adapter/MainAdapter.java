package com.rane.mov.adapter;

import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rane.mov.DetailActivity;
import com.rane.mov.R;
import com.rane.mov.model.Model;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<Model> mdata = new ArrayList<>();

    public void setData (ArrayList<Model> items) {
        mdata.clear();
        mdata.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.bind(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterView;
        TextView nameView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.poster_view);
            nameView = itemView.findViewById(R.id.name_view);
        }

        void bind(final Model model) {
            Glide.with(itemView.getContext()).load(model.getPoster_path()).apply(new RequestOptions()
                    .placeholder(R.drawable.default_picture)).override(350,550)
                    .error(R.drawable.default_picture).into(posterView);

            nameView.setText(model.getName());
            itemView.setOnClickListener(new ClickItemAdapter(new ClickItemAdapter.onCallback() {
                @Override
                public void onItemClicked(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.Extra, model);
                    view.getContext().startActivity(intent);
                }
            }));
            Log.d("datadata", "bind : "+model.getPoster_path());
        }
    }
}

package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    //Constructor for context and posts
    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void addAll(List<Post> allposts) {
        posts.addAll(allposts);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public PostsAdapter(List<Post> posts) {
        this.posts = posts;
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUser;
        private ImageView ivPicture;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            //Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUser.setText(post.getUser().getUsername());
            ParseFile img = post.getImage();
            if (img != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivPicture);
            }
        }

        @Override
        public void onClick(View view) {

            //gets of post clicked on
            int position = getAdapterPosition();

            //checks if position is valid
            if (position != RecyclerView.NO_POSITION) {
                // gets movie at position
                Post post = posts.get(position);

                Toast.makeText(context, "Post selected", Toast.LENGTH_SHORT).show();
                // creates intent for  new activity
                Intent intent = new Intent(context, PostDetailsActivity.class);
                // uses parceler

                intent.putExtra("description",post.getDescription());
                intent.putExtra("image",post.getImage());
                intent.putExtra("timestamp",post.getCreatedAt());
                intent.putExtra("username",post.getUser().getUsername());
                // shows activity
                context.startActivity(intent);
            }
        }
    }
}

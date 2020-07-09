package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

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

    public PostsAdapter (List <Post> posts){
        this.posts = posts ;
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUser;
        private ImageView ivPicture;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser=itemView.findViewById(R.id.tvUser);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            ivPicture=itemView.findViewById(R.id.ivPicture);
        }

        public void bind(Post post) {
            //Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUser.setText(post.getUser().getUsername());
            ParseFile img = post.getImage();
            if (img != null){
                Glide.with(context).load(post.getImage().getUrl()).into(ivPicture);
            }
        }
    }
    // Clean all elements of the recycler
    /*public static void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public static void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }*/
}

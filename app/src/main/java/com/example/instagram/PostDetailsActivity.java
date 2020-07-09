package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    private TextView tvUser;
    private ImageView ivPicture;
    private TextView tvDescription;
    private TextView tvTimestamp;
    private String description;
    private Date createdAt;
    private String username;
    private ParseFile image;

    private Context context;

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvUser = findViewById(R.id.tvUser);
        tvDescription = findViewById(R.id.tvDescription);
        ivPicture = findViewById(R.id.ivPicture);
        tvTimestamp = findViewById(R.id.tvTimestamp);

        description = getIntent().getStringExtra("description");
        image = (ParseFile) getIntent().getExtras().get("image");
        createdAt = (Date) getIntent().getExtras().get("timestamp");
        username = getIntent().getStringExtra("username");

        tvDescription.setText(description);
        tvUser.setText(username);
        tvTimestamp.setText(createdAt.toString());
        Glide.with(this).load(image.getUrl()).into(ivPicture);
    }
}
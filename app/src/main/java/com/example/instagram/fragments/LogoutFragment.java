package com.example.instagram.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.LoginActivity;
import com.example.instagram.R;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@ParseClassName("User")
public class LogoutFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Button btnLogout;
    private Button btnTakeProfilePicture;
    private ImageView ivProfilePicture;

    private ImageView ivPP;
    private ParseFile img;
    private TextView tvUser;

    public LogoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogoutFragment newInstance(String param1, String param2) {
        LogoutFragment fragment = new LogoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogout=view.findViewById(R.id.btnLogout);
        btnTakeProfilePicture=view.findViewById(R.id.btnTakeProfilePicture);
        ivProfilePicture=view.findViewById(R.id.ivProfilePic);
        tvUser=view.findViewById(R.id.tvUser);
        ivPP=view.findViewById(R.id.ivPP);

        ParseUser user = ParseUser.getCurrentUser();
        img = user.getParseFile("profilePic");
        tvUser.setText(user.getUsername());

        if(img!=null){
            ivProfilePicture.setVisibility(View.GONE);
            ivPP.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(img.getUrl()).into(ivPP);
        }
        else{
            ivProfilePicture.setVisibility(View.VISIBLE);
            ivPP.setVisibility(View.GONE);
        }



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                if(currentUser == null){
                    Intent i = new Intent(getContext(), LoginActivity.class);
                    startActivity(i);
                }
            }
        });



    }
}
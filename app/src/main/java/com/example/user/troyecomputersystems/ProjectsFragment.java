package com.example.user.troyecomputersystems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ProjectsFragment extends Fragment {

    private View ProjectView;
    private RecyclerView myProjectList;
    private DatabaseReference ProjectRef, proRef;
    private String curentID;
    private  FirebaseAuth mAuth;



    public ProjectsFragment() {
        // Required empty public constructor
    }


    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ProjectView =  inflater.inflate(R.layout.fragment_projects, container, false);
        myProjectList = (RecyclerView) ProjectView.findViewById(R.id.recyleProject);
        myProjectList.setLayoutManager(new LinearLayoutManager((getActivity())));

        mAuth = FirebaseAuth.getInstance();
       curentID = mAuth.getCurrentUser().getUid();

        ProjectRef = FirebaseDatabase.getInstance().getReference().child("Project").child(curentID);


        return ProjectView;

    }


        }



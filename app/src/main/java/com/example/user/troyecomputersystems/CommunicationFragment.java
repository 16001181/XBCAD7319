package com.example.user.troyecomputersystems;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;


public class CommunicationFragment extends Fragment {

    private List<Messages> messagesList;

    private List<String> listDataHeader;

    private HashMap<String, List<String>> listHash;

    public static ArrayList<String> Date = new ArrayList<String>();
    public static ArrayList<String> Message = new ArrayList<String>();
    public static ArrayList<String> EmployeeName = new ArrayList<String>();
    public static ArrayList<String> Time = new ArrayList<String>();
    private ExpandableListView MessageView;
    private ListView listView;
    DatabaseReference databaseReference;
    private Button SendMessage;
    private EditText NewMessage;
    private TextView MessagesText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_communicate, container, false);



        listDataHeader = new ArrayList<>();
       // listView = (ListView) view.findViewById(R.id.ListViewID);
        listHash = new HashMap<>();

        //FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Communication");

        messagesList = new ArrayList<>();

        SendMessage = view.findViewById(R.id.SendMessage);
        NewMessage = view.findViewById(R.id.NewMessage);
       // listView = view.findViewById(R.id.ListViewID);

        final ArrayList<String> friends = new ArrayList<String>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                messagesList.clear();
                for (DataSnapshot player : dataSnapshot.getChildren()) {
                    Log.v(TAG,""+ player.getKey()); //displays the key for the node
                    Log.v(TAG,""+ player.child("Date").getValue());
                    Log.v(TAG,""+ player.child("CommunicationMessage").getValue());
                    Log.v(TAG,""+ player.child("EmployeeName").getValue());
                    Log.v(TAG,""+ player.child("Time").getValue());
                    Date.add(player.child("Date").getValue().toString());
                    Message.add(player.child("CommunicationMessage").getValue().toString());
                    EmployeeName.add(player.child("EmployeeName").getValue().toString());
                    Time.add(player.child("Time").getValue().toString());

                }

                initData(Date,Message,EmployeeName,Time,view);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewMessage = v.findViewById(R.id.NewMessage);
                  //String sMessage = NewMessage.getText().toString();

                java.util.Date d = new Date();
                String sDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                Users users = new Users();
                String sName = users.getName();

                //if (TextUtils.isEmpty(sMessage) ) {
                 //   Toast.makeText(getActivity(), "No message entered!" + sName,
                 //           Toast.LENGTH_SHORT).show();
                 //   return;
               // }


                    System.out.println("********" + users.getName());
                    System.out.println("********" + sDate);

                    Messages messages = new Messages("testing",sDate,users.getName(),"12:56");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String key = database.getReference("Communication").push().getKey();
                    //DatabaseReference databaseReference = database.getReference("Communication");
                    databaseReference.child(key).child("CommunicationMessage").setValue("Hello Howsit Stew");
                    databaseReference.child(key).child("Date").setValue(sDate);
                    databaseReference.child(key).child("EmployeeName").setValue("John");
                    databaseReference.child(key).child("Time").setValue("12:26");


                    databaseReference.push();

                //calls login fragment
               // FragmentTransaction fr = getFragmentManager().beginTransaction();
                //fr.replace(R.id.fragment_container, new LoginFragment());
                //Toast.makeText(getActivity(), "Registration Successful!!", Toast.LENGTH_SHORT).show();
                //fr.commit();



            }
        });




        return view;
    }

    public void onStart(){


        super.onStart();
    }

    private void initData(ArrayList<String> Date, ArrayList<String> Message, ArrayList<String> EmployeeName, ArrayList<String> Time, View view) {

        MessagesText = view.findViewById(R.id.MessagesText);
        String sMessages = "";









        for(int i = 0;i < Date.size();i++)
        {
            System.out.println("gffffffffffffffffffffffffffffffff " + i);


            List<String> MessageAll = new ArrayList<>();

            MessageAll.add("Name: " + EmployeeName.get(i) + "\n" + "Date: " + Date.get(i) +"\n" + "Time: " + Time.get(i) + "\n" + "Message: " + Message.get(i)+"\n\n" );
            sMessages = "\n" + sMessages + MessageAll.toString().replace("[", "").replace("]", "");
            //MessagesText.setText(MessageAll.toString());





        }
        MessagesText.setText(sMessages);




        // List<String> androidStudio = new ArrayList<>();
        // androidStudio.add("hello");





    }



}


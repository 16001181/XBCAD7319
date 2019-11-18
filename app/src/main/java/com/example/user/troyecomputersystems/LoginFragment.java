package com.example.user.troyecomputersystems;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class LoginFragment extends Fragment {

    String usernameDatabase = "";
    String passwordDatabase = "";

    public LoginFragment() {
        // Required empty public constructor
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);


        final EditText Username, Password;
        Button Login;

        Username = view.findViewById(R.id.edtUsername);
        Password = view.findViewById(R.id.edtPassword);
        Login = view.findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userName = Username.getText().toString();
                final String password = Password.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Information Missing!!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LoginDetails");
                myRef.orderByChild("Username").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                    String usernameDatabase = "";
                    String passwordDatabase = "";
                    String nameDatabase = "";

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                            Users user = new Users();
                            user.setID(childDataSnapshot.getKey());
                            usernameDatabase = childDataSnapshot.child("Username").getValue().toString();
                            passwordDatabase = childDataSnapshot.child("Password").getValue().toString();
                            nameDatabase = childDataSnapshot.child("Name").getValue().toString();
                        }
                        if (userName.equals(usernameDatabase) && passwordDatabase.equals(password)) {
                            Toast.makeText(getActivity(), "Successful Login!!", Toast.LENGTH_SHORT).show();
                            Users user = new Users();
                            user.setName(nameDatabase);
                            System.out.println("*********************************"+user.getName());
                            FragmentTransaction fr = getFragmentManager().beginTransaction();
                            fr.replace(R.id.fragment_container, new CommunicationFragment());
                            fr.addToBackStack(null)
                              .commit();

                            // Toast.makeText(getActivity(), "Swipe from the left of the screen to the right to open menu!!", Toast.LENGTH_LONG).show();
                            //FragmentManager fragmentManager = getFragmentManager();
                            // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            // //Navigation_DrawerFragment navigation_drawerFragment = new Navigation_DrawerFragment();
                            //fragmentTransaction.replace(R.id.fragment_container, navigation_drawerFragment)
                            //        .commit();
                            // FragmentManager fragmentManagers = getFragmentManager();
                            // FragmentTransaction fragmentTransactions = fragmentManagers.beginTransaction();
                            // HomeFragment navigation_drawerFragments = new HomeFragment();
                            //  fragmentTransactions.replace(R.id.ShowFragments, navigation_drawerFragments)
                            //   .addToBackStack(null)
                            //   .commit();
                        } else {
                            Toast.makeText(getActivity(), "Incorrect user information, OR plz register!!", Toast.LENGTH_SHORT).show();
                            passwordDatabase = "";
                            usernameDatabase = "";
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }}
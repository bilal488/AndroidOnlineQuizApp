package com.example.bilal.androidonlinequizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bilal.androidonlinequizapp.BroadcastReveiver.AlarmReceiver;
import com.example.bilal.androidonlinequizapp.Common.Common;
import com.example.bilal.androidonlinequizapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    //T1-->Start//
    MaterialEditText edtNewUser,edtNewPassword,edtNewEmail;  // for sign up
    MaterialEditText edtUser,edtPassword;   //for signin

    Button btnSingUp,btnSignIn;

    FirebaseDatabase database;
    DatabaseReference users;
    //T1-->End//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //T6-->Start//
        registerAlarm();
        //T6-->End//

        //T1-->Start//
        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUser = (MaterialEditText)findViewById(R.id.edtUser);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);

        btnSignIn = (Button)findViewById(R.id.btn_sign_in);
        btnSingUp = (Button)findViewById(R.id.btn_sign_up);

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignUpDialod();
            }

        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin(edtUser.getText().toString(),edtPassword.getText().toString());
                
            }
        });
        //T1<End//

    }

    //T6-->Start//
    private void registerAlarm() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.HOUR_OF_DAY,18);  // 9 hour
        calendar.set(java.util.Calendar.MINUTE,55);   //
        calendar.set(java.util.Calendar.SECOND,0);

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }
    //T6-->End//

    //T1-->Start//
    private void signin(final String user, final String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(user).exists()){
                    if (!user.isEmpty()){
                        User login = dataSnapshot.child(user).getValue(User.class);
                        if (login.getPassword().equals(pwd)){
                            Intent homeActivity = new Intent(MainActivity.this,Home.class);
                            Common.currentUser = login;
                            startActivity(homeActivity);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Please enter your user name", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    //T1-->End//

    //T1-->Start//
    private void showSignUpDialod() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Sign Up");
        alertDialog.setMessage("Please Fill full information");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout,null);

        edtNewUser = (MaterialEditText)sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewEmail = (MaterialEditText)sign_up_layout.findViewById(R.id.edtNewEmail);
        edtNewPassword = (MaterialEditText)sign_up_layout.findViewById(R.id.edtNewPassword);

        alertDialog.setView(sign_up_layout);
        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final User user = new User(edtNewUser.getText().toString(),edtNewPassword.getText().toString(),edtNewEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUsername()).exists()){
                            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            users.child(user.getUsername()).setValue(user);
                            Toast.makeText(MainActivity.this, "User registration success!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();

    }
    //T1-->End//
}

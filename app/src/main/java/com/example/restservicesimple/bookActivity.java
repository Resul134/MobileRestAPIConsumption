package com.example.restservicesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bookActivity extends AppCompatActivity {


    public static final String BOOKING = "BOOKING";
    private FirebaseAuth mAuth;

    private Rooms avalaibleRooms;
    private TextView messageView;
    private Reservations avalaibleReserv;

    TextView responseView;

    EditText fromTime;
    EditText toTime;
    EditText userID;
    EditText purpose;
    EditText roomID;

    Button bookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        Intent intent = getIntent();
        //avalaibleRooms = (Rooms) intent.getSerializableExtra(BOOKING);
        avalaibleReserv = (Reservations) intent.getSerializableExtra(BOOKING);



        messageView = findViewById(R.id.output);

        bookButton = findViewById(R.id.bookButton);
        mAuth = FirebaseAuth.getInstance();
        Log.d("Firebase", String.valueOf(mAuth.getCurrentUser()));

        if (mAuth.getCurrentUser() != null)
        {
            bookButton.setVisibility(View.VISIBLE);

        }

        //messageView.setText("Name: " + avalaibleRooms.getName() +"\nDescription: " + avalaibleRooms.getDescription() + "\nCapacity: " + avalaibleRooms.getCapacity());
        messageView.setText("From time: " + avalaibleReserv.getFromTime() +"\nTo time: " + avalaibleReserv.getToTime() + "\nuserID: "
                + avalaibleReserv.getUserId() + "\nPurpose: " + avalaibleReserv.getPurpose() + "\nroomID: " + avalaibleReserv.getRoomId());







    }

    public void bookARoom(View view){



        fromTime = findViewById(R.id.fromTime);
        toTime = findViewById(R.id.toTime);
        userID = findViewById(R.id.userId);
        purpose = findViewById(R.id.purpose);
        roomID = findViewById(R.id.roomId);


        String fromTimeString =  fromTime.getText().toString();
        String toTimeString = toTime.getText().toString();
        String userIDString = userID.getText().toString();
        String purposeString = purpose.getText().toString();
        String roomIDstring = roomID.getText().toString();


        int fromTimeParsed;
        int toTimeParsed;

        int roomIDparsed;


        fromTimeParsed = Integer.parseInt(fromTimeString);
        toTimeParsed = Integer.parseInt(toTimeString);
        roomIDparsed = Integer.parseInt(roomIDstring);





        ReservationServ reservationService = AppUtils.getReservations();

        Reservations reserv = new Reservations(fromTimeParsed, toTimeParsed, userIDString, purposeString, roomIDparsed);

        Call<Integer> postReservation  = reservationService.saveBookBody(reserv);
        postReservation.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful())
                {
                    Integer theNewReservation = response.body();
                    Log.d("MYRESERVATIONS", theNewReservation.toString());
                    Toast.makeText(bookActivity.this, "Reservation added: " + theNewReservation.toString(), Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String error = "Error: " + response.code() + " " + response.message();
                    Log.e("MYRESERVATIONS", error);
                    Toast.makeText(bookActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("MYRESERVATIONS", t.getMessage());
            }
        });
    }

    public void goBack(View view){

        finish();

    }




}

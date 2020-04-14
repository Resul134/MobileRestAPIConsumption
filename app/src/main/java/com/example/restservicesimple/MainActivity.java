package com.example.restservicesimple;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MYROOMS";
    private static final String KotlinString = "Kotlin";
    TextView mainMessageTextView;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Har låst orientation fordi mainactivity ikke er glad for landscape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mainMessageTextView = findViewById(R.id.mainMessageTextView);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "woow - resul", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        SwipeRefreshLayout refreshLayout = findViewById(R.id.mainSwiperefresh);
        refreshLayout.setOnRefreshListener(() -> {
            getAllFreeReservations();
            refreshLayout.setRefreshing(false);
        });


    }





    public void gotoLoginPage(View view){
        Intent intent = new Intent(MainActivity.this, LoginPageActivity.class);
        startActivity(intent);

    }

    public void gotoDeletePage(View view){
        startActivity(new Intent(MainActivity.this, deleteActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu code with a switch case.
        switch (item.getItemId()){
            case R.id.infoItem:
                Toast.makeText(MainActivity.this, "This application was made by Resul - To refresh the list and return to reservations, swipe down.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.zealandItem:
                Toast.makeText(MainActivity.this, "Sponsored by Zealand and Anders", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bookInfoItem:
                Toast.makeText(MainActivity.this, "Click on an element on the list, to access booking page.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kotlinItem:
                String message = "This activity was created in the purpose of showing, that kotlin could also be used and is" +
                        "an effective programming tool - It was also a part of the assignment";
                Intent intent = new Intent(MainActivity.this, KotlinTest.class);
                intent.putExtra(KotlinString, message);
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllFreeReservations();
    }



    public void getAllFreeReservations()
    {

        ReservationServ resService = AppUtils.getReservations();
        Call<List<Reservations>> getAllRoomsCall = resService.getAllReservations();


        getAllRoomsCall.enqueue(new Callback<List<Reservations>>() {
            @Override
            public void onResponse(Call<List<Reservations>> call, Response<List<Reservations>> response) {

                if (response.isSuccessful()) {
                    List<Reservations> allReservations = response.body();
                    Log.d(LOG_TAG, "onResponse: " + allReservations.toString());
                    populateRecyclerViewReservation(allReservations);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    mainMessageTextView.setText(message);

                }

            }

            @Override
            public void onFailure(Call<List<Reservations>> call, Throwable t) {

                Log.e(LOG_TAG, t.getMessage());
                mainMessageTextView.setText(t.getMessage());

            }


        });



    }


    public void getAllFreeRooms(View view)
    {

        RoomService resService = AppUtils.getRoomService();
        Call<List<Rooms>> getAllRoomsCall = resService.getfreeRoomsByTime(19999323);


        getAllRoomsCall.enqueue(new Callback<List<Rooms>>() {
            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {

                if (response.isSuccessful()) {
                    List<Rooms> allRooms = response.body();
                    Log.d(LOG_TAG, "onResponse: " + allRooms.toString());
                    populateRecyclerViewRooms(allRooms);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    mainMessageTextView.setText(message);

                }

            }

            @Override
            public void onFailure(Call<List<Rooms>> call, Throwable t) {

                Log.e(LOG_TAG, t.getMessage());
                mainMessageTextView.setText(t.getMessage());

            }


        });



    }

    private void populateRecyclerViewRooms(List<Rooms> allRooms) {
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewSimpleAdapter<Rooms> adapter = new RecyclerViewSimpleAdapter<>(allRooms);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position, item) -> {
            Rooms room = (Rooms) item;
            Log.d(LOG_TAG, item.toString());
            Intent intent = new Intent(MainActivity.this, bookActivity.class);
            intent.putExtra(bookActivity.BOOKING, room);
            Log.d(LOG_TAG, "putExtra " + room.toString());
            startActivity(intent);
        });
    }

    private void populateRecyclerViewReservation(List<Reservations> allReservations) {
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewSimpleAdapter<Reservations> adapter = new RecyclerViewSimpleAdapter<>(allReservations);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position, item) -> {
            Reservations reserv = (Reservations) item;
            Log.d(LOG_TAG, item.toString());
            Intent intent = new Intent(MainActivity.this, bookActivity.class);
            intent.putExtra(bookActivity.BOOKING, reserv);
            Log.d(LOG_TAG, "putExtra " + reserv.toString());
            startActivity(intent);
        });

    }
}

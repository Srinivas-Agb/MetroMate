package com.miniproject.metromate;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
/*
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

 */

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RadioButton minimum_Interchange,CheckedBtn,shortest_Route;
    private static final int REQUEST_CHECK_SETTINGS = 1001;

    int checkedBtnId,sIndex,eIndex,count = 0,change = 0;
    RadioGroup filter;
    TextView sourceText,destText;
    String source="Select Source",dest="Select Destination", actvSource , actvDestination;
    CardView holidays_list,show_metro_map,buy_tickets,show_fare_calculator,btn,otherInfo,s1,s2;
    Button show_route_n_fare;
    ArrayList<String> list = new ArrayList<>();
    String[][] a;
    String[] b = {"Ameerpet","MG Bus Station", "Parade Ground"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        actvSource = source;
        actvDestination =dest;

        //radio group
        filter = findViewById(R.id.filter);
        minimum_Interchange = findViewById(R.id.minimumInterchange);
        shortest_Route = findViewById(R.id.shortestRoute);
        shortest_Route.setChecked(true);
        CheckedBtn=shortest_Route;
        filter.setOnCheckedChangeListener((group, checkedId) -> {
            // Check which RadioButton was selected
            if (checkedId == R.id.minimumInterchange) {
                // Option 1 selected
                CheckedBtn=minimum_Interchange;
                // Perform desired action
            } else if (checkedId == R.id.shortestRoute) {
                CheckedBtn=shortest_Route;
            }
        });

        s1=findViewById(R.id.cvDepart);
        s2=findViewById(R.id.Destination);
        s1.setOnClickListener(v -> openSourceList());
        s2.setOnClickListener(v -> openDestList());
        sourceText=findViewById(R.id.tvStationDepart);
        destText=findViewById(R.id.tvStationDestination);
        show_metro_map = findViewById(R.id.CardMetroMap);
        show_metro_map.setOnClickListener(v -> openMetroMap());

        holidays_list = findViewById(R.id.cardHolidayList);
        holidays_list.setOnClickListener(v -> openHolidayList());

        buy_tickets = findViewById(R.id.cardBuyTickets);
        buy_tickets.setOnClickListener(v -> openBuyTicket());

        show_fare_calculator = findViewById(R.id.FareCalculator);
        show_fare_calculator.setOnClickListener(v -> openFareCalculator());

        btn = findViewById(R.id.cardNearestStation);
        btn.setOnClickListener(v -> openNearestMetro());
//
        otherInfo = findViewById(R.id.cardOtherInfo);
        otherInfo.setOnClickListener(v -> openOtherInfo());

        //main code
        checkedBtnId = filter.getCheckedRadioButtonId();
        CheckedBtn = findViewById(checkedBtnId);

        show_route_n_fare = findViewById(R.id.show_fare_n_route1);
        show_route_n_fare.setOnClickListener(v -> openFareActivity(source,dest));

    }




    private void openOtherInfo() {
        startActivity(new Intent(MainActivity.this, OtherInfo.class));
    }

//    private void openNearestMetro() {
//        startActivity(new Intent(MainActivity1.this, NearestMetro.class));
//    }

    public void checkButton(View view) {
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void openMetroMap()
    {
        startActivity(new Intent(MainActivity.this, MetroMap.class));
    }
    private void openBuyTicket()
    {
        startActivity(new Intent(MainActivity.this,BuyTickets.class));
    }
    private void openHolidayList()
    {
        startActivity(new Intent(MainActivity.this, HolidayList.class));
    }
    public void openFareActivity(@NonNull String source, String dest) {

        if(source.equals("Select Source") && dest.equals("Select Destination"))
            Toast.makeText(MainActivity.this, "Please select Source & Destination", Toast.LENGTH_SHORT).show();
        else if( source.equals("Select Source") )
            Toast.makeText(MainActivity.this, "Please select Source", Toast.LENGTH_SHORT).show();
        else if( dest.equals("Select Destination") )
            Toast.makeText(MainActivity.this, "Please select Destination", Toast.LENGTH_SHORT).show();
        else if(source.equals(dest))
            Toast.makeText(MainActivity.this, "Source & Destination should not be same", Toast.LENGTH_SHORT).show();
        else {
            if(CheckedBtn==minimum_Interchange)
                minInterchange(source,dest);
            else{
                shortestRoute(source,dest);
            }
        }
        //minInterchange(source,dest);

    }

    public void shortestRoute(String start, String dest) {
//        actvSource = start;
//        actvDestination = dest;
        String c[] = new String[]{
                "Miyapur", "JNTU College", "KPHB Colony", "Kukatpally", "Dr.B.R.Ambedkar Balanagar",
                "Moosapet", "Bharat Nagar", "Erragadda", "ESI Hospital", "SR Nagar",
                "Ameerpet", "Punjagutta", "Irrum Manzil", "Khairatabad", "Lakdi-Ka-Pul",
                "Assembly", "Nampally", "Gandhi Bhavan", "Osmania Medical College",
                "MG Bus Station", "Malakpet", "New Market", "Musarambagh", "Dilsukhnagar",
                "Chaitanyapuri", "Victoria Memorial", "LB Nagar",
                "Nagole", "Uppal", "Stadium", "NGRI", "Habsiguda", "Tarnaka", "Mettuguda",
                "Secunderabad East", "Parade Ground", "Paradise", "Rasoolpura", "Prakash Nagar",
                "Begumpet",  "Madhura Nagar", "Yusufguda", "Road No.5 Jubilee Hills",
                "Jubilee Hills Check Post", "Peddamma Gudi", "Madhapur", "Durgam Cheruvu",
                "Hitec City", "Raidurg",
                "Secunderabad West", "Gandhi Hospital", "Musheerabad", "RTC X Roads",
                "Chikkadpally", "Narayanguda", "Sultan Bazar"
        };
        for(int i=0;i<c.length;i++)
        {
            if(start.equals(c[i]))
                sIndex=i;
            if(dest.equals(c[i]))
                eIndex=i;
        }
        int f = new FareCalculator().fare(start,dest);
        Intent intent = new Intent(this, ShortestRoute.class);
        intent.putExtra("SOURCE", sIndex);
        intent.putExtra("DEST", eIndex);
        intent.putExtra("FARE", f);

        startActivity(intent);

    }

    private void openFareCalculator()
    {
        startActivity(new Intent(MainActivity.this, FareCalculator.class));
    }
    public void minInterchange(String start,String dest)
    {
        int f = new FareCalculator().fare(start,dest);
        Intent intent = new Intent(MainActivity.this, MinInterchange.class);
        intent.putExtra("SOURCE", start);
        intent.putExtra("DEST", dest);
        intent.putExtra("FARE", f);
        startActivity(intent);

    }

//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//
//    }



    private void checkLocationSettings() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        Task<LocationSettingsResponse> task = LocationServices.getSettingsClient(this)
                .checkLocationSettings(builder.build());

        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // Location settings meet the requirements, start the location updates or other location-related operations
                startLocationUpdates();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    int statusCode = apiException.getStatusCode();
                    if (statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                        try {
                            // Show the dialog to enable location services
                            ResolvableApiException resolvable = (ResolvableApiException) apiException;
                            resolvable.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error
                        }
                    }
                }
            }
        });
    }
    private void openNearestMetro() {
        // Check location settings
        checkLocationSettings();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                // User enabled location settings, start the location updates or other location-related operations
                startLocationUpdates();
            } else {
                // User did not enable location settings
                Toast.makeText(this, "Location services are required for this feature.", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_OK) {
            String selectedItem = data.getStringExtra("selectedItem");

            if (requestCode == 1) {
                source = selectedItem;
                sourceText.setText(source);
            } else if (requestCode == 2) {
                dest = selectedItem;
                destText.setText(dest);
            }
        }
    }

    private void startLocationUpdates() {
        // Start the location updates or perform other location-related operations
        // ...

        // Start the NearestMetro activity
        Intent intent = new Intent(MainActivity.this, NearestMetro.class);
        startActivity(intent);
    }

    private void openSourceList() {
        Intent intent = new Intent(MainActivity.this, StationList.class);
        startActivityForResult(intent, 1);
    }

    private void openDestList() {
        Intent intent = new Intent(MainActivity.this, StationList.class);
        startActivityForResult(intent, 2);
    }



}


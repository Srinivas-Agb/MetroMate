package com.miniproject.metromate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class StationList extends AppCompatActivity {
    SearchView searchView;
    //Button back;
    ListView stations;
    String StationNames[];
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations_list);
        searchView = findViewById(R.id.search_bar);
        //back=findViewById(R.id.back_btn_station_list);
        stations = findViewById(R.id.station_list_view);
        StationNames = new String[]{"Ameerpet", "Assembly", "Begumpet", "Bharat Nagar",
                "Chaitanyapuri", "Chikkadpally", "Dilsukhnagar", "Dr.B.R.Ambedkar Balanagar",
                "Durgam Cheruvu", "ESI Hospital", "Erragadda", "Gandhi Bhavan", "Gandhi Hospital",
                "Habsiguda", "Hitec City", "Irrum Manzil", "JNTU College", "Jubilee Hills Check Post",
                "KPHB Colony", "Khairatabad", "Kukatpally", "LB Nagar", "Lakdi-Ka-Pul", "MG Bus Station",
                "Madhapur", "Madhura Nagar", "Malakpet", "Mettuguda", "Miyapur", "Moosapet", "Musarambagh",
                "Musheerabad", "NGRI", "Nagole", "Nampally", "Narayanguda", "New Market", "Osmania Medical College",
                "Parade Ground", "Paradise", "Peddamma Gudi", "Prakash Nagar", "Punjagutta", "RTC X Roads",
                "Raidurg", "Rasoolpura", "Road No.5 Jubilee Hills", "SR Nagar", "Secunderabad East", "Secunderabad West",
                "Stadium", "Sultan Bazar", "Tarnaka", "Uppal", "Victoria Memorial", "Yusufguda"};
        arrayList = new ArrayList<>(Arrays.asList(StationNames));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        stations.setAdapter(adapter);

        //back.setOnClickListener(v -> finish());

        // Set click listener for the ListView
        stations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Get the clicked item from the adapter
                String clickedItem = adapter.getItem(position);
                // Perform any action with the clicked item
                // For example, you can display a Toast message
                //Toast.makeText(StationList.this, "Clicked: " + clickedItem, Toast.LENGTH_SHORT).show();
                // Inside StationList activity's onItemClick method
                String selectedItem = adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("selectedItem", selectedItem);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}

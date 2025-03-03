package com.miniproject.metromate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class NearestMetro extends AppCompatActivity implements LocationListener {
    private static final int REQUEST_LOCATION = 1;
    ArrayList<JSONObject> ASaka;
    ArrayAdapter<String[]> adapter;
    JSONArray jSONArray;
    LinearLayout linearLayout;
    String latitude,longitude,latitude1,longitude1;
    ArrayList<String[]> stationNames;
    ArrayAdapter<String> adapter1;
    public double lat;
    ListView lst;
    Button map,back;
    LocationManager locationManager;
    public double lon;


    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        setContentView((R.layout.nearest_metro));
        lst = (ListView) findViewById(R.id.list);
//        adapter = new ArrayAdapter<String[]>(this, R.layout.list_item_layout, R.id.textView1, stationNames) {
//            @NonNull
//            @Override
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                TextView textView1 = view.findViewById(R.id.textView1);
//                TextView textView2 = view.findViewById(R.id.textView2);
//                String[] stationData = getItem(position);
//
//                if (stationData != null) {
//                    textView1.setText(stationData[0]);
//                    textView2.setText(stationData[1]);
//                }
//
//                return view;
//            }
//        };
//        lst.setAdapter(adapter);
        map=findViewById(R.id.mapsBtn);
        map.setVisibility(View.INVISIBLE);

        linearLayout = findViewById(R.id.ll_map_info);

// Hide the LinearLayout initially
        linearLayout.setVisibility(View.INVISIBLE);


        back = findViewById(R.id.back_btn15);
        back.setOnClickListener(v -> finish());


        LocationManager locationManager2 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        boolean z2 = false;
//        try {
//            z = locationManager2.isProviderEnabled("gps");
//        } catch (Exception unused) {
//            makeText(this, "Unable to access the location", Toast.LENGTH_SHORT);
//            z = false;
//        }
//        try {
//            z2 = locationManager2.isProviderEnabled("network");
//        } catch (Exception unused2) {
//            makeText(this, "Unable to access the location", Toast.LENGTH_SHORT);
//        }
//        if (!z && !z2) {
//            new AlertDialog.Builder(this).setMessage("Please on the GPS location for accurate results").setPositiveButton("Settings", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    NearestMetro.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
//                }
//            }).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null).show();
//        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        this.locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
            this.locationManager.requestLocationUpdates("gps", 0, 0.0f, this);
        }
    }

    public void onLocationChanged(Location location) {
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();
        stationNames = new ArrayList<String[]>();
        String [][]sn =  new String[10][2];
        this.locationManager.removeUpdates(this);
        try {
            jSONArray = new JSONObject("{\"students\": [\n     " +
                    "   {\"name\":\"Miyapur\",\"lat\":\"17.49655511109275\",\"long\":\" 78.37307189762151\"},\n  " +
                    "      {\"name\":\"JNTU College\",\"lat\":\"17.498670208430948\",\"long\":\"78.38893113314487\"},\n   " +
                    "     {\"name\":\"KPHB Colony\",\"lat\":\"17.49378236216134\",\"long\":\"78.40173298807238\"},\n     " +
                    "   {\"name\":\"Kukatpally\",\"lat\":\"17.485082592739392\",\"long\":\"78.41168135444356\"},\n      " +
                    "  {\"name\":\"Balanagar\",\"lat\":\"17.47676597356499\",\"long\":\"78.4219867898636\"},\n     " +
                    "   {\"name\":\"Moosapet\",\"lat\":\"17.471989153259134\",\"long\":\"78.42602648993935\"},\n     " +
                    "   {\"name\":\"Bharatnagar\",\"lat\":\"17.46413256408599\",\"long\":\"78.43002429737989\"},\n   " +
                    "     {\"name\":\"Erragadda\",\"lat\":\"17.45721269777435\",\"long\":\"78.43352157670269\"},\n     " +
                    "   {\"name\":\"ESI Hospital\",\"lat\":\"17.447477297679228\",\"long\":\"78.43830065697351\"},\n   " +
                    "     {\"name\":\"SR Nagar\",\"lat\":\"17.441533625162286\",\"long\":\"78.44170293061751\"},\n    " +
                    "    {\"name\":\"Ameerpet\",\"lat\":\"435709636021766\",\"long\":\"78.44460037050558\"},\n      " +
                    "  {\"name\":\"Punjagutta\",\"lat\":\"17.42861343105057\",\"long\":\"78.45112995089679\"},\n    " +
                    "    {\"name\":\"Irrum Manzil\",\"lat\":\"17.420379244210235\",\"long\":\"78.45619371368828\"},\n      " +
                    "  {\"name\":\"Khairatabad\",\"lat\":\"17.41278226775719\",\"long\":\"78.46022112924064\"},\n       " +
                    " {\"name\":\"Lakdi Ka Pul\",\"lat\":\"17.403945168650772\",\"long\":\"78.465023116027\"},\n     " +
                    "   {\"name\":\"Assembly\",\"lat\":\"17.398087057078136\",\"long\":\"78.47084194829877\"},\n     " +
                    "   {\"name\":\"Nampally\",\"lat\":\"17.392305698128233\",\"long\":\"78.47016021630274\"},\n     " +
                    "   {\"name\":\"Gandhi Bhavan\",\"lat\":\"17.38605084632505\",\"long\":\"78.47307901951888\"},\n   " +
                    "     {\"name\":\"Osmania Medical College\",\"lat\":\"17.382501200690267\",\"long\":\"78.48096754911207\"},\n   " +
                    "     {\"name\":\"MG Bus station\",\"lat\":\"17.379863981851102\",\"long\":\"78.48627256202619\"},\n    " +
                    "    {\"name\":\"Malakpet\",\"lat\":\"17.37722062340575\",\"long\":\"78.49384007633525\"},\n   " +
                    "     {\"name\":\"New Market\",\"lat\":\"17.37345384801486\",\"long\":\"78.50315566791329\"},\n    " +
                    "    {\"name\":\"Musarambagh\",\"lat\":\"17.37114821469516\",\"long\":\"78.51194092367234\"},\n   " +
                    "     {\"name\":\"Dilsukhnagar\",\"lat\":\"17.36853625028612\",\"long\":\"78.52570499520266\"},\n   " +
                    "     {\"name\":\"Chaitanyapuri\",\"lat\":\"17.368376235951946\",\"long\":\"78.53582492468544\"},\n    " +
                    "    {\"name\":\"Victoria Memorial\",\"lat\":\"17.361577111806195\",\"long\":\"78.54400321223602\"},\n     " +
                    "   {\"name\":\"LB Nagar\",\"lat\":\"17.34972733982562\",\"long\":\"78.54796991601411\"},\n     " +
                    "   {\"name\":\"Secunderabad West\",\"lat\":\"17.433798554150144\",\"long\":\"78.4994324097276\"},\n     " +
                    "   {\"name\":\"Gandhi Hospital\",\"lat\":\"17.425666887905194\",\"long\":\"78.5018725327531\"},\n    " +
                    "    {\"name\":\"Musheerabad\",\"lat\":\"17.417793366173584\",\"long\":\"78.49935120889586\"},\n    " +
                    "    {\"name\":\"RTC X Roads\",\"lat\":\"17.40710734659368\",\"long\":\"78.4965579493953\"},\n     " +
                    "   {\"name\":\"Chikkadpally\",\"lat\":\"17.400791095141827\",\"long\":\"78.49487613757178\"},\n      " +
                    "  {\"name\":\"Narayanguda\",\"lat\":\"17.394235060702645\",\"long\":\"78.48987522905361\"},\n     " +
                    "   {\"name\":\"Sultan Bazaar\",\"lat\":\"17.384119814935463\",\"long\":\"78.48369867402019\"},\n    " +
                    "    {\"name\":\"Nagole\",\"lat\":\"17.391095546525325\",\"long\":\"78.55878567258733\"},\n     " +
                    "   {\"name\":\"uppal\",\"lat\":\"17.40008549150384\",\"long\":\"78.56017146762339\"},\n    " +
                    "    {\"name\":\"Stadium\",\"lat\":\"17.401184852130356\",\"long\":\"78.55425072599144\"},\n     " +
                    "   {\"name\":\"NGRI\",\"lat\":\"17.40758891260213\",\"long\":\"78.55974913823059\"},\n    " +
                    "    {\"name\":\"Habsiguda\",\"lat\":\" 17.421111054123692\",\"long\":\"78.54037961124457\"},\n   " +
                    "     {\"name\":\"Tarnaka\",\"lat\":\"17.42871448065143\",\"long\":\"78.528728733998\"},\n   " +
                    "     {\"name\":\"Mettuguda\",\"lat\":\"17.436365610421365\",\"long\":\"78.51974406827392\"},\n    " +
                    "    {\"name\":\"Secunderabad East\",\"lat\":\"17.43778900025487\",\"long\":\"78.50443604550568\"},\n     " +
                    "   {\"name\":\"Parade Ground\",\"lat\":\"17.44377353016131\",\"long\":\"78.49685120689112\"},\n      " +
                    "  {\"name\":\"Paradise\",\"lat\":\"17.44432104384098\",\"long\":\"78.48644964500588\"},\n   " +
                    "     {\"name\":\"Rasoolpura\",\"lat\":\"17.44453846814573\",\"long\":\"78.47640786827556\"},\n    " +
                    "    {\"name\":\"Prakash Nagar\",\"lat\":\"17.445874102714658\",\"long\":\"78.46575072223763\"},\n      " +
                    "  {\"name\":\"Begumpet\",\"lat\":\"17.43834055704569\",\"long\":\"78.45689846827429\"},\n    " +
                    "    {\"name\":\"Madhura Nagar\",\"lat\":\"17.43654616592323\",\"long\":\"78.43992685292824\"},\n    " +
                    "    {\"name\":\"Yousufguda\",\"lat\":\"17.435770961181614\",\"long\":\"78.42765822093567\"},\n     " +
                    "   {\"name\":\"Jubilee Hills Road No 5\",\"lat\":\"17.430935583331202\",\"long\":\"78.42315802173349\"},\n    " +
                    "    {\"name\":\"Jubilee Hills Check Post\",\"lat\":\"17.428233852061474\",\"long\":\" 78.41372203328201\"},\n    " +
                    "    {\"name\":\"Peddamma Gudi\",\"lat\":\"17.431368957408598\",\"long\":\"78.40952383657861\"},\n     " +
                    "   {\"name\":\"Madhapur\",\"lat\":\"17.43742330157167\",\"long\":\"78.4008388989663\"},\n    " +
                    "    {\"name\":\"Durgam Cheruvu\",\"lat\":\"17.443628446957756\",\"long\":\"78.38767106827538\"},\n      " +
                    "  {\"name\":\"Hitec City\",\"lat\":\"17.450088920499304\",\"long\":\"78.38333382966091\"},\n      " +
                    "  {\"name\":\"Raidurg\",\"lat\":\"17.443063017779718\",\"long\":\"78.37706902659357\"}]}").getJSONArray("students");
            float[] dist = new float[10];
            //String[] sn = new String[10];
            Arrays.fill(dist, Float.MAX_VALUE);

            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                float distance = (float) distance(this.lat, this.lon, Double.parseDouble(jSONObject.getString("lat")), Double.parseDouble(jSONObject.getString("long")));

                // Check if the current distance is smaller than the largest distance in dist array
                if (distance < dist[9]) {
                    // Shift elements in the array to make room for the new distance
                    for (int i = 8; i >= 0; i--) {
                        if (distance < dist[i]) {
                            dist[i + 1] = dist[i];
                            dist[i] = distance;
                        } else {
                            break;
                        }
                    }
                }
            }
            Arrays.sort(dist);

// The dist array now contains the top 5 nearest distances

            this.ASaka = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                double ans =distance(this.lat, this.lon, Double.parseDouble(jSONObject2.getString("lat")),
                        Double.parseDouble(jSONObject2.getString("long")));
                float d=(float) ans;
                for(int x=0;x<10;x++)
                {
                    if(d==dist[x])
                    {
                        String[] stationData = new String[2];
                        stationData[0] = jSONObject2.getString("name");
                        stationData[1] = Math.round(ans * 100.0) / 100.0 + " km";
                        stationNames.add(stationData);

                        //sn[x]=jSONObject2.getString("name");
                    }
                    if(d==dist[0])
                    {
                        latitude1=jSONObject2.getString("lat");
                        longitude1=jSONObject2.getString("long");
                    }
                }
            }
            Collections.sort(stationNames, new StationDistanceComparator());
            ProgressBar loadingProgressBar = findViewById(R.id.loadingProgressBar);
            loadingProgressBar.setVisibility(View.VISIBLE);


            // Load values and populate ListView

// Hide the loading animation and show the content
            loadingProgressBar.setVisibility(View.GONE);
            //ListView listView = findViewById(R.id.list);
            lst.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
//            if(getApplicationContext()==NearestMetro.this)
//                Toast.makeText(NearestMetro.this, "Open Maps will open the location of the nearest metro station", Toast.LENGTH_LONG).show();

            map.setVisibility(View.VISIBLE);


            adapter = new ArrayAdapter<String[]>(this, R.layout.list_item_layout, R.id.textView1, stationNames) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView1 = view.findViewById(R.id.textView1);
                    TextView textView2 = view.findViewById(R.id.textView2);
                    String[] stationData = getItem(position);

                    if (stationData != null) {
                        textView1.setText(stationData[0]);
                        textView2.setText(stationData[1]);
                    }

                    return view;
                }
            };
            lst.setAdapter(adapter);



        } catch (Exception unused) {
            System.exit(2);
        }
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str;
                str = String.format(Locale.ENGLISH, "geo:%S,%S", new Object[]{latitude1, longitude1});
                NearestMetro.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        });
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Your code logic for handling the item click event
                // You can access the clicked item using the position parameter
                String str;
                String station = stationNames.get(position)[0];
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        if(jSONObject.getString("name").equals(station))
                        {
                            latitude=jSONObject.getString("lat");
                            longitude=jSONObject.getString("long");
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                str = String.format(Locale.ENGLISH, "geo:%S,%S", new Object[]{latitude, longitude});
                NearestMetro.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));

            }
        });

    }
    public double distance(double lat1, double lon1, double lat2, double lon2) {
        // Earth's radius in kilometers
        double radius = 6371;

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Haversine formula
        double dlat = lat2Rad - lat1Rad;
        double dlon = lon2Rad - lon1Rad;
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radius * c;

        return distance;
    }


    public class StationDistanceComparator implements Comparator<String[]> {

        @Override
        public int compare(String[] station1, String[] station2) {
            // Assuming the distance is stored as a string at index 1 in the stationData array
            String distance1 = station1[1];
            String distance2 = station2[1];

            // Extract the numerical value from the distance strings
            double distanceValue1 = Double.parseDouble(distance1.split(" ")[0]);
            double distanceValue2 = Double.parseDouble(distance2.split(" ")[0]);

            // Compare the distances
            if (distanceValue1 < distanceValue2) {
                return -1; // station1 is closer
            } else if (distanceValue1 > distanceValue2) {
                return 1; // station2 is closer
            } else {
                return 0; // distances are equal
            }
        }
    }


}

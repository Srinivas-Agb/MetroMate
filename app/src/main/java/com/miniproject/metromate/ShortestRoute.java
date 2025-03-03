package com.miniproject.metromate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class ShortestRoute extends AppCompatActivity {
    ArrayList<String> change = new ArrayList<>();
    ArrayList<Integer> color = new ArrayList<>();
    Button back;
    String[][] a;
    public int dest, start, stop, f;
    ArrayAdapter<String> adapter;
    ListView lst;
    TextView s1, c1, f1, d1;
    public ShortestRoute.Graph f192gh;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fare_and_route_list);
        back = findViewById(R.id.back_btn5);
        back.setOnClickListener(v -> finish());
        this.start = (Integer) getIntent().getIntExtra("SOURCE", 0);
        this.stop = (Integer) getIntent().getIntExtra("DEST", 0);
        this.f = (Integer) getIntent().getIntExtra("FARE", 0);

        lst = (ListView) findViewById(R.id.listroute);
        s1 = (TextView) findViewById(R.id.sta);
        c1 = (TextView) findViewById(R.id.chan);
        f1 = (TextView) findViewById(R.id.f);
        d1 = (TextView) findViewById(R.id.time);
        f1.setText("" + (f));
        ShortestRoute.Graph graph;
        graph = new Graph();
        this.f192gh = graph;
        graph.buildGraph();
        findWay(start, stop);

    }

    public void findWay(int start, int stop) {
        int i = 0;
        int parseInt = start;
        int parseInt2 = stop;
        this.dest = parseInt2;
        ArrayList<String> shortestPath = this.f192gh.shortestPath(parseInt, parseInt2);
        //ListView listView = (ListView) findViewById(R.id.list_item);
        //ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("ARRAYLIST");
//        adapter=new ArrayAdapter<String>(ShortestRoute.this,android.R.layout.simple_list_item_1,shortestPath);
//        adapter = new ShortestRoute.CustomArrayAdapter(ShortestRoute.this, shortestPath,color);
//        lst.setAdapter(adapter);
        //int count = shortestPath.size();
        //int changes = change.size();
        // s1.setText(""+(count));
        //c1.setText(""+changes);


        int i2 = 1;
        while (i2 < shortestPath.size() - 1) {
            int i3 = i2 - 1;
            int i4 = i2 + 1;
//            if (shortestPath.get(i3).charAt(shortestPath.get(i3).length()-3)
//                    != shortestPath.get(i4).charAt(shortestPath.get(i4).length()-3)
//                    && (shortestPath.get(i2).equals("Ameerpet") || shortestPath.get(i2).equals("MG Bus Station")
//                    || shortestPath.get(i2).equals("Parade Ground"))) {
//                this.change.add(shortestPath.get(i2));
//            }
            if ((shortestPath.get(i3).equals("Malakpet") && shortestPath.get(i4).equals("Sultan Bazar"))
                    || (shortestPath.get(i3).equals("Sultan Bazar") && shortestPath.get(i4).equals("Malakpet"))
                    || (shortestPath.get(i3).equals("Osmania Medical College") && shortestPath.get(i4).equals("Sultan Bazar"))
                    || (shortestPath.get(i3).equals("Sultan Bazar") && shortestPath.get(i4).equals("Osmania Medical College"))) {
                this.change.add("MG Bus Station");
            }
            if ((shortestPath.get(i3).equals("Secunderabad West") && shortestPath.get(i4).equals("Paradise"))
                    || (shortestPath.get(i3).equals("Paradise") && shortestPath.get(i4).equals("Secunderabad West"))
                    || (shortestPath.get(i3).equals("Secunderabad West") && shortestPath.get(i4).equals("Secunderabad East"))
                    || (shortestPath.get(i3).equals("Secunderabad East") && shortestPath.get(i4).equals("Secunderabad West"))) {
                this.change.add("Parade Ground");
            }
            if ((shortestPath.get(i3).equals("Punjagutta") && shortestPath.get(i4).equals("Madhura Nagar"))
                    || (shortestPath.get(i3).equals("Madhura Nagar") && shortestPath.get(i4).equals("Punjagutta"))
                    || (shortestPath.get(i3).equals("Punjagutta") && shortestPath.get(i4).equals("Begumpet"))
                    || (shortestPath.get(i3).equals("Begumpet") && shortestPath.get(i4).equals("Punjagutta"))
                    || (shortestPath.get(i3).equals("SR Nagar") && shortestPath.get(i4).equals("Madhura Nagar"))
                    || (shortestPath.get(i3).equals("Madhura Nagar") && shortestPath.get(i4).equals("SR Nagar"))
                    || (shortestPath.get(i3).equals("SR Nagar") && shortestPath.get(i4).equals("Begumpet"))
                    || (shortestPath.get(i3).equals("Begumpet") && shortestPath.get(i4).equals("SR Nagar"))) {
                this.change.add("Ameerpet");
            }

            i2 = i4;
        }
        adapter = new ShortestRoute.CustomArrayAdapter(ShortestRoute.this, shortestPath, color);
        lst.setAdapter(adapter);
        int count = shortestPath.size();
        s1.setText("" + (count));
        for (int x = 0; x < shortestPath.size(); x++) {
            int flag = 0;
            for (int y = 0; y < change.size(); y++) {
                if (change.get(y).equals(shortestPath.get(x))) {
                    shortestPath.set(x, "Change here : " + shortestPath.get(x));
                    flag = 1;
                    //color.set(x,3);
                }
            }
            if (x == 0) {
                shortestPath.set(x, "Source : " + shortestPath.get(x));
            } else if (x == shortestPath.size() - 1) {
                shortestPath.set(x, "Destination : " + shortestPath.get(x));
            } else {
                if (flag == 0)
                    shortestPath.set(x, "   " + shortestPath.get(x));
            }
        }
        int changes = change.size();
        c1.setText("" + changes);
        d1.setText((Math.round(((double) shortestPath.size()) * 2.203d) + ((long) (this.change.size() * 4))) + " min");
    }

    class Graph {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        /* renamed from: hs */
        HashMap<Integer, String> f193hs;

        Graph() {
            HashMap<Integer, String> hashMap = new HashMap<>();
            this.f193hs = hashMap;
            hashMap.put(0, "Miyapur");
            this.f193hs.put(1, "JNTU College");
            this.f193hs.put(2, "KPHB Colony");
            this.f193hs.put(3, "Kukatpally");
            this.f193hs.put(4, "Dr.B.R.Ambedkar Balanagar");
            this.f193hs.put(5, "Moosapet");
            this.f193hs.put(6, "Bharat Nagar");
            this.f193hs.put(7, "Erragadda");
            this.f193hs.put(8, "ESI Hospital");
            this.f193hs.put(9, "SR Nagar");
            this.f193hs.put(10, "Ameerpet");
            this.f193hs.put(11, "Punjagutta");
            this.f193hs.put(12, "Irrum Manzil");
            this.f193hs.put(13, "Khairatabad");
            this.f193hs.put(14, "Lakdi-Ka-Pul");
            this.f193hs.put(15, "Assembly");
            this.f193hs.put(16, "Nampally");
            this.f193hs.put(17, "Gandhi Bhavan");
            this.f193hs.put(18, "Osmania Medical College");
            this.f193hs.put(19, "MG Bus Station");
            this.f193hs.put(20, "Malakpet");
            this.f193hs.put(21, "New Market");
            this.f193hs.put(22, "Musarambagh");
            this.f193hs.put(23, "Dilsukhnagar");
            this.f193hs.put(24, "Chaitanyapuri");
            this.f193hs.put(25, "Victoria Memorial");
            this.f193hs.put(26, "LB Nagar");
            this.f193hs.put(27, "Nagole");
            this.f193hs.put(28, "Uppal");
            this.f193hs.put(29, "Stadium");
            this.f193hs.put(30, "NGRI");
            this.f193hs.put(31, "Habsiguda");
            this.f193hs.put(32, "Tarnaka");
            this.f193hs.put(33, "Mettuguda");
            this.f193hs.put(34, "Secunderabad East");
            this.f193hs.put(35, "Parade Ground");
            this.f193hs.put(36, "Paradise");
            this.f193hs.put(37, "Rasoolpura");
            this.f193hs.put(38, "Prakash Nagar");
            this.f193hs.put(39, "Begumpet");
            this.f193hs.put(40, "Madhura Nagar");
            this.f193hs.put(41, "Yusufguda");
            this.f193hs.put(42, "Road No.5 Jubilee Hills");
            this.f193hs.put(43, "Jubilee Hills Check Post");
            this.f193hs.put(44, "Peddamma Gudi");
            this.f193hs.put(45, "Madhapur");
            this.f193hs.put(46, "Durgam Cheruvu");
            this.f193hs.put(47, "Hitec City");
            this.f193hs.put(48, "Raidurg");
            this.f193hs.put(49, "Secunderabad West");
            this.f193hs.put(50, "Gandhi Hospital");
            this.f193hs.put(51, "Musheerabad");
            this.f193hs.put(52, "RTC X Roads");
            this.f193hs.put(53, "Chikkadpally");
            this.f193hs.put(54, "Narayanguda");
            this.f193hs.put(55, "Sultan Bazar");
        }

        public void addEdge(ArrayList<ArrayList<Integer>> arrayList, int i, int i2) {
            arrayList.get(i).add(Integer.valueOf(i2));
            arrayList.get(i2).add(Integer.valueOf(i));
        }

        public void buildGraph() {
            int i = 0;
            for (int i2 = 0; i2 < 56; i2++) {
                this.adj.add(new ArrayList());
            }
            while (i < 26) {
                int i3 = i + 1;
                addEdge(this.adj, i, i3);
                i = i3;
            }
            int i4 = 27;
            while (i4 <= 38) {
                int i5 = i4 + 1;
                addEdge(this.adj, i4, i5);
                i4 = i5;
            }
            addEdge(this.adj, 39, 10);
            int i6 = 40;
            addEdge(this.adj, 10, 40);
            while (i6 < 48) {
                int i7 = i6 + 1;
                addEdge(this.adj, i6, i7);
                i6 = i7;
            }
            int i8 = 49;
            while (i8 < 55) {
                int i9 = i8 + 1;
                addEdge(this.adj, i8, i9);
                i8 = i9;
            }
            addEdge(this.adj, 55, 19);
            addEdge(this.adj, 35, 49);
        }

        public ArrayList<String> shortestPath(int i, int i2) {
            int[] iArr = new int[56];
            int[] iArr2 = new int[56];
            LinkedList linkedList = new LinkedList();
            iArr2[i] = -1;
            //color.add(3);           //3 for black
            linkedList.add(Integer.valueOf(i));
            while (!linkedList.isEmpty()) {
                int intValue = ((Integer) linkedList.peek()).intValue();
//                if(intValue<=26)
//                    color.add(0);       //red
//                else if(intValue<=48)
//                    color.add(1);
//                else
//                    color.add(2);
                linkedList.remove();
                Iterator it = this.adj.get(intValue).iterator();
                while (it.hasNext()) {
                    int intValue2 = ((Integer) it.next()).intValue();
                    if (iArr[intValue2] == 0) {
                        iArr[intValue2] = 1;
                        iArr2[intValue2] = intValue;
                        linkedList.add(Integer.valueOf(intValue2));
                    }
                }
            }
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(this.f193hs.get(Integer.valueOf(i2)));
            while (i2 != i) {
                System.out.println();
                arrayList.add(this.f193hs.get(Integer.valueOf(iArr2[i2])));
                i2 = iArr2[i2];
            }
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    private class CustomArrayAdapter extends ArrayAdapter<String> {
        private ArrayList<String> itemList;
        private ArrayList<Integer> colorList;
        private Context context;

        public CustomArrayAdapter(Context context, ArrayList<String> itemList, ArrayList<Integer> colorList) {
            super(context, 0, itemList);
            this.itemList = itemList;
            this.colorList = generateColorList(itemList);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            TextView textViewItem = convertView.findViewById(android.R.id.text1);

            String station = itemList.get(position);
            int color = colorList.get(position);

            // Set the text color based on the color value
            if (color == -1) {
                textViewItem.setTextColor(ContextCompat.getColor(context, android.R.color.black));
                textViewItem.setTypeface(null, Typeface.BOLD);
            } else if (color == 0) {
                textViewItem.setTextColor(ContextCompat.getColor(context, R.color.red));
                textViewItem.setTypeface(null, Typeface.NORMAL);
            } else if (color == 1) {
                textViewItem.setTextColor(ContextCompat.getColor(context, R.color.blue));
                textViewItem.setTypeface(null, Typeface.NORMAL);
            } else if (color == 2) {
                textViewItem.setTextColor(ContextCompat.getColor(context, R.color.green));
                textViewItem.setTypeface(null, Typeface.NORMAL);
            }

            textViewItem.setText(station);

            return convertView;
        }
    }

    private ArrayList<Integer> generateColorList(ArrayList<String> itemList) {
        ArrayList<Integer> colorList = new ArrayList<>();
        String[] c = {
                "Miyapur", "JNTU College", "KPHB Colony", "Kukatpally", "Dr.B.R.Ambedkar Balanagar",
                "Moosapet", "Bharat Nagar", "Erragadda", "ESI Hospital", "SR Nagar",
                "Ameerpet", "Punjagutta", "Irrum Manzil", "Khairatabad", "Lakdi-Ka-Pul",
                "Assembly", "Nampally", "Gandhi Bhavan", "Osmania Medical College",
                "MG Bus Station", "Malakpet", "New Market", "Musarambagh", "Dilsukhnagar",
                "Chaitanyapuri", "Victoria Memorial", "LB Nagar",
                "Nagole", "Uppal", "Stadium", "NGRI", "Habsiguda", "Tarnaka", "Mettuguda",
                "Secunderabad East", "Parade Ground", "Paradise", "Rasoolpura", "Prakash Nagar",
                "Begumpet", "Madhura Nagar", "Yusufguda", "Road No.5 Jubilee Hills",
                "Jubilee Hills Check Post", "Peddamma Gudi", "Madhapur", "Durgam Cheruvu",
                "Hitec City", "Raidurg",
                "Secunderabad West", "Gandhi Hospital", "Musheerabad", "RTC X Roads",
                "Chikkadpally", "Narayanguda", "Sultan Bazar"
        };

        for (int j = 0; j < itemList.size(); j++) {
            int index = -1;
            if (j == 0 || j == itemList.size() - 1 || change.contains(itemList.get(j))) {
                colorList.add(-1); // -1 indicates no color change
                continue;
            }
            for (int i = 0; i < c.length; i++) {
                if (c[i].equals(itemList.get(j))) {
                    index = i;
                    break;
                }
            }
            if (index == 10 && (itemList.get(j - 1).equals("Begumpet") || itemList.get(j - 1).equals("Madhura Nagar"))) {
                colorList.add(1);
            } else if (index <= 26) {

                colorList.add(0);
            } else if (index <= 48) {
                colorList.add(1);
            } else {
                colorList.add(2);
            }
        }

        return colorList;
    }


}

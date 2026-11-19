//package com.example.laborator4task;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.widget.ListView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
//public class ImaginiReteaActivity extends AppCompatActivity {
//    private ListView lvImagini;
//    private List<ImaginiDomeniu> listaImagini = new ArrayList<>();
//    private List<Bitmap> imaginiDescarcate = new ArrayList<>();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_imagini_retea);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        lvImagini = findViewById(R.id.lv_imagini);
//        List<String> linkuriPoze = new ArrayList<>();
//        linkuriPoze.add("https://i.pinimg.com/236x/6d/be/c9/6dbec9122988fd816f431d7d4a0b5c54.jpg");
//        linkuriPoze.add("https://static.vecteezy.com/system/resources/thumbnails/048/938/229/small/cars-standing-in-a-row-photo.jpeg");
//        linkuriPoze.add("https://i.pinimg.com/474x/de/ce/cc/dececce9305467f52b20fbe57de2a4a0.jpg");
//        linkuriPoze.add("https://i.pinimg.com/474x/9c/98/05/9c98055fbeea0e74d70fa31c84af67c2.jpg");
//        linkuriPoze.add("https://i.pinimg.com/736x/2a/fa/9e/2afa9e124b61bf94b6e4a1f6d75a5da2.jpg");
//
//        Executor executor = Executors.newSingleThreadExecutor();
//        Handler handler = new Handler(Looper.myLooper());
//
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                for (String link : linkuriPoze){
//                    HttpURLConnection con = null;
//                    try {
//                        URL url = new URL(link);
//                        con = (HttpURLConnection) url.openConnection();
//                        InputStream is = con.getInputStream();
//                        imaginiDescarcate.add(BitmapFactory.decodeStream(is));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (con != null) {
//                            con.disconnect();}
//                        }
//                }
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (imaginiDescarcate.size() == 5) {
//                            listaImagini.add(new ImaginiDomeniu("Pozik 1", imaginiDescarcate.get(0), "https://ro.wikipedia.org/wiki/Porsche"));
//                            listaImagini.add(new ImaginiDomeniu("Pozik 2", imaginiDescarcate.get(1), "https://en.wikipedia.org/wiki/List_of_Porsche_vehicles"));
//                            listaImagini.add(new ImaginiDomeniu("Pozik 3", imaginiDescarcate.get(2), "https://en.wikipedia.org/wiki/Porsche_911_(930)"));
//                            listaImagini.add(new ImaginiDomeniu("Pozik 4", imaginiDescarcate.get(3), "https://www.porsche.com/central-eastern-europe/en/_romania_/accessoriesandservice/classic/models/"));
//                            listaImagini.add(new ImaginiDomeniu("Pozik 5", imaginiDescarcate.get(4), "https://en.wikipedia.org/wiki/Porsche_914"));
//
//                            ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(), R.layout.item_imagine, listaImagini);
//                            lvImagini.setAdapter(adapter);
//                        }
//                    }
//                }
//                });
//
//
//    });
//    }
//    lvImagini.setOnItemClickListener((adapterView, view, i, l) -> {
//        Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
//        it.putExtra("link", listaImagini.get(i).getLink());
//        startActivity(it);
//    });
//}
//}















package com.example.laborator4task;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiReteaActivity extends AppCompatActivity {
    private ListView lvImagini;
    private List<ImaginiDomeniu> listaImagini = new ArrayList<>();
    private List<Bitmap> imaginiDescarcate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini_retea);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvImagini = findViewById(R.id.lv_imagini);
        List<String> linkuriPoze = new ArrayList<>();
        linkuriPoze.add("https://i.pinimg.com/236x/6d/be/c9/6dbec9122988fd816f431d7d4a0b5c54.jpg");
        linkuriPoze.add("https://static.vecteezy.com/system/resources/thumbnails/048/938/229/small/cars-standing-in-a-row-photo.jpeg");
        linkuriPoze.add("https://i.pinimg.com/474x/de/ce/cc/dececce9305467f52b20fbe57de2a4a0.jpg");
        linkuriPoze.add("https://i.pinimg.com/474x/9c/98/05/9c98055fbeea0e74d70fa31c84af67c2.jpg");
        linkuriPoze.add("https://i.pinimg.com/736x/2a/fa/9e/2afa9e124b61bf94b6e4a1f6d75a5da2.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriPoze) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imaginiDescarcate.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imaginiDescarcate.size() == 5) {
                            listaImagini.add(new ImaginiDomeniu("Pozik 1", imaginiDescarcate.get(0), "https://ro.wikipedia.org/wiki/Porsche"));
                            listaImagini.add(new ImaginiDomeniu("Pozik 2", imaginiDescarcate.get(1), "https://en.wikipedia.org/wiki/List_of_Porsche_vehicles"));
                            listaImagini.add(new ImaginiDomeniu("Pozik 3", imaginiDescarcate.get(2), "https://en.wikipedia.org/wiki/Porsche_911_(930)"));
                            listaImagini.add(new ImaginiDomeniu("Pozik 4", imaginiDescarcate.get(3), "https://www.porsche.com/central-eastern-europe/en/_romania_/accessoriesandservice/classic/models/"));
                            listaImagini.add(new ImaginiDomeniu("Pozik 5", imaginiDescarcate.get(4), "https://en.wikipedia.org/wiki/Porsche_914"));

                            ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(), R.layout.item_imagine, listaImagini);
                            lvImagini.setAdapter(adapter);
                        }
                    }
                });
            }
        }); // Aici se închide corect executor.execute

        // Listener-ul a fost mutat în interiorul metodei onCreate()
        lvImagini.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
            it.putExtra("link", listaImagini.get(i).getLink());
            startActivity(it);
        });
    } // Aici se închide onCreate
} // Aici se închide clasa
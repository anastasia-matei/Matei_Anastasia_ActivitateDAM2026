package com.example.s9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VremeActivity extends AppCompatActivity {

    private static final String API_KEY = "";
    private Button btnCauta;
    private EditText etOras;
    private Spinner spnZile;
    private TextView tvRezultat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vreme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCauta = findViewById(R.id.btnCauta);
        etOras = findViewById(R.id.etOras);
        spnZile = findViewById(R.id.spnZile);
        tvRezultat = findViewById(R.id.tvRezultat);

        String[] optiuni = {"1 zi", "5 zile"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, optiuni);
        spnZile.setAdapter(adapter);


       Executor executor = Executors.newSingleThreadExecutor();
       Handler handler = new Handler(Looper.getMainLooper());


       btnCauta.setOnClickListener(v->{
           String oras = etOras.getText().toString().trim();
           if(oras.isEmpty()){
               Toast.makeText(this, "Introdu numele unui oras!", Toast.LENGTH_SHORT).show();
               return;
           }

           String optiuneAleasa = spnZile.getSelectedItem().toString();

           executor.execute(()->{
               try {
                   String orasCodificat = URLEncoder.encode(oras, "UTF-8");
                   String link = "https://dataservice.accuweather.com/locations/v1/cities/search"
                           +"?apikey="+
                                API_KEY + "&q=" +orasCodificat +"&metric=true";

                   URL url = new URL(link);
                   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                   BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                   StringBuilder builder = new StringBuilder("");

                   String linie;
                   while ((linie = reader.readLine()) != null) {
                       builder.append(linie);
                   }
                   reader.close();
                   conn.disconnect();


                   JSONArray vector = new JSONArray(builder.toString());

                   if (vector.length() == 0) {
                       handler.post(() -> tvRezultat.setText("Orașul nu a fost găsit."));
                       return;
                   }
                   JSONObject obiect = vector.getJSONObject(0);
                   String cheieOras = obiect.getString("Key");

                   handler.post(() -> {
                       Toast.makeText(VremeActivity.this, "Cod obtinut: " + cheieOras, Toast.LENGTH_SHORT).show();
                   });

                   //String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                         //  + cheieOras + "?apikey=" + API_KEY + "&metric=true";


                   String tipPrognoza = optiuneAleasa.equals("5 zile") ? "5day" : "1day";
                   String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/"
                           + tipPrognoza + "/" + cheieOras + "?apikey=" + API_KEY + "&metric=true";



                   String raspunsVreme = citesteUrl(linkVreme);
                   JSONObject jsonVreme = new JSONObject(raspunsVreme);
                   JSONArray dailyForecasts = jsonVreme.getJSONArray("DailyForecasts");
                   JSONObject primaZi = dailyForecasts.getJSONObject(0);

                   //JSONObject temperature = primaZi.getJSONObject("Temperature");
                   //double tempMin = temperature.getJSONObject("Minimum").getDouble("Value");
                   //double tempMax = temperature.getJSONObject("Maximum").getDouble("Value");

                   StringBuilder rezultatFinal = new StringBuilder();
                   rezultatFinal.append("Cod oraș: ").append(cheieOras).append("\n\n");

//                   handler.post(() -> tvRezultat.setText(
//                           "Cod oras: " + cheieOras + "\n" +
//                                   "Temperatura minima: " + tempMin + " °C\n" +
//                                   "Temperatura maxima: " + tempMax + " °C"
//                   ));

                   for (int i = 0; i < dailyForecasts.length(); i++) {
                       JSONObject zi = dailyForecasts.getJSONObject(i);
                       String data = zi.getString("Date").substring(0, 10);

                       JSONObject temperature = zi.getJSONObject("Temperature");
                       double tempMin = temperature.getJSONObject("Minimum").getDouble("Value");
                       double tempMax = temperature.getJSONObject("Maximum").getDouble("Value");

                       rezultatFinal.append("Data: ").append(data)
                               .append("\nMin: ").append(tempMin).append(" °C")
                               .append(" | Max: ").append(tempMax).append(" °C\n\n");
                   }
                   handler.post(() -> tvRezultat.setText(rezultatFinal.toString()));


               }catch(Exception e){
                   e.printStackTrace();
                   handler.post(()->tvRezultat.setText("Eroare" + e.getMessage()));
               }
           });
       });
    }



    private String citesteUrl(String link) throws Exception{
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder builder = new StringBuilder();


        String linie;
        while ((linie = reader.readLine()) != null) {
            builder.append(linie);
        }
        reader.close();
        conn.disconnect();
        return builder.toString();
    }
}
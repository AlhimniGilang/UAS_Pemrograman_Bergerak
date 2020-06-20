package com.example.uasmobilecomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasmobilecomputing.Model.ModelDataIndonesia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tSembuh,tPositif,tMeninggal;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tSembuh = findViewById(R.id.tSembuh);
        tPositif = findViewById(R.id.tPositif);
        tMeninggal = findViewById(R.id.tMeninggal);
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading");
            dialog.setCancelable(false);
            dialog.show();
        retrofit2.Call<List<ModelDataIndonesia>> call = Api.service().getData();
        call.enqueue (new Callback<List<ModelDataIndonesia>>() {
            @Override
            public void onResponse(Call<List<ModelDataIndonesia>> call, Response<List<ModelDataIndonesia>> response) {
                tSembuh.setText(response.body().get(0).getSembuh());
                tPositif.setText(response.body().get(0).getPositif());
                tMeninggal.setText(response.body().get(0).getMeninggal());
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelDataIndonesia>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }


        });
    }
}

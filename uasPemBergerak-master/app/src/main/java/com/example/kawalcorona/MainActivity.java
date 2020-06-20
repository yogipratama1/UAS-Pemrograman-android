package com.example.kawalcorona;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kawalcorona.Model.Api;
import com.example.kawalcorona.Model.ModelDataInd;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tSembuh, tMeninggal, tPositif, tDirawat;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tSembuh     = findViewById(R.id.tSembuh);
        tMeninggal  = findViewById(R.id.tMeninggal);
        tPositif    = findViewById(R.id.tPositif);
        tDirawat    = findViewById(R.id.tDirawat);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();
        Call<List<ModelDataInd>> call = Api.service().getData();
        call.enqueue(new Callback<List<ModelDataInd>>() {
            @Override
            public void onResponse(Call<List<ModelDataInd>> call, Response<List<ModelDataInd>> response) {
                tSembuh.setText(response.body().get(0).getSembuh());
                tMeninggal.setText(response.body().get(0).getMeninggal());
                tPositif.setText(response.body().get(0).getPositif());
                tDirawat.setText(response.body().get(0).getDirawat());
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelDataInd>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
}

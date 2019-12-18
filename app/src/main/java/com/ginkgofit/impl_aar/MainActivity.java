package com.ginkgofit.impl_aar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ginkgofit.my_library.AlipayHelper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRetrofit();
            }
        });

        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlipayHelper.get().pay("", MainActivity.this, new AlipayHelper.OnPayResultListener() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String s) {
                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void createRetrofit() {
        try {
            OkHttpClient httpClient = new OkHttpClient.Builder().build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl("http://baidu.com/")
                    .build();
            retrofit.create(RemoteService.class);
        } catch (Throwable t) {
            t.printStackTrace();
            Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

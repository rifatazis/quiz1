package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    DecimalFormat x = new DecimalFormat("#,###.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvAtas = findViewById(R.id.tvAtas);
        TextView tvTengah = findViewById(R.id.tvTengah);
        Button btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        if (intent != null) {
            String nama = intent.getStringExtra("nama");
            String tipeMember = intent.getStringExtra("tipeMember");
            String kodeBarang = intent.getStringExtra("kodeBarang");
            String namaBarang = intent.getStringExtra("namaBarang");
            double hargaBarang = intent.getDoubleExtra("hargaBarang", 0);
            double totalHarga = intent.getDoubleExtra("totalHarga", 0);
            double diskonHarga = intent.getDoubleExtra("diskonHarga", 0);
            double diskonMember = intent.getDoubleExtra("diskonMember", 0);
            double jumlahBayar = intent.getDoubleExtra("jumlahBayar", 0);

            tvAtas.setText("Selamat Datang " + nama + "\n Tipe Member " + tipeMember);
            tvTengah.setText("Transaksi Hari Ini.\n" +
                            "Kode Barang: " + kodeBarang + "\n" +
                            "Nama Barang: " + namaBarang + "\n" +
                            "Harga: Rp. " + x.format(hargaBarang) + "\n" +
                            "Total Harga: Rp. " + x.format(totalHarga) + "\n" +
                            "Discount Harga: Rp. " + x.format(diskonHarga) + "\n" +
                            "Discount Member: Rp. " + x.format(diskonMember) + "\n" +
                            "Jumlah Bayar: Rp. " + x.format(jumlahBayar));

            btnShare.setOnClickListener(this);
        }
    }
    @Override
    public void onClick (View v){
        String namaBarang = getIntent().getStringExtra("namaBarang");
        double jumlahBayar = getIntent().getDoubleExtra("jumlahBayar", 0);

        String shareMessage =
                "Nama Barang: " + namaBarang + "\n" +
                "Melakukan transaksi sebesar Rp." + x.format(jumlahBayar)+
                "\npada RifatAlAzizQuiz1";

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, shareMessage);

        startActivity(Intent.createChooser(share, "Bagikan via"));
    }
}

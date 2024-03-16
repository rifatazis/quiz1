package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNama, etKode, etJumlah;
    private RadioButton rbGold, rbSilver, rbBiasa;
    private Button btnProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etKode = findViewById(R.id.etKode);
        etJumlah = findViewById(R.id.etJumlah);
        rbGold = findViewById(R.id.rbGold);
        rbSilver = findViewById(R.id.rbSilver);
        rbBiasa = findViewById(R.id.rbBiasa);
        btnProses = findViewById(R.id.btnProses);

        btnProses.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nama = etNama.getText().toString();
        String kode = etKode.getText().toString().toUpperCase();
        int jumlah = Integer.parseInt(etJumlah.getText().toString());

        double totalSementara = 0;
        double hargaBarang = 0;
        String NamaBarang = "";
        switch (kode) {
            case "IPX":
                hargaBarang = 5725300;
                NamaBarang = " Iphone X";
                break;
            case "PCO":
                hargaBarang = 2730551;
                NamaBarang = " POCO M3";
                break;
            case "AAE":
                hargaBarang = 8676981;
                NamaBarang = " Acer Aspire E14 ";
                break;
            default:
                Toast.makeText(this, "pilih IPX,PCO, atau AAE", Toast.LENGTH_SHORT).show();
                return;
        }

        totalSementara = hargaBarang * jumlah;
        double diskonMember = 0;
        String member = "";
        if (rbGold.isChecked()) {
            diskonMember =0.1 * totalSementara;
            member = "Gold";
        } else if (rbSilver.isChecked()) {
            diskonMember =0.05 * totalSementara;
            member = "Silver";
        } else if (rbBiasa.isChecked()) {
            diskonMember =0.02 * totalSementara;
            member = "Biasa";
        }

        double diskonHarga =0;
        if (totalSementara > 10000000) {
            diskonHarga = 100000;
        }

        double totalHarga = totalSementara - diskonMember - diskonHarga;

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("nama", nama);
        intent.putExtra("tipeMember", member);
        intent.putExtra("kodeBarang", kode);
        intent.putExtra("namaBarang", NamaBarang);
        intent.putExtra("hargaBarang", hargaBarang);
        intent.putExtra("totalHarga", totalSementara);
        intent.putExtra("diskonHarga", diskonHarga);
        intent.putExtra("diskonMember", diskonMember);
        intent.putExtra("jumlahBayar", totalHarga);
        startActivity(intent);
    }
}

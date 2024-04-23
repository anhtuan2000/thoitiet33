package com.example.thoitiet33;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText edtTenTp;
    private TextView tvThanhPho, tvQuocGia, tvNhietDo, tvTrangThai, tvDoAm, tvMay, tvGio, tvNgayThang;
    private Button btnThanhPho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần từ layout XML
        edtTenTp = findViewById(R.id.edtTenTp);
        tvThanhPho = findViewById(R.id.tvThanhPho);
        tvQuocGia = findViewById(R.id.tvQuocGia);
        tvNhietDo = findViewById(R.id.tvNhietDo);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        tvDoAm = findViewById(R.id.tvDoAm);
        tvMay = findViewById(R.id.tvMay);
        tvGio = findViewById(R.id.tvGio);
        tvNgayThang = findViewById(R.id.tvNgayThang);
        btnThanhPho = findViewById(R.id.btnThanhPho);

        // Khởi tạo Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference weatherRef = database.getReference("weather_forecast");

        // Xử lý sự kiện khi người dùng nhấn nút "Chọn"
        btnThanhPho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy tên tỉnh thành từ ô nhập liệu
                String tenTp = edtTenTp.getText().toString();

                // Lấy dữ liệu từ Firebase dựa trên tên tỉnh thành
                weatherRef.child(tenTp).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Lấy dữ liệu từ dataSnapshot và cập nhật lên giao diện
                            WeatherData weatherData = dataSnapshot.getValue(WeatherData.class);
                            if (weatherData != null) {
                                tvThanhPho.setText(tenTp);

                                tvNhietDo.setText(weatherData.getTemperature() + "°C");

                                tvDoAm.setText(weatherData.getHumidity() + "%");
                                tvMay.setText(weatherData.getCloudiness() + "%");
                                tvGio.setText(weatherData.getWindSpeed() + " km/h");

                                // Đặt ảnh thời tiết tương ứng
                                //imgThoiTiet.setImageResource(R.drawable.ic_sunny); // Ví dụ: sử dụng ảnh cho trời nắng
                                // Chú ý: Bạn cần tải ảnh thời tiết từ dữ liệu hoặc sử dụng thư viện hình ảnh để tải từ URL
                            }
                        } else {
                            // Nếu không tìm thấy dữ liệu cho tỉnh thành này, hiển thị thông báo hoặc xử lý khác
                            // Ví dụ: Toast.makeText(MainActivity.this, "Không tìm thấy dữ liệu cho tỉnh thành này", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Xử lý khi có lỗi xảy ra
                    }
                });
            }
        });
    }
}

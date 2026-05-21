package com.example.session_4;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnInfo;
    private Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btn_back);
        btnInfo = findViewById(R.id.btn_info);
        btnAction = findViewById(R.id.btn_action);

        btnBack.setOnClickListener(view -> finish());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new InfoFragment())
                .commit();

        btnInfo.setOnClickListener(view -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new InfoFragment())
                .commit());

        btnAction.setOnClickListener(view -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ActionFragment())
                .commit());
    }
}
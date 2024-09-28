package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText etAgeValue, etHeightValue, etWeightValue;
    TextView tvAnswer;
    Button button, buttonClearHistory;
    RecyclerView recyclerViewHistory;
    HistoryAdapter historyAdapter;
    List<String> bmiHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        etAgeValue = findViewById(R.id.etAgeValue);
        etHeightValue = findViewById(R.id.etHeightValue);
        etWeightValue = findViewById(R.id.etWeightValue);
        tvAnswer = findViewById(R.id.tvAnswer);
        button = findViewById(R.id.button);
        buttonClearHistory = findViewById(R.id.buttonClearHistory);
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        bmiHistory = new ArrayList<>();

        historyAdapter = new HistoryAdapter(bmiHistory);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistory.setAdapter(historyAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double age = Double.parseDouble(etAgeValue.getText().toString());
                    double height = Double.parseDouble(etHeightValue.getText().toString());
                    double weight = Double.parseDouble(etWeightValue.getText().toString());

                    double heightInMeters = height / 100;
                    double bmi = weight / (heightInMeters * heightInMeters);

                    String resultMessage = "Your BMI is: " + String.format("%.2f", bmi) + "\n";
                    String advice = determineAdvice(bmi);

                    bmiHistory.add(resultMessage + advice);
                    historyAdapter.notifyItemInserted(bmiHistory.size() - 1);
                    recyclerViewHistory.scrollToPosition(bmiHistory.size() - 1);

                    tvAnswer.setText(resultMessage + advice);

                } catch (NumberFormatException e) {
                    tvAnswer.setText("Please enter valid data.");
                }
            }
        });

        buttonClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmiHistory.clear();
                historyAdapter.notifyDataSetChanged();
                tvAnswer.setText("BMI History cleared.");
            }
        });
    }

    private String determineAdvice(double bmi) {
        if (bmi < 18.5) {
            return "BMI Interpretation: Underweight\nConsider a balanced diet to gain weight.";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "BMI Interpretation: Normal weight\nKeep maintaining a healthy lifestyle.";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "BMI Interpretation: Overweight\nConsider a diet and exercise to lose weight.";
        } else {
            return "BMI Interpretation: Obesity\nConsult a healthcare provider for advice.";
        }
    }
}

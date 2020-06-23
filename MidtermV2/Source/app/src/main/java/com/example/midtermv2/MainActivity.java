package com.example.midtermv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare variables
        Button button;
        final EditText input;
        final TextView output;

        //set views
        input = findViewById(R.id.editText_input);
        output = findViewById(R.id.textView_output);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get dollars from EditText
                String in = input.getText().toString();
                //check if empty
                if (TextUtils.isEmpty(in))
                {
                    input.setError("Required");
                    return;
                }
                double dollars = Double.parseDouble(in);

                //convert to euro
                double euros = dollars * 0.88;

                //output to TextView
                String out = Double.toString(euros) + " Euros";
                output.setText(out);
            }
        });
    }
}
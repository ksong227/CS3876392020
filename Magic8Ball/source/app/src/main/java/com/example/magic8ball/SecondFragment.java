package com.example.magic8ball;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Random;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //RNG
        Random random = new java.util.Random();
        int randomNum = random.nextInt(6);
        String ansText = "";

        //Select answer String from RNG and Send String to TextView
        ImageView randomImage = view.getRootView().findViewById(R.id.imageView2);

        switch (randomNum)
        {
            case 0: ansText = "<b>The Magic 8-Ball says...</b><br>Yes definitely.";
                    randomImage.setImageResource(R.drawable.ball0);
                break;
            case 1: ansText = "<b>The Magic 8-Ball says...</b><br>Signs point to yes.";
                    randomImage.setImageResource(R.drawable.ball1);
                break;
            case 2: ansText = "<b>The Magic 8-Ball says...</b><br>Cannot predict now...";
                    randomImage.setImageResource(R.drawable.ball2);
                break;
            case 3: ansText = "<b>The Magic 8-Ball says...</b><br>Very doubtful.";
                    randomImage.setImageResource(R.drawable.ball3);
                break;
            case 4: ansText = "<b>The Magic 8-Ball says...</b><br>My reply is no.";
                    randomImage.setImageResource(R.drawable.ball4);
                break;
            case 5: ansText = "<b>The Magic 8-Ball says...</b><br>Better not tell you now.";
                randomImage.setImageResource(R.drawable.ball5);
                break;
        }

        //Send String to TextView
        TextView randomView = view.getRootView().findViewById(R.id.textview_second);
        randomView.setText(Html.fromHtml(ansText));


        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}
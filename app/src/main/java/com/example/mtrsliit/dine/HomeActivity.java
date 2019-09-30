package com.example.mtrsliit.dine;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Button mtexthome,review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mtexthome = (Button) findViewById(R.id.Viewprofile);
        review = (Button)findViewById(R.id.feedback);

        mtexthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login =new Intent(HomeActivity.this,GetAllUsersActivity.class);
                startActivity(login);

            }



        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this,FoodReviewActivity.class);
                startActivity(intent);

            }



        });
    }

}


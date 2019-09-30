package com.example.mtrsliit.dine;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FoodReviewActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText txtId,txtName,txtReview,txtRate;
    Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_review);


        databaseHelper = new DatabaseHelper(this);

        txtId = (EditText)findViewById(R.id.edittext_id);
        txtName = (EditText)findViewById(R.id.edittext_name);
        txtReview = (EditText)findViewById(R.id.edittext_review);
        txtRate = (EditText)findViewById(R.id.edittext_rate);

        btn1 = (Button)findViewById(R.id.add);
        btn2 = (Button)findViewById(R.id.btShow);
        btn3 = (Button)findViewById(R.id.btdelete);
        btn4 = (Button)findViewById(R.id.btUpdate);


        AddReview();
        viewAll();
        updateReview();
        deleteReview();



    }

    private void clearControls(){
        txtName.setText("");
        txtReview.setText("");
        txtRate.setText("");
    }




    public void AddReview(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name = txtName.getText().toString();
                final String rate = txtRate.getText().toString();

                if (txtName.getText().toString().equals("")){
                    txtName.setError("Field cannot be empty");
                }
                else if (txtReview.getText().toString().equals("")){
                    txtReview.setError("Field cannot be empty");
                }
                else if(!rate.matches("([0-9])")){
                    txtRate.setError("Enter number between 1-10");
                }
                else {
                            if(!Name.matches("[a-zA-Z]+")){
                                txtName.requestFocus();
                                txtName.setError("Please enter alphabetic characters Only");
                           }else {
                                boolean isSend = databaseHelper.insertData1(txtName.getText().toString(), txtReview.getText().toString(), txtRate.getText().toString());
                                if (isSend == true)
                                    Toast.makeText(FoodReviewActivity.this, "Review added", Toast.LENGTH_LONG).show();

                                else
                                    Toast.makeText(FoodReviewActivity.this, "Review added faild", Toast.LENGTH_LONG).show();

                                clearControls();
                            }

                }

                    /*boolean isSend=databaseHelper.insertData1(txtName.getText().toString(),txtReview.getText().toString(),txtRate.getText().toString());
                    if (isSend==true)
                      Toast.makeText(FoodReviewActivity.this, "Review added",Toast.LENGTH_LONG).show();

                    else
                        Toast.makeText(FoodReviewActivity.this, "Review added faild",Toast.LENGTH_LONG).show();

                    clearControls();*/
                }

        });



    }

    public void viewAll(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = databaseHelper.getAllReview();
                if (res.getCount()==0){
                    //show msg
                    showMessage("Error", "No Data Found!!!");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id: " + res.getString(0)+ "\n");
                    buffer.append("FoodName: " + res.getString(1)+ "\n");
                    buffer.append("Description: " + res.getString(2)+ "\n");
                    buffer.append("Rate: " + res.getString(3)+ "\n\n");

                }
                //show all data
                showMessage("Data", buffer.toString());

            }
        });
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void updateReview(){
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                boolean isSend=databaseHelper.updateReview(txtId.getText().toString(),txtName.getText().toString(),txtReview.getText().toString(),txtRate.getText().toString());
                if (isSend==true)
                    Toast.makeText(FoodReviewActivity.this, "Review Updated",Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(FoodReviewActivity.this, "Review Update faild",Toast.LENGTH_LONG).show();

                clearControls();
            }

        });



    }

    public void deleteReview(){
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows=databaseHelper.deleteReview(txtId.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(FoodReviewActivity.this, "Review Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(FoodReviewActivity.this, "Review Delete faild",Toast.LENGTH_LONG).show();

                clearControls();

            }
        });
    }


}

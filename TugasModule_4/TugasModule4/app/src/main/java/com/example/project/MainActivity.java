package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {

    private EditText phoneNumber;
    private EditText websiteUri;
    private EditText locationUri;
    private EditText textShare;

    Button buttonWebsite;
    Button buttonLocation;
    Button buttonText;
    Button buttonPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial
        phoneNumber = findViewById(R.id.edtPhoneNumber);
        websiteUri = findViewById(R.id.edtWebsiteUri);
        locationUri = findViewById(R.id.edtLocationUri);
        textShare = findViewById(R.id.edtShareText);

        buttonPhone = findViewById(R.id.btnPhoneNumber);
        buttonWebsite = findViewById(R.id.btnWebsiteUri);
        buttonLocation = findViewById(R.id.btnLocationUri);
        buttonText = findViewById(R.id.btnShareText);

        buttonPhone.setOnClickListener(this);
        buttonWebsite.setOnClickListener(this);
        buttonLocation.setOnClickListener(this);
        buttonText.setOnClickListener(this);
    }

    //run program when hit button and call function from interface MainView
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPhoneNumber:
                checkPhoneNumber();
                break;
            case R.id.btnWebsiteUri:
                checkUrl();
                break;
            case R.id.btnLocationUri:
                checkLocation();
                break;
            case R.id.btnShareText:
                checkShareText();
                break;
        }
    }

    //for check edittext number phone if isEmpty show toast else notEmpty run the program
    @Override
    public void checkPhoneNumber() {
        if (!phoneNumber.getText().toString().isEmpty()) {
            Intent dialPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.getText().toString()));
            startActivity(dialPhone);
        } else {
            Toast.makeText(this, "Number phone cannot empty", Toast.LENGTH_SHORT).show();
        }
    }

    //for check edittext url, if this edittext is empty show toast else not empty run the program
    @Override
    public void checkUrl() {
        if (!websiteUri.getText().toString().isEmpty()) {
            Intent openWebsite = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("http://" + websiteUri.getText().toString()));
            startActivity(openWebsite);
        } else {
            Toast.makeText(this, "Url cannot empty", Toast.LENGTH_SHORT).show();
        }
    }

    //for check edittext location, if this is empty show toast else run the program
    @Override
    public void checkLocation() {
        if (!locationUri.getText().toString().isEmpty()) {
            Intent openLocation = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" +
                    locationUri.getText().toString()));
            startActivity(openLocation);
        } else {
            Toast.makeText(this, "Location cannot empty", Toast.LENGTH_SHORT).show();
        }
    }

    //for check edittext text share, if this is empty show toast else run the program
    @Override
    public void checkShareText() {
        if (!textShare.getText().toString().isEmpty()) {
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType("text/plan")
                    .setChooserTitle("Share this text with : "
                    )
                    .setText(textShare.getText().toString())


                    .startChooser();
        } else {
            Toast.makeText(this, "Share text cannot empty", Toast.LENGTH_SHORT).show();
        }
    }
}

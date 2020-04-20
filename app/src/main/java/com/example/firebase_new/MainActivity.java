package com.example.firebase_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.UUID;

import static com.example.firebase_new.FBref.refStuddentGrade;
import static com.example.firebase_new.FBref.refStudents;

public class MainActivity extends AppCompatActivity {
    EditText S_Name,Address,S_Phone,HomePhone,M_Name,M_Phone,D_Name,D_Phone,Subject,Quarter,Grade;
    Student_Private_Info SPI;
    StuGrades SG;
    String uniqueID = UUID.randomUUID().toString();;

    /**
     * @author Dvir Dadon
     * @Version 3.6.3
     * @since 19/4/2020
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        S_Name = findViewById(R.id.S_Name);
        Address = findViewById(R.id.S_Name);
        S_Phone = findViewById(R.id.S_Phone);
        HomePhone = findViewById(R.id.HomePhone);
        M_Name = findViewById(R.id.M_Name);
        M_Phone = findViewById(R.id.M_Phone);
        D_Name = findViewById(R.id.D_Name);
        D_Phone = findViewById(R.id.D_Phone);
        Subject = findViewById(R.id.Subject);
        Quarter = findViewById(R.id.Quarter);
        Grade = findViewById(R.id.Grade);
    }

    /**
     * @since 19/4/2020
     * @param view
     * The method insert the info the user entered to the firebase data
     */

    public void Data_In(View view) {
        String Student_N = S_Name.getText().toString();
        String address = Address.getText().toString();
        String Mom_N = M_Name.getText().toString();
        String Dad_N = D_Name.getText().toString();
        uniqueID = UUID.randomUUID().toString();

        String Student_P = S_Phone.getText().toString();
        String HomeP = HomePhone.getText().toString();
        String Mom_Phone = M_Phone.getText().toString();
        String Dad_Phone = D_Phone.getText().toString();

        int S_P = Integer.parseInt(Student_P);
        int HP = Integer.parseInt(HomeP);
        int M_P = Integer.parseInt(Mom_Phone);
        int D_P = Integer.parseInt(Dad_Phone);

        SPI = new Student_Private_Info(Student_N,address,S_P,HP,Mom_N,M_P,Dad_N,D_P,uniqueID);
        refStudents.child(uniqueID).setValue(SPI);
    }

    /**
     * @since 19/4/2020
     * @param view
     * The method insert the info the user entered to the firebase data
     */
    public void Grades_In(View view) {
        String subject = Subject.getText().toString();
        String quarter = Quarter.getText().toString();
        String grade = Grade.getText().toString();

        int Q = Integer.parseInt(quarter);
        int G = Integer.parseInt(grade);

        SG = new StuGrades(subject,Q,G,uniqueID);
        refStuddentGrade.child(uniqueID).setValue(SG);
    }

    /**
     * @since 19/4/2020
     * @param menu
     * @return menu inflater
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    /**
     * @since 19/4/2020
     * @param item
     * @return moving to the next activity as the user choice
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.menuDelete): {
                Intent s = new Intent(this, menuDelete.class);
                startActivity(s);
                break;
            }
            case (R.id.menuSort): {
                Intent s = new Intent(this, menuSort.class);
                startActivity(s);
                break;
            }
            case (R.id.Credits): {
                Intent s = new Intent(this, Credits.class);
                startActivity(s);
                break;
            }
            default:
                break;
        }
        return true;
    }
}


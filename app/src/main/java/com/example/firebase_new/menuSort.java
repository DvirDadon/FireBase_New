package com.example.firebase_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebase_new.FBref.refStudentGrade;
import static com.example.firebase_new.FBref.refStudents;

public class menuSort extends AppCompatActivity {
    ArrayList<String> stuGradeList = new ArrayList<String>();
    ArrayList<StuGrades> stuGradeValues = new ArrayList<StuGrades>();
    ArrayList<StuGrades> stuSub = new ArrayList<StuGrades>();
    ArrayList<String> sorted = new ArrayList<String>();
    ListView  lvGrades,lvsorted;
    String  str3, str4;
    ArrayAdapter<String> adp2;
    ArrayAdapter<String> adp3;
    EditText Filter_Grade,Filter_Subject;
    ValueEventListener stuGradeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sort);
        lvGrades = findViewById(R.id.lvGrades);
        lvsorted = findViewById(R.id.lvsorted);
        Filter_Grade = findViewById(R.id.Filter_Grade);
        Filter_Subject = findViewById(R.id.Filter_Subject);

        ValueEventListener StuGradeListener = new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dS) {
                stuGradeList.clear();
                stuGradeValues.clear();
                for (DataSnapshot data : dS.getChildren()){
                    str3 = (String) data.getKey();
                    StuGrades stuGradeTmp = data.getValue(StuGrades.class);
                    stuGradeValues.add(stuGradeTmp);
                    str4 = stuGradeTmp.getSubject()+","+stuGradeTmp.getQuarter()+","+stuGradeTmp.getGrade();
                    stuGradeList.add(str3+" "+str4);
                }
                adp2 = new ArrayAdapter<String>(menuSort.this,R.layout.support_simple_spinner_dropdown_item,stuGradeList);
                lvGrades.setAdapter(adp2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refStudentGrade.addValueEventListener(StuGradeListener);
    }

    /**
     * @since 26/4/2020
     * The method filtering the subject by user input
     * @param view
     */
    public void Filter_Subject(View view) {
        String sub =Filter_Subject.getText().toString();
        Query query = refStudentGrade.orderByChild("subject").equalTo(sub);

        ValueEventListener stuGradeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                sorted.clear();
                stuSub.clear();
                for (DataSnapshot data : dS.getChildren()){
                    str3 = (String) data.getKey();
                    StuGrades stuGradeTmp = data.getValue(StuGrades.class);
                    stuSub.add(stuGradeTmp);
                    str4 = stuGradeTmp.getSubject()+","+stuGradeTmp.getQuarter()+","+stuGradeTmp.getGrade();
                    sorted.add(str3+" "+str4);
                }
                adp3 = new ArrayAdapter<String>(menuSort.this,R.layout.support_simple_spinner_dropdown_item, sorted);
                lvsorted.setAdapter(adp3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(stuGradeListener); }

    /**
     * @since 26/4/2020
     * The method filtering grade upward from the input that entered.
     * @param view
     */
    public void Filter_Grade(View view) {
        String grade = Filter_Grade.getText().toString();
        long G = Long.parseLong(grade);
        Query query = refStudentGrade.orderByChild("grade").startAt(G);

        ValueEventListener stuGradeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                sorted.clear();
                stuSub.clear();
                for (DataSnapshot data : dS.getChildren()){
                    str3 = (String) data.getKey();
                    StuGrades stuGradeTmp = data.getValue(StuGrades.class);
                    stuSub.add(stuGradeTmp);
                    str4 = stuGradeTmp.getSubject()+","+stuGradeTmp.getQuarter()+","+stuGradeTmp.getGrade();
                    sorted.add(str3+" "+str4);
                }
                adp3 = new ArrayAdapter<String>(menuSort.this,R.layout.support_simple_spinner_dropdown_item, sorted);
                lvsorted.setAdapter(adp3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(stuGradeListener); }

    public void EndAct () {
        if (stuGradeList!=null) {
            refStudentGrade.removeEventListener(stuGradeListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.menuDataIn): {
                Intent s = new Intent(this, MainActivity.class);
                EndAct();
                startActivity(s);

                break;
            }
            case (R.id.menuDelete): {
                Intent s = new Intent(this, menuDelete.class);
                EndAct();
                startActivity(s);
                break;
            }
            case (R.id.Credits): {
                Intent s = new Intent(this, Credits.class);
                EndAct();
                startActivity(s);
                break;
            }
            default:
                break;
        }
        return true;
    }
}

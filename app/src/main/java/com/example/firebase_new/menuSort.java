package com.example.firebase_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebase_new.FBref.refStuddentGrade;
import static com.example.firebase_new.FBref.refStudents;

public class menuSort extends AppCompatActivity {
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student_Private_Info> stuValues = new ArrayList<Student_Private_Info>();
    ArrayList<String> stuGradeList = new ArrayList<String>();
    ArrayList<StuGrades> stuGradeValues = new ArrayList<StuGrades>();
    ArrayList<StuGrades> stuSub = new ArrayList<StuGrades>();
    ArrayList<String> sorted = new ArrayList<String>();
    ListView lv, lvGrades,lvsorted;
    String str1, str2, str3, str4;
    ArrayAdapter<String> adp;
    ArrayAdapter<String> adp2;
    ArrayAdapter<String> adp3;
    EditText Filter_Grade;
    ValueEventListener stuListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sort);
        lv = findViewById(R.id.lv);
        lvGrades = findViewById(R.id.lvGrades);
        lvsorted = findViewById(R.id.lvsorted);
        Filter_Grade = findViewById(R.id.Filter_Grade);
        ValueEventListener stuListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                stuGradeList.clear();
                stuGradeValues.clear();
                for (DataSnapshot data : dS.getChildren()){
                    str1 = (String) data.getKey();
                    Student_Private_Info stuTmp = data.getValue(Student_Private_Info.class);
                    stuValues.add(stuTmp);
                    str2 = stuTmp.getStudent_N();
                    stuList.add(str1+" "+str2);
                }
                for (DataSnapshot data : dS.getChildren()){
                    str3 = (String) data.getKey();
                    StuGrades stuGradeTmp = data.getValue(StuGrades.class);
                    stuGradeValues.add(stuGradeTmp);
                    str4 = stuGradeTmp.getSubject();
                    stuGradeList.add(str3+" "+str4);
                }
                adp = new ArrayAdapter<String>(menuSort.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                lv.setAdapter(adp);
                adp2 = new ArrayAdapter<String>(menuSort.this,R.layout.support_simple_spinner_dropdown_item,stuGradeList);
                lvGrades.setAdapter(adp2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refStudents.addValueEventListener(stuListener);
    }

    public void Filter_Subject(View view) {
        String sub = Filter_Grade.getText().toString();
        Query query = refStuddentGrade.equalTo(sub);
        sorted.clear();
        ValueEventListener stuListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                for (DataSnapshot data : dS.getChildren()){
                    str3 = (String) data.getKey();
                    StuGrades stuGradeTmp = data.getValue(StuGrades.class);
                    stuSub.add(stuGradeTmp);
                    str4 = stuGradeTmp.getQuarter()+"";
                    sorted.add(str3+" "+str4);
                }
                adp3 = new ArrayAdapter<String>(menuSort.this,R.layout.support_simple_spinner_dropdown_item, sorted);
                lvsorted.setAdapter(adp3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refStudents.addValueEventListener(stuListener);
        query.addListenerForSingleValueEvent(stuListener);
    }

    public void Filter_Grade(View view) {

    }
}

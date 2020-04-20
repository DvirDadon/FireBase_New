package com.example.firebase_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebase_new.FBref.refStuddentGrade;
import static com.example.firebase_new.FBref.refStudents;

public class menuDelete extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student_Private_Info> stuValues = new ArrayList<Student_Private_Info>();
    ArrayList<String> stuGradeList = new ArrayList<String>();
    ArrayList<StuGrades> stuGradeValues = new ArrayList<StuGrades>();
    ListView lv,lvGrades;
    String str1,str2,str3,str4;
    ArrayAdapter<String> adp;
    ArrayAdapter<String> adp2;
    AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_delete);
        lv = findViewById(R.id.lv);
        lvGrades = findViewById(R.id.lvGrades);
        lvGrades.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvGrades.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);
        ValueEventListener stuListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {

                stuList.clear();
                stuValues.clear();
                stuGradeList.clear();
                stuGradeValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
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

                adp = new ArrayAdapter<String>(menuDelete.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                lv.setAdapter(adp);
                adp2 = new ArrayAdapter<String>(menuDelete.this,R.layout.support_simple_spinner_dropdown_item,stuGradeList);
                lvGrades.setAdapter(adp2);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refStudents.addValueEventListener(stuListener);
    }

    /**
     * @since 20/4/2020
     * the method checking if the user want to delete information by long click
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Warning!");
        adb.setMessage("Are you sure you want to delete the information?");
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                adb.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         String strstuID=stuValues.get(pos).getStuID();
                        refStudents.child(strstuID).removeValue();
                        stuList.remove(pos);
                        adp.notifyDataSetChanged();

                        strstuID=stuGradeValues.get(pos).getStuID();
                        refStuddentGrade.child(strstuID).removeValue();
                        stuGradeList.remove(pos);
                        adp2.notifyDataSetChanged();
                    }
                });
                adb.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog ad = adb.create();
                ad.show();
                return true;
            }
        });
        lvGrades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                adb.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                adb.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog ad = adb.create();
                ad.show();
                return true;
            }
        });


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


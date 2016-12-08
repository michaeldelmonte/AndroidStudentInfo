package com.example.androidstudentinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtIdNumber, txtFullName;
    Spinner sp_spinner;
    RadioGroup rbg_radioGroup;

    String[] courses;
    String selected_course;

    String selected_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // spinner
        courses = getResources().getStringArray(R.array.course_array);
        sp_spinner = (Spinner) findViewById(R.id.sp_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courses);
        sp_spinner.setAdapter(adapter);
        sp_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = adapterView.getSelectedItemPosition();
                selected_course=courses[index];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // radiogroup
        rbg_radioGroup = (RadioGroup) findViewById(R.id.rbg_radioGroup);
        rbg_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.rb1);
                if (rb1.isChecked()) {
                    selected_gender="Male";
                } else {
                    selected_gender="Female";
                }
            }
        });

        findViewById(R.id.txtIdNumber).requestFocus(); // set txt id number field selected on start
    }

    public void handleSubmit(View view) {
        txtIdNumber = (EditText) findViewById(R.id.txtIdNumber);
        txtFullName = (EditText) findViewById(R.id.txtFullName);

        String strIdNumber = txtIdNumber.getText().toString();
        String strFullName = txtFullName.getText().toString();

        if (!strIdNumber.equals("") || !strFullName.equals("") || !selected_course.equals("----")) {
            String header_dialog="Student Information";
            String id_number_dialog="ID Number: "+strIdNumber;
            String full_name_dialog="Full Name: "+strFullName;
            String gender_dialog="Gender: "+selected_gender;
            String course_dialog="Course: "+selected_course;

            ((EditText) findViewById(R.id.txtIdNumber)).getText().clear();
            ((EditText) findViewById(R.id.txtFullName)).getText().clear();
            ((RadioButton) rbg_radioGroup.getChildAt(0)).setChecked(true);
            ((Spinner) findViewById(R.id.sp_spinner)).setSelection(0);

            findViewById(R.id.txtIdNumber).requestFocus();

            InfoDialogFragment infoDialogFragment = InfoDialogFragment.newInstance(header_dialog,id_number_dialog+"\n"+full_name_dialog+"\n"+gender_dialog+"\n"+course_dialog);
            infoDialogFragment.show(getFragmentManager(), "dialog");
        } else {
            Toast.makeText(getBaseContext(), "Missing field input!", Toast.LENGTH_SHORT).show();
        }
    }

    public void handleCancel(View view) {
        ((EditText) findViewById(R.id.txtIdNumber)).getText().clear();
        ((EditText) findViewById(R.id.txtFullName)).getText().clear();
        ((RadioButton) rbg_radioGroup.getChildAt(0)).setChecked(true);
        ((Spinner) findViewById(R.id.sp_spinner)).setSelection(0);

        findViewById(R.id.txtIdNumber).requestFocus();
    }
}

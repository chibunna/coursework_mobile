package com.mrchibunna.madDiscovery;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import practice.application.sqlitesaveuserdata.R;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper peopleDB;

    Button btnSave, btnView,btnUpdate,btnDelete;

    EditText etName,etLocation,etOrg,etID,etTime, etDate, etInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleDB = new DatabaseHelper(this);

        etName = (EditText) findViewById(R.id.etNewName);
        etLocation = (EditText) findViewById(R.id.etNewLocation);
        etOrg = (EditText) findViewById(R.id.etNewOrg);
        etTime = (EditText) findViewById(R.id.etNewTime);
        etInfo = (EditText) findViewById(R.id.etNewInfo);
        etDate = (EditText) findViewById(R.id.etNewDate);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnView = (Button) findViewById(R.id.btnView);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        AddData();
        ViewData();
        UpdateData();
        DeleteData();
    }

    public void AddData() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String location = etLocation.getText().toString();
                String organizers = etOrg.getText().toString();
                String addinfo = etInfo.getText().toString();
                String date = etDate.getText().toString();
                String time = etTime.getText().toString();


                boolean insertData = peopleDB.addData(name, location, organizers, date, time, addinfo);

                if (TextUtils.isEmpty(etName.getText())) {

                    btnSave.setEnabled(false);

                    etName.setError("First name is required!");
                }
                else {
                    btnSave.setEnabled(false);
                }

                if (insertData) {
                    Toast.makeText(MainActivity.this, "Event Successfully Inserted!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void ViewData(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = peopleDB.showData();

                if (data.getCount() == 0) {
                    display("Error", "No Event Found.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()) {
                    buffer.append("ID:  " + data.getString(0) + "\n");
                    buffer.append("Name:  " + data.getString(1) + "\n");
                    buffer.append("Location:  " + data.getString(2) + "\n");
                    buffer.append("Organizers:  " + data.getString(3) + "\n");
                    buffer.append("Date:  " + data.getString(4) + "\n");
                    buffer.append("Time:  " + data.getString(5) + "\n");
                    buffer.append("Comments:  " + data.getString(6) + "\n");

                    display("Your Event Details:", buffer.toString());
                }
            }
        });
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if (temp > 0) {
                    boolean update = peopleDB.updateData(etID.getText().toString(), etName.getText().toString(),
                            etLocation.getText().toString(), etOrg.getText().toString(),etDate.getText().toString(),etTime.getText().toString(), etInfo.getText().toString());

                    if (update == true) {
                        Toast.makeText(MainActivity.this, "Successfully Updated Event!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You Must Enter An ID to Update", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if(temp > 0){
                    Integer deleteRow = peopleDB.deleteData(etID.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(MainActivity.this, "Event Successfully Deleted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "You Must Enter An ID to Delete", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    }

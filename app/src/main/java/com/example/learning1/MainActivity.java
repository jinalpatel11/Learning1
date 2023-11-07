package com.example.learning1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert , update ,delete ,view ;

    DBHelper DB ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB  = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String contactTxt = contact.getText().toString();
                String dobTxt = dob.getText().toString();

                 Boolean checkinsertdata = DB.insertuserdata(nameTxt,contactTxt,dobTxt);

                 if(checkinsertdata)
                     Toast.makeText(MainActivity.this,"New Entry inserted",Toast.LENGTH_SHORT).show();
                 else
                     Toast.makeText(MainActivity.this,"New Entry not inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String contactTxt = contact.getText().toString();
                String dobTxt = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTxt,contactTxt,dobTxt);

                if(checkupdatedata)
                    Toast.makeText(MainActivity.this,"Entry updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry not updated",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(nameTxt);

                if(checkdeletedata)
                    Toast.makeText(MainActivity.this,"Entry deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry not deleted",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();

                Cursor result = DB.getData(nameTxt);

                if(result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext())
                {
                    buffer.append("Name:"+result.getString(0)+ "\n");
                    buffer.append("Contact:"+result.getString(1)+ "\n");
                    buffer.append("Date of Birth:"+result.getString(2)+ "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}
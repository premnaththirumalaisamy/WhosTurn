package com.premnath.whosturn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddMembersActivity extends AppCompatActivity {

    Button btn_add_member;
    EditText memberName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);
        btn_add_member = findViewById(R.id.add_btn);
        memberName = findViewById(R.id.member_name);

        btn_add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String member = memberName.getText().toString();
                addMember(member);
                Toast.makeText(AddMembersActivity.this,"Successfully added " + member , Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void addMember(String member){

        try {
            FileOutputStream writer = openFileOutput("Users",MODE_APPEND);
            OutputStreamWriter osWriter = new OutputStreamWriter(writer);
            osWriter.write(member);
            osWriter.write("\n");
            osWriter.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

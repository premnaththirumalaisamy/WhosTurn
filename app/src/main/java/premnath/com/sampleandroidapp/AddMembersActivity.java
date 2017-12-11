package premnath.com.sampleandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class AddMembersActivity extends AppCompatActivity {

    Button addMember;
    EditText memberName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);
        addMember = (Button)findViewById(R.id.add_btn);
        memberName = (EditText)findViewById(R.id.member_name);

        addMember.setOnClickListener(new View.OnClickListener() {
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

package com.premnath.whosturn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DisplayMembersActivity extends AppCompatActivity {

    private static final String membersFile = "Users";
    List<String> members = new ArrayList<>();

    ListView list_members;
    Button btn_add_members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        members = Utils.loadMembers(membersFile,this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,members);
        list_members = findViewById(R.id.members_list);
        list_members.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btn_add_members = findViewById(R.id.add_members);
        btn_add_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayMembersActivity.this,AddMembersActivity.class);
                startActivity(intent);
            }
        });
    }

}

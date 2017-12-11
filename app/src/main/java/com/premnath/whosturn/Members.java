package com.premnath.whosturn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Members extends AppCompatActivity {

    private static final String usersFile = "Users";
    List<String> members = new ArrayList<>();

    ListView listView;
    Button addMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        members = Utils.loadMembers(usersFile,this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,members);
        listView = (ListView)findViewById(R.id.members_list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addMembers = (Button)findViewById(R.id.add_members);
        addMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Members.this,AddMembersActivity.class);
                startActivity(intent);
            }
        });
    }

}

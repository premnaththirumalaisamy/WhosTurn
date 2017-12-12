package com.premnath.whosturn;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_display_members;
    Button btn_dustbin, btn_utensils, btn_bathroom, btn_living_room;

    private static final String membersFile = "Users";
    private static final String dustbinQueueFile = "btn_dustbin";
    private static final String utensilsQueueFile = "btn_utensils";
    private static final String bathRoomQueueFile = "bathroom";
    private static final String livingRoomQueueFile = "roomCleaning";

    Queue<String> dustbinQueue = new ArrayDeque<>();
    Queue<String> utensilsQueue = new ArrayDeque<>();
    Queue<String> bathroomQueue = new ArrayDeque<>();
    Queue<String> livingRoomQueue = new ArrayDeque<>();
    Queue<String> tempQueue;

    static AlertDialog adb;

    //TODO
    // Move members data to FireBase.
    // Support to skipp current turn.
    // Fairly assign tasks.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn_display_members = findViewById(R.id.btn_members);
        btn_dustbin = findViewById(R.id.dustbin);
        btn_utensils = findViewById(R.id.utensils);
        btn_bathroom = findViewById(R.id.bathroom);
        btn_living_room = findViewById(R.id.room);

        btn_display_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listMembers = new Intent(MainActivity.this,DisplayMembersActivity.class);
                startActivity(listMembers);
            }
        });

        btn_dustbin.setOnClickListener(this);
        btn_utensils.setOnClickListener(this);
        btn_bathroom.setOnClickListener(this);
        btn_living_room.setOnClickListener(this);
    }

    public void init(){
        List<String> defaultMembers = Utils.loadMembers(membersFile,this);

        List<String> members = Utils.loadMembers(dustbinQueueFile,this);
        members = (members.isEmpty())? defaultMembers : members;
        dustbinQueue = populateQueue(members);

        members = Utils.loadMembers(utensilsQueueFile,this);
        members = (members.isEmpty())? defaultMembers : members;
        utensilsQueue = populateQueue(members);

        members = Utils.loadMembers(livingRoomQueueFile,this);
        members = (members.isEmpty())? defaultMembers : members;
        livingRoomQueue = populateQueue(members);

        members = Utils.loadMembers(bathRoomQueueFile,this);
        members = (members.isEmpty())? defaultMembers : members;
        bathroomQueue = populateQueue(members);
    }

    public Queue<String> populateQueue(List<String> members){
        Queue<String> q = new ArrayDeque<String>();
        q.addAll(members);
        return q;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveStatus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveStatus();
    }

    protected void saveStatus(){
        if(!dustbinQueue.isEmpty())
            Utils.saveQueueStatus(dustbinQueueFile,this,dustbinQueue);
        if(!utensilsQueue.isEmpty())
            Utils.saveQueueStatus(utensilsQueueFile,this,utensilsQueue);
        if(!bathroomQueue.isEmpty())
            Utils.saveQueueStatus(bathRoomQueueFile,this,bathroomQueue);
        if(!livingRoomQueue.isEmpty())
            Utils.saveQueueStatus(livingRoomQueueFile,this,livingRoomQueue);
    }
    @Override
    public void onClick(View view) {
        String content="";
        switch (view.getId()){
            case R.id.dustbin:
                content = dustbinQueue.peek();
                tempQueue = dustbinQueue;
                break;
            case R.id.utensils:
                content = utensilsQueue.peek();
                tempQueue = utensilsQueue;
                break;
            case R.id.bathroom:
                content = bathroomQueue.peek();
                tempQueue = bathroomQueue;
                break;
            case R.id.room:
                content = livingRoomQueue.peek();
                tempQueue = livingRoomQueue;
                break;
        }

        adb = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Who's Turn")
                .setMessage(content)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    String user = tempQueue.remove();
                    tempQueue.add(user);
                } })
                .setNegativeButton("Not Yet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                } })
                .show();
    }

    public static AlertDialog getLastDialog(){
        return adb;
    }
}

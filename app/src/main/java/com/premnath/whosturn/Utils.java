package com.premnath.whosturn;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * Created by premnath on 05/12/17.
 */

public class Utils {

    public static List<String> loadMembers(String file,Context ctx){
        List<String > members = Collections.emptyList();
        try {
            FileInputStream inputStream = ctx.openFileInput(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            if(line!=null)members = new ArrayList<>();
            while (line!=null){
                if(!line.contains("member") && !line.contains("Name"))
                    members.add(line);
                line = reader.readLine();
            }
            inputStream.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    public static void saveQueueStatus(String file,Context ctx,Queue<String> content){

        try {
            FileOutputStream writer = ctx.openFileOutput(file,Context.MODE_PRIVATE);
            OutputStreamWriter osWriter = new OutputStreamWriter(writer);
            while(!content.isEmpty()) {
                String member = content.remove();
                osWriter.write(member);
                osWriter.write("\n");
            }
            osWriter.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package application.example.api_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        ArrayList<String> mvs= intent.getStringArrayListExtra("mvs");
        TextView g=findViewById(R.id.gen);
        g.setText(mvs.get(0));
        RecyclerView programmingList=(RecyclerView) findViewById(R.id.list);
        programmingList.setLayoutManager(new LinearLayoutManager(this));
        String[] names=new String[mvs.size()-1];
        for(int i=1;i<mvs.size();i++) {
            names[i - 1] = mvs.get(i);
        }
        programmingList.setAdapter(new MoviesAdapter(names));
    }
}
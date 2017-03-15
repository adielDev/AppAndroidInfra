package adiel.appandroidinfra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.Map;

import static adiel.androidinframodule.utils.MyUtils.getHashMapResource;


public class MainActivity extends AppCompatActivity {

    Map<String,String> classMap;
    String[] entries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.lv);
        entries = getResources().getStringArray(R.array.mainlist);
        classMap = getHashMapResource(getApplicationContext(),R.xml.confgs);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String className = entries[position];
                String classString = classMap.get(className);
                try {
                    Class<?> activity = Class.forName(classString);
                    intent.setClass(getApplicationContext(), activity);
                    startActivity(intent);
                }catch (Exception e){
                    if(e instanceof NullPointerException){
                        Log.e("adiel","className or classString is null");
                    }else  if(e instanceof ClassNotFoundException){
                        Log.e("adiel","activity not exist or not declare in manifest");
                    }else {
                        Log.e("adiel","not expected exception");
                    }
                    e.printStackTrace();

                }


            }
        });

    }






    @Override
    protected void onDestroy() {
        Log.d("adiel","MainActivity:onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("adiel","MainActivity:onResume");
        //registerReceiver(broadcastReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("adiel","MainActivity:onPause");
    }

}

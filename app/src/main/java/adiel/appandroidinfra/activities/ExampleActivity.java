package adiel.appandroidinfra.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import adiel.appandroidinfra.R;
import adiel.appandroidinfra.receivers.GateReceiver;
import adiel.appandroidinfra.receivers.ReceiverConst;
import adiel.appandroidinfra.servieces.DTOS.ExampleDto;
import adiel.appandroidinfra.servieces.MyIntentService;
import adiel.appandroidinfra.servieces.services.ExampleService;
import adiel.appandroidinfra.servieces.services.IExampleService;

public class ExampleActivity extends AppCompatActivity {

    Handler uiHndler = new Handler();
    TextView tv1;
    LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        tv1 = (TextView) findViewById(R.id.tv1);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ReceiverConst.LBActions.ExampleAction);
        localBroadcastManager.registerReceiver(MyReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent(this,GateReceiver.class);
        sendBroadcast(intent);
    }

    public void sendMessageToGateService(View view) {
        Intent messageToGateService = new Intent(ExampleActivity.this, MyIntentService.class);
        ExampleDto exampleDto = new ExampleDto("adiel",34);
        messageToGateService.putExtra(MyIntentService.DTO,exampleDto);
        messageToGateService.putExtra(MyIntentService.ISERVICE, IExampleService.class.getName());
        startService(messageToGateService);
    }

    public BroadcastReceiver MyReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            final ExampleDto exampleDto = intent.getParcelableExtra(ExampleService.RESULT);
            Log.d("adiel","exampleDto:"+exampleDto.toString());
            uiHndler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ExampleActivity.this, exampleDto.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

}

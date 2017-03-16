package adiel.appandroidinfra.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import adiel.appandroidinfra.R;
import adiel.appandroidinfra.receivers.GateReceiver;
import adiel.appandroidinfra.servieces.DTOS.ExampleDto;
import adiel.appandroidinfra.servieces.MyIntentService;
import adiel.appandroidinfra.servieces.services.IExampleService;

public class ExampleActivity extends AppCompatActivity {


    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        tv1 = (TextView) findViewById(R.id.tv1);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
}

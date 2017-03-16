package adiel.appandroidinfra.servieces;

import android.app.IntentService;
import android.content.Intent;

import java.util.LinkedHashMap;
import java.util.Map;

import adiel.appandroidinfra.MyApp;
import adiel.appandroidinfra.servieces.interfaces.GateServerDto;
import adiel.appandroidinfra.servieces.interfaces.GateService;

public class MyIntentService extends IntentService {

    public final static String DTO ="dto";
    public final static String ISERVICE="service";

    Map<String,String> serviceMap;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp myApp = (MyApp) getApplication();
        Map<String,String> appServiceMap = myApp.getServiceMap();
        serviceMap =new LinkedHashMap<>(appServiceMap);// help tp prevent memory leak? or not?
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            GateServerDto gateServerDto =intent.getParcelableExtra(DTO);
            String iservice = intent.getStringExtra(ISERVICE);
            String service = serviceMap.get(iservice);
            try {
                Class<GateService> gateServiceClass= (Class<GateService>) Class.forName(service);
                GateService gateService = gateServiceClass.newInstance();
                gateService.setDto(gateServerDto);
                gateService.execute(getApplicationContext());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}

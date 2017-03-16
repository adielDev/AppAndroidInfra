package adiel.appandroidinfra.servieces.services;

import android.content.Context;
import android.util.Log;

import adiel.appandroidinfra.servieces.DTOS.ExampleDto;
import adiel.appandroidinfra.servieces.interfaces.GateServerDto;
import adiel.appandroidinfra.servieces.interfaces.GateService;

/**
 * Created by recntrek7 on 16/03/17.
 */

public class ExampleService implements IExampleService{

    ExampleDto dto;

    @Override
    public void setDto(GateServerDto dto){
        this.dto = (ExampleDto) dto;
    }

    @Override
    public void execute(Context context) {
        Log.d("adiel",dto.toString());
    }
}

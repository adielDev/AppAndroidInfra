package adiel.appandroidinfra.servieces.interfaces;

import android.content.Context;

/**
 * Created by recntrek7 on 16/03/17.
 */

public interface GateService {

    void setDto(GateServerDto dto);
    void execute(Context context);
}

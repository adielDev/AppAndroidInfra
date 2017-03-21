package adiel.appandroidinfra.data;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;


public class TodosQueryHandler extends AsyncQueryHandler {
    public TodosQueryHandler(ContentResolver cr) {
        super(cr);
    }
}

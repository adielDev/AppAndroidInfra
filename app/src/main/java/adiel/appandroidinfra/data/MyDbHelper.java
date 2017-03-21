package adiel.appandroidinfra.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import static adiel.appandroidinfra.data.TodosContract.*;

/**
 * Created by recntrek7 on 14/03/17.
 */

public class MyDbHelper {

    public void updateTodo(ContentResolver contentResolver) {
        int id = 2;
        String[] args = {String.valueOf(id)};
        ContentValues values = new ContentValues();
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Call Mr Clark Kent");
        int numRows = contentResolver.update(TodosContract.TodosEntry.CONTENT_URI, values,
                TodosEntry._ID + "=?",  args);
        Log.d("Update Rows ", String.valueOf(numRows));

    }
    public void deleteTodo(ContentResolver contentResolver) {
        int id = 2;
        String[] args = {String.valueOf(id)};
        TodosQueryHandler handler = new TodosQueryHandler(contentResolver);
        handler.startDelete(1, null,
                TodosEntry.CONTENT_URI, TodosEntry._ID + " =?", args);

    }

    public void deleteAllTodo(ContentResolver contentResolver) {
        int numRows = contentResolver.delete(TodosEntry.CONTENT_URI, null, null);
        Log.d("Delete Rows", String.valueOf(numRows));
    }

    public void readData(ContentResolver contentResolver) {

        String[] projection = {TodosEntry.COLUMN_TEXT,
                TodosEntry.COLUMN_CREATED,
                TodosEntry.COLUMN_EXPIRED,
                TodosEntry.COLUMN_DONE,
                TodosContract.CategoriesEntry.COLUMN_DESCRIPTION};
        String selection = TodosEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {"1"};
        Cursor c = contentResolver.query(TodosEntry.CONTENT_URI, projection, null, null, null );
        int i = c.getCount();
        Log.d("Record Count", String.valueOf(i));
        String rowContent = "";
        while (c.moveToNext()) {
            for (i=0; i<=4; i++) {
                rowContent += c.getString(i) + " - ";
            }
            Log.i("Row " + String.valueOf(c.getPosition()), rowContent);
            rowContent = "";
        }
        c.close();
    }
    public void createTodo(ContentResolver contentResolver){

        ContentValues values = new ContentValues();
        values.put(TodosEntry.COLUMN_TEXT, "Call Mr Pink");
        values.put(TodosEntry.COLUMN_CATEGORY, 1);
        values.put(TodosEntry.COLUMN_CREATED, "2016-01-02");
        values.put(TodosEntry.COLUMN_DONE, 0);
        Uri uri = contentResolver.insert(TodosEntry.CONTENT_URI, values);
    }

    public void createCategory(ContentResolver contentResolver){
        ContentValues values = new ContentValues();
        values.put(TodosContract.CategoriesEntry.COLUMN_DESCRIPTION, "Work");
        Uri uri = contentResolver.insert(TodosContract.CategoriesEntry.CONTENT_URI,
                values);
        Log.d("MainActivity", "Inserted note " + uri);
    }

    public void createTestTodos(ContentResolver contentResolver) {

        for (int i = 1; i<=20; i++) {
            ContentValues values = new ContentValues();
            values.put(TodosEntry.COLUMN_TEXT, "Todo Item #" + i);
            values.put(TodosEntry.COLUMN_CATEGORY, 1);
            values.put(TodosEntry.COLUMN_CREATED, "2016-01-02");
            values.put(TodosEntry.COLUMN_DONE, 0);
            TodosQueryHandler handler = new TodosQueryHandler(contentResolver);
            handler.startInsert(1, null, TodosEntry.CONTENT_URI,
                    values );
        }
    }
}

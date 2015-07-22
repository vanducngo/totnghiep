package sample.com.myappsearch;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by root on 22/07/2015.
 */
public class DataSource {
    // Database fields
    private SQLiteDatabase database;


    public static final String TABLE_BRAND = "Brand";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_NAME = "Name";


    private DataBaseHelper dbHelper;
    private String[] allColumns = { COLUMN_ID, COLUMN_NAME};

    public DataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
        dbHelper.openDataBase();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public ArrayList<DataModel> getAllVocabulary() {
        ArrayList<DataModel> rListModel = new ArrayList<DataModel>();

        Cursor cursor = database.query(TABLE_BRAND,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataModel tVocabulary = cursorToModel(cursor);
            rListModel.add(tVocabulary);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return rListModel;
    }

    private DataModel cursorToModel(Cursor cursor) {
        DataModel rVocabulary = new DataModel();
        rVocabulary.setId(cursor.getLong(0));
        rVocabulary.setName(cursor.getString(1));
        return rVocabulary;
    }

    public DataModel getBrand(String sWord) {
        Cursor cursor = database.query(TABLE_BRAND,
                allColumns, COLUMN_NAME + " = '" + sWord
                        + "'", null, null, null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        DataModel newVocabulary = cursorToModel(cursor);
        cursor.close();
        return newVocabulary;
    }

    public DataModel getBrandById(int id) {
        Cursor cursor = database.query(TABLE_BRAND,
                allColumns, COLUMN_ID + " = " + id, null, null,
                null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        DataModel rVocabulary = cursorToModel(cursor);
        cursor.close();
        return rVocabulary;
    }
}
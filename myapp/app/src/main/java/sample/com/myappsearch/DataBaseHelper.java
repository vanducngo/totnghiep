package sample.com.myappsearch;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by root on 22/07/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_PATH = "/data/data/sample.com.myappsearch/databases/";
    // Database Name
    private static final String DATABASE_NAME = "LRA";

    public static final String TABLE_BRAND = "Brand";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_NAME = "Name";

    private String[] allColumns = { COLUMN_ID, COLUMN_NAME};

    Context ctx;
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }


//    // Getting single contact
//    public Contact Get_ContactDetails(String name) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_CONTACT, new String[] { KEY_ID,
//                        KEY_NAME, KEY_EMAILID, KEY_MOBILENO }, KEY_NAME + "=?",
//                new String[] { name }, null, null, null, null);
//        if (cursor != null && cursor.moveToFirst()){
//            Contact cont = new Contact(cursor.getString(1), cursor.getString(2), cursor.getString(3));
//            // return contact
//            cursor.close();
//            db.close();
//
//            return cont;
//
//        }
//        return null;
//    }

    public void CopyDataBaseFromAsset() throws IOException{
        InputStream in  = ctx.getAssets().open("LRA.sqlite");
        Log.e("sample", "Starting copying" );
        String outputFileName = DATABASE_PATH+DATABASE_NAME;
        File databaseFile = new File( "/data/data/sample.com.myappsearch/databases/");
        // check if databases folder exists, if not create one and its subfolders
        if (!databaseFile.exists()){
            databaseFile.mkdir();
        }

        OutputStream out = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;


        while ((length = in.read(buffer))>0){
            out.write(buffer,0,length);
        }
        Log.e("sample", "Completed");
        out.flush();
        out.close();
        in.close();

    }


    public void openDataBase () throws SQLException{
        String path = DATABASE_PATH+DATABASE_NAME;
        SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        try {
////            CopyDataBaseFromAsset();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public ArrayList<DataModel> searchProduct(String product) {
        ArrayList<DataModel> rListModel = new ArrayList<DataModel>();

        SQLiteDatabase database = this.getReadableDatabase();

//        Cursor cursor = database.query(TABLE_BRAND,
//                allColumns, null, null, null, null, null);
        String query = "SELECT a.Name, i.Url FROM AliasProduct as a join Product as p on a.ProductId = p.Id join Image as i on a.ProductId = i.Id join Brand as b on p.BrandId = b.Id where a.IsMain = 'True' and p.IsActive = 'True' and a.Name like '%"+ product+"%'";
        Cursor cursor = database.rawQuery(query,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataModel tVocabulary = cursorToModel(cursor);
            rListModel.add(tVocabulary);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        database.close();
        return rListModel;
    }

    private DataModel cursorToModel(Cursor cursor) {
        DataModel rVocabulary = new DataModel();
        rVocabulary.setName(cursor.getString(0));
        rVocabulary.setImageUrl(cursor.getString(1));
        return rVocabulary;
    }
}
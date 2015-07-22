package sample.com.myappsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        TextView tv = (TextView)view.findViewById(R.id.listview);

        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        try {
            dbHelper.CopyDataBaseFromAsset();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dbHelper.openDataBase();

        List<DataModel> list = dbHelper.searchProduct("a");
        dbHelper.close();
        if(list !=null){
            tv.setText(list.toArray().toString());
        }else{
            Toast.makeText(getActivity(), "Loi roi", Toast.LENGTH_LONG).show();
        }
        return view;
    }
}

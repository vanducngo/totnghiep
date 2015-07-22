package sample.com.myappsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ProductGridAdapter adapter;
    private GridView mGridView;
    ArrayList<DataModel> list;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        final EditText tv = (EditText)view.findViewById(R.id.search_field);

        mGridView = (GridView)view.findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductDetailsFragment tFragment = new ProductDetailsFragment();
                ((MainActivity)getActivity()).replaceFragment(tFragment, list.get(position).getId() ,list.get(position).getImageUrl());
            }
        });

        Button button = (Button)view.findViewById(R.id.button_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
                try {
                    dbHelper.CopyDataBaseFromAsset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dbHelper.openDataBase();

                list = dbHelper.searchProduct(tv.getText().toString());
                dbHelper.close();
                if (list != null && list.size()>0) {
                    adapter = new ProductGridAdapter(getActivity(),list);
                    mGridView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Loi roi", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}

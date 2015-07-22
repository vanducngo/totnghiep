package sample.com.myappsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 19/05/2015.
 */
public class CommentAdapter extends BaseAdapter {
    private ArrayList<String> mDataSet;
    private Context mContext;

    //Constructor
    public CommentAdapter(Context context, ArrayList<String> myDataSet) {
        mDataSet = myDataSet;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(mContext);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.comment_item_view, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.comment_tv);
            textView.setText(mDataSet.get(position));
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}

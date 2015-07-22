package sample.com.myappsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 19/05/2015.
 */
public class ProductGridAdapter extends BaseAdapter {
    private ArrayList<DataModel> mDataSet;
    private Context mContext;

    //Constructor
    public ProductGridAdapter(Context context, ArrayList<DataModel> myDataSet) {
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
        return mDataSet.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(mContext);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.product_item_view, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.item_name);
            textView.setText(mDataSet.get(position).getName());

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.item_image);

            Picasso.with(mContext).load(mDataSet.get(position).getImageUrl()).into(imageView);


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}

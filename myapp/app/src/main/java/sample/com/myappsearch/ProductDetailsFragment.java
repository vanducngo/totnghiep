package sample.com.myappsearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

@SuppressLint("SetJavaScriptEnabled")
public class ProductDetailsFragment extends Fragment {

    private ImageView mImage;
    private WebView details;
    private LinearLayout listComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rView = inflater.inflate(R.layout.product_details, parent,
                false);

        initView(rView);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return rView;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initView(View sView) {
        mImage = (ImageView)sView.findViewById(R.id.product_image);
        details = (WebView)sView.findViewById(R.id.product_details);
        listComment = (LinearLayout)sView.findViewById(R.id.comment_list);

        long roductId = ((MainActivity)getActivity()).getSelectedProductID();
        Picasso.with(getActivity()).load(((MainActivity)getActivity()).getImageUrl()).into(mImage);

        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        try {
            dbHelper.CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHelper.openDataBase();
        ArrayList<String> list = dbHelper.getComments(roductId);
        String description = dbHelper.getDescription(roductId);

        details.loadDataWithBaseURL(null, description, "text/html", "utf-8", null);

        dbHelper.close();
        if (list != null && list.size()>0) {
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            CommentAdapter adapter = new CommentAdapter(getActivity(), list);
            for(int i = 0; i < list.size();i++){
                View view = inflater.inflate(R.layout.comment_item_view,null);
                TextView v = (TextView)view.findViewById(R.id.comment_tv);
                v.setText(list.get(i));
                listComment.addView(view);
            }
        } else {
            Toast.makeText(getActivity(), "Loi roi", Toast.LENGTH_LONG).show();
        }

    }


}

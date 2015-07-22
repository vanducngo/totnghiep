package sample.com.myappsearch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    public long getSelectedProductID() {
        return selectedProductID;
    }

    private long selectedProductID;

    public String getImageUrl() {
        return imageUrl;
    }

    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new MainActivityFragment());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void replaceFragment(Fragment sFragment, long dataModel,String imageUrl) {
        selectedProductID  = dataModel;
        this.imageUrl = imageUrl;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null);

        //Replace current fragment by sFragment with custom animation.
        transaction
                .setCustomAnimations(R.anim.move_right_in,
                        R.anim.move_left_out, R.anim.move_right_out,
                        R.anim.move_left_in)
                .replace(R.id.main_fragment_container, sFragment).commit();
    }

    public void addFragment(Fragment sFragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null);
        transaction
                .add(R.id.main_fragment_container, sFragment).commit();
    }
}

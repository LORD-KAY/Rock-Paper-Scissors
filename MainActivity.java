package com.slam.rockpaperscissors;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements HomeFragment.onHomeFragment{

    private  HomeFragment homeFragment;
    private GameFragment gameFragment;

    private HelpfeedbackFragment helpfeedbackFragment;

    private String m_Text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Codes for setting up the back navigation bar on the application action bar
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                }else{
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });
        //using the onclick listener to activate the back navigation in the main activity
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     onBackPressed();

                                                 }
                                             });



        //Making the fragment transaction from the first activity fragment to the main activity
        if(savedInstanceState == null &&
                findViewById(R.id.fragmentContainer) != null){
            homeFragment = new HomeFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.view_show, R.anim.view_hide, R.anim.view_show, R.anim.view_hide);
            transaction.add(R.id.fragmentContainer,homeFragment, "homeFragment");
            transaction.commit();
        }
        else{
            homeFragment
                    = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_archived) {
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    //Checking to see the whether a particular pane is empty or not
    @Override
    public void onHomeFragmentListener() {
       if(findViewById(R.id.fragmentContainer) != null)
           displayonHomeFragment(R.id.fragmentContainer , null);
        else
           displayonHomeFragment(R.id.rightPaneContainer, null);
    }

    @Override
    public void onHelpFeedbackListener() {
        if(findViewById(R.id.fragmentContainer) != null)
            displayonHelpFragment(R.id.fragmentContainer, null);
        else
            displayonHelpFragment(R.id.rightPaneContainer, null);
    }

    private void displayonHelpFragment(int fragmentContainer, Object o) {

        helpfeedbackFragment = new HelpfeedbackFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.view_show, R.anim.view_hide, R.anim.view_show,R.anim.view_hide);
        transaction.replace(fragmentContainer, helpfeedbackFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void displayonHomeFragment(int fragmentContainer, Object o) {


        if(o != null){
            Bundle arguments = new Bundle();
        }

        gameFragment = new GameFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.view_show, R.anim.view_hide, R.anim.view_show,R.anim.view_hide);
        transaction.replace(fragmentContainer,gameFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }



}


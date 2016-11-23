package com.slam.rockpaperscissors;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.content.Context;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment{

    public Button playButton;

    public HomeFragment() {
    }


    public interface onHomeFragment{

        void onHomeFragmentListener();

        void onHelpFeedbackListener();


    }

    private onHomeFragment listener;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        playButton = (Button) view.findViewById(R.id.playButton);
        //setting click listener for the play button
       playButton.setOnClickListener(playButtonListener);

        return view;
    }

    public final View.OnClickListener playButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onHomeFragmentListener();
        }
    };


    public void onAttach(Context context){
        super.onAttach(context);
        listener = (onHomeFragment) context;
    }

    public void onDetach(){
        super.onDetach();
        listener = null;
    }


    public void onCreateOptionsMenu(Menu menu , MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_main, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_help:
                listener.onHelpFeedbackListener();
                return true;
            case R.id.action_archived:
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

}

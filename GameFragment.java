package com.slam.rockpaperscissors;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;
import android.support.design.widget.Snackbar;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by lord on 11/15/16.
 */

public class GameFragment extends Fragment {

    private FloatingActionButton FABSave;
    private TextInputLayout nameTextInputLayout;

    private TextView nameLabelTextView;

    private GridLayout gridView;

    public String input;

    public ImageView imageView;
    public TextView computerTextView;
    public ImageButton rock;
    public ImageButton paper;
    public ImageButton scissors;
    public ImageButton replayButton;
    public TextView scorelabel1;
    public TextView scorelabel;

    public final int min = 1;
    public final int max = 4;
    public int  compResults;

    public int compScore = 0;
    public int playerScore = 0;

    //Instantiating the media player method
    MediaPlayer mp;
    //Display for tie
    public String tie = "Its A Tie, Play Again";


    public final NumberFormat numberFormat = NumberFormat.getIntegerInstance();

    public interface  onGameFragment{
        void onGameFragmentListener();
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        super.onCreateView(inflater, container , savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_game, container ,false);

        FABSave = (FloatingActionButton) view.findViewById(R.id.fabSave);
        FABSave.setOnClickListener(saveButtonClickFAB);
        FABSave.setEnabled(false);

        nameLabelTextView = (TextView) view.findViewById(R.id.nameLabelTextView);

        gridView = (GridLayout) view.findViewById(R.id.gridView);
        gridView.setVisibility(GridLayout.GONE);

        nameTextInputLayout = (TextInputLayout) view.findViewById(R.id.nameTextInputLayout);
        nameTextInputLayout.getEditText().addTextChangedListener(nameChangeListener);

        //programmatically manipulating the textviews and the image buttons

        imageView = (ImageView) view.findViewById(R.id.imageView);

        rock = (ImageButton) view.findViewById(R.id.rock);
        rock.setOnClickListener(rockListener);
        paper = (ImageButton) view.findViewById(R.id.paper);
        paper.setOnClickListener(paperListener);
        scissors = (ImageButton)view.findViewById(R.id.scissors);
        scissors.setOnClickListener(scissorsListener);

        //Displaying the scores in the text view
        scorelabel1 = (TextView) view.findViewById(R.id.scorelabel1);
        scorelabel = (TextView) view.findViewById(R.id.labelScore);

        replayButton = (ImageButton) view.findViewById(R.id.replayButton);
        replayButton.setOnClickListener(replayButtonListener);

        return view;
    }


    public final TextWatcher nameChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           saveButtonFAB();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    public final View.OnClickListener saveButtonClickFAB = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if(view != null){
                InputMethodManager inputMethod = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethod.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            gridView.setVisibility(GridLayout.VISIBLE);

            nameTextInputLayout.setVisibility(nameTextInputLayout.GONE);
            FABSave.setVisibility(FABSave.GONE);
            //Hiding the Keyboard will be here
            nameLabelTextView.setText(input);
            //clearing the container or the input text box
            nameTextInputLayout.getEditText().setText("");
        }
    };

    private void saveButtonFAB(){
        input = nameTextInputLayout.getEditText().getText().toString();
        if(input.trim().length() != 0){
            FABSave.setEnabled(true);
            FABSave.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.button_colors)));

        }
        else{
            FABSave.setEnabled(false);
            FABSave.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disabled)));
        }
    }

    public void Compute(){
        Random r = new Random();
        compResults = r.nextInt(max - min) + min;
    }


    //codes for each button that will be clicked by the user
    //Codes for the Rock button
    public final View.OnClickListener rockListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Rock Code Representation of the Codes
            Compute();
            switch (compResults)
            {
                case 1:
                    imageView.setImageResource(R.drawable.rock);
                    Toast.makeText(getActivity(), getString(R.string.tie),Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.paper);
                    Toast.makeText(getActivity(), getString(R.string.compWins),Toast.LENGTH_SHORT).show();
                    compScore = compScore + 1;
                    scorelabel.setText(numberFormat.format(compScore));
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.scissors);
                    //Play Sound when Rock Smashes Scissors
                    mp = MediaPlayer.create(getActivity().getBaseContext(),R.raw.rock);
                    mp.start();
                    Toast.makeText(getActivity(), input + "Wins",Toast.LENGTH_SHORT).show();
                    playerScore = playerScore + 1;
                    scorelabel1.setText(numberFormat.format(playerScore));
                    break;
            }

        }

    };

    //Codes for the Paper button
    public final View.OnClickListener paperListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Paper Code Representation of the Codes
            Compute();
            switch (compResults)
            {
                case 1:
                    imageView.setImageResource(R.drawable.rock);
                    Toast.makeText(getActivity(),input + "Wins" , Toast.LENGTH_SHORT).show();
                    playerScore = playerScore + 1;
                    scorelabel1.setText(numberFormat.format(playerScore));
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.paper);
                    Toast.makeText(getActivity(),getString(R.string.tie), Toast.LENGTH_SHORT).show();

                    break;
                case 3:
                    imageView.setImageResource(R.drawable.scissors);
                    Toast.makeText(getActivity(), getString(R.string.compWins), Toast.LENGTH_SHORT).show();
                    compScore = compScore + 1;
                    scorelabel.setText(numberFormat.format(compScore));
                    break;
            }

        }
    };

    //Codes for the Scissors Button
    public final View.OnClickListener scissorsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Scissors  code Representation of the Codes
            Compute();
            switch (compResults)
            {
                case 1:
                    imageView.setImageResource(R.drawable.rock);
                    Toast.makeText(getActivity(), getString(R.string.compWins), Toast.LENGTH_SHORT).show();
                    compScore = compScore + 1;
                    scorelabel.setText(numberFormat.format(compScore));
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.paper);
                    Toast.makeText(getActivity(), input + "Wins",Toast.LENGTH_SHORT).show();
                    playerScore = playerScore + 1;
                    scorelabel1.setText(numberFormat.format(playerScore));
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.scissors);
                    Toast.makeText(getActivity(), getString(R.string.tie), Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    // Codes for the Replay Button
    public final View.OnClickListener replayButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           imageView.setImageResource(R.color.imageColor);
            playerScore = 0;
            compScore = 0;
            nameLabelTextView.setText("");
            scorelabel.setText(String.valueOf(playerScore));
            scorelabel1.setText(String.valueOf(compScore));
            nameTextInputLayout.setVisibility(nameTextInputLayout.VISIBLE);
            FABSave.setVisibility(FABSave.VISIBLE);
            gridView.setVisibility(GridLayout.GONE);
        }
    };
}

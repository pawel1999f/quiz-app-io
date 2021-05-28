package com.example.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class EndGameDialogFragment extends DialogFragment {
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout to use as dialog or embedded fragment
//        return inflater.inflate(container);
//    }

    private final String setName;
    private final int points;
    private final int maxPoints;

    public EndGameDialogFragment(String name, int pt, int mpt)
    {
        setName = name;
        points = pt;
        maxPoints = mpt;
    }

    // Wzorzec projektowy Budowniczego.
    // Ułatwia stworzenie okna dialogowego.

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use of the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Czy na pewno chcesz zakończyć?")
                .setPositiveButton("tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), EndScreenActivity.class);
                        intent.putExtra("POINTS", points);
                        intent.putExtra("MAX_POINTS", maxPoints);
                        intent.putExtra("SET_NAME", setName);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Returning the creation of the AlertDialog object
        return builder.create();
    }
}

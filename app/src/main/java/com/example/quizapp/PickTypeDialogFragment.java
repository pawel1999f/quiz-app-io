package com.example.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class PickTypeDialogFragment extends DialogFragment {
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout to use as dialog or embedded fragment
//        return inflater.inflate(container);
//    }

    String setName;
    Context cnt;

    public PickTypeDialogFragment(String name)
    {
        setName = name;
        //cnt = c;
    }

    // Wzorzec projektowy Budowniczego.
    // Ułatwia stworzenie okna dialogowego.

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Dialog dialog = super.onCreateDialog(savedInstanceState);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Use of the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Choose mode")
                .setPositiveButton("quickplay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), QuickplayActivity.class);
                        intent.putExtra("SET_NAME", setName);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("quiz", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), QuizActivity.class);
                        intent.putExtra("SET_NAME", setName);
                        startActivity(intent);
                    }
                });

        return builder.create();
    }


}

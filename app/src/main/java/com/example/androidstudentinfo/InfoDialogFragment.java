package com.example.androidstudentinfo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

public class InfoDialogFragment extends android.app.DialogFragment {

    static InfoDialogFragment newInstance(String title, String message) {
        InfoDialogFragment infoDialogFragment = new InfoDialogFragment();

        Bundle args = new Bundle();

        args.putString("title", title);
        args.putString("message", message);

        infoDialogFragment.setArguments(args);

        return infoDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title   = getArguments().getString("title");
        String message = getArguments().getString("message");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton("OK", null).create();
    }
}

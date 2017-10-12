package com.intdb.android.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.intdb.android.R;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public class DialogUtils {

    /**
     * Shows About
     * @param context
     * @param <V>
     */
    public static <V> void showAboutDialog(Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(R.string.lbl_about)
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
}

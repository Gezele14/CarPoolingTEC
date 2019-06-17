package com.XTEC.carpoolingtec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialogs {


    public  Dialogs(){

    }

    public void Alert(Context context, String Tittle, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(Tittle)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setMessage(Message);

        AlertDialog Alert = builder.create();
        Alert.show();
    }


}

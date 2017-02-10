package com.fknt.voltage.smartshoppinglist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.concurrent.Callable;

/**
 * Created by SD on 10.02.2017.
 */

public class ConfirmDialog {

    Callable<Object> callbackIfOk;
    Callable<Object> callbackIfCancel;
    String caption;
    Context context;

    public ConfirmDialog(Context context, String caption, Callable<Object> callbackIfOk, Callable<Object> callbackIfCancel) {
        this.context=context;
        this.caption=caption;
        this.callbackIfOk = callbackIfOk;
        this.callbackIfCancel = callbackIfCancel;
    }

    public ConfirmDialog(Context context, String caption,Callable<Object> callbackIfOk ) {

        this(context, caption, callbackIfOk, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return false;
            }
        });

    }

    public void Show(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this.context);
        builder.setTitle(this.caption);
//        final EditText input= new EditText(getContext());
//        input.setInputType(InputType.TYPE_CLASS_TEXT);
//        builder.setView(input);

        builder.setPositiveButton(R.string.btn_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            callbackIfOk.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    callbackIfCancel.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.cancel();
            }
        });

        builder.show();
    }
}

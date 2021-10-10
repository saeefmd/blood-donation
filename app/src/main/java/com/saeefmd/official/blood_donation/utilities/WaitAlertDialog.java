package com.saeefmd.official.blood_donation.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.saeefmd.official.blood_donation.R;

public class WaitAlertDialog extends Dialog {

    public WaitAlertDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.custom_dialog_wait);
    }
}

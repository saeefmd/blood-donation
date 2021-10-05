package com.saeefmd.official.blood_donation.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.saeefmd.official.blood_donation.R;

public class DatePickerDialog extends Dialog {

    private Context context;

    public DatePickerDialog(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_date_picker);
    }
}

package com.saeefmd.official.blood_donation.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.saeefmd.official.blood_donation.R;

public class DeveloperInfoDialog extends Dialog {

    private Context context;

    public DeveloperInfoDialog(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_developer_info);

        Button okBt = findViewById(R.id.developer_info_ok_bt);

        okBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

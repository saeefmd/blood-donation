package com.saeefmd.official.miublooddonors.Utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.saeefmd.official.miublooddonors.R;

public class MyInfoAlertDialog extends Dialog{

    public MyInfoAlertDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_my_info);

        Button updateBt = findViewById(R.id.my_info_update_bt);
        Button okBt = findViewById(R.id.my_info_ok_bt);

        okBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}

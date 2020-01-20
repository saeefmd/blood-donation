package com.saeefmd.official.miublooddonors.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.saeefmd.official.miublooddonors.R;

public class AboutDialog extends Dialog {

    private Context context;

    public AboutDialog(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_about);

        Button okBt = findViewById(R.id.about_ok_bt);

        okBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

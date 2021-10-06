package com.saeefmd.official.blood_donation.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import com.saeefmd.official.blood_donation.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerDialog extends Dialog {

    private Context context;
    private final OnDateSelected onDateSelected;

    public DatePickerDialog(Context context, OnDateSelected onDateSelected) {
        super(context);

        this.context = context;
        this.onDateSelected = onDateSelected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_date_picker);

        DatePicker datePicker = findViewById(R.id.donate_date_picker);
        Button saveBt = findViewById(R.id.date_picker_ok_bt);

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = getDate(datePicker);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(date);
                Log.d("Date: " , dateString);

                onDateSelected.onSaveClicked(dateString);
                dismiss();
            }
        });
    }

    private Date getDate(DatePicker picker) {
        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public interface OnDateSelected {
        void onSaveClicked(String date);
    }
}

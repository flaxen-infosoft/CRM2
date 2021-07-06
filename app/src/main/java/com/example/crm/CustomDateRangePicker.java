package com.example.crm;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;
import java.util.Date;

public class CustomDateRangePicker {

    Date st, en;

    public View init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.date_range_picker_dialog, null, false);
        DateRangeCalendarView dateRangeCalendarView = v.findViewById(R.id.calendar);
        Calendar current = Calendar.getInstance();
        dateRangeCalendarView.setCurrentMonth(current);
        dateRangeCalendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onFirstDateSelected(Calendar startDate) {
                st = startDate.getTime();
            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                st = startDate.getTime();
                en = endDate.getTime();
            }
        });

        return v;
    }

    public Date getStartDate() {
        return st;
    }

    public Date getEndDate() {
        return en;
    }
}

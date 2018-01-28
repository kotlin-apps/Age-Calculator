package com.skynetlabz.zainaftab.agecalculator

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : Activity() {
    private var date: Date? = null
    val cal = Calendar.getInstance()
    var sdf: SimpleDateFormat? = null

    var year = cal.get(Calendar.YEAR)
    var month = cal.get(Calendar.MONTH)
    var day = cal.get(Calendar.DAY_OF_MONTH)

    val today = cal.time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun calculate_age(V: View) {
        var year = Calendar.getInstance().get(Calendar.YEAR)
//        tv_result.setText("Your age is: " + (year - Integer.valueOf(et_date.text.toString())) + " years")
        val years = today.time - date!!.time
        val c = Calendar.getInstance();
        c.timeInMillis = years
        var month = c.get(Calendar.MONTH) - 1;
        if (month < 0)
            month=c.get(Calendar.MONTH);
        tv_result.setText("Your age is: \n"
                + (c.get(Calendar.YEAR) - 1970) + " years "
                + (month) + " months "
                + (c.get(Calendar.DAY_OF_MONTH)) + " days")
    }

    fun show_picker(v: View) {
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView();
            }
        }
        DatePickerDialog(this, dateSetListener, year, month, day).show()
    }


    private fun updateDateInView() {
        //date formatting
        val myFormat = "dd/MM/yyyy" // mention the format you need
        sdf = SimpleDateFormat(myFormat, Locale.US)
        date = cal.time
        year = cal.get(Calendar.YEAR)
        month = cal.get(Calendar.MONTH)
        day = cal.get(Calendar.DAY_OF_MONTH)
        Toast.makeText(this, "" + date!!.time + "\n" + today.time, Toast.LENGTH_LONG).show()
        et_date!!.setText(sdf!!.format(cal.getTime()))
    }
}

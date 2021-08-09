package com.khushk.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.khushk.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener {view->
            clickDatePicker(view)
        }
    }
    private fun clickDatePicker(view: View?) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

      val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedyear, selectedmonth, selecteddayOfMonth ->
            Toast.makeText(this,
                "The chosen year is $year, the month is $month and the day is $selecteddayOfMonth"
                , Toast.LENGTH_LONG).show()
            val selectedDate = "$selecteddayOfMonth/${selecteddayOfMonth+1}/$selectedyear"
             binding.tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time /60000
            val diffrenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            binding.tvSelectedDateInMinutes.setText(diffrenceInMinutes.toString())
        },
        year, month , day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }


}
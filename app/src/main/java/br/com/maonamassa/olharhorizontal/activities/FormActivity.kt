package br.com.maonamassa.olharhorizontal.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import br.com.maonamassa.olharhorizontal.R
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_form.*
import java.util.*

/**
 * Created by Aluno on 28/04/2018.
 */
class FormActivity: AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setupView()
    }

    fun setupView () {
        dataInput.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.setVersion(DatePickerDialog.Version.VERSION_1);
            dpd.show(fragmentManager, "Datepickerdialog")


        }

    }

    override fun onDateSet(p0: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val date = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
        dataInput.setText(date)
    }
}
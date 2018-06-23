package br.com.maonamassa.olharhorizontal.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.OngAdapter
import br.com.maonamassa.olharhorizontal.utils.ProjetosApi
import br.com.maonamassa.olharhorizontal.utils.RetrofitHelper
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_lista.*
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

        sendImage.setOnClickListener () {
            //Implementar funcionalidade

        }

        sendButton.setOnClickListener() {
            var ong=ONG ()

            val name= nameEditText.text.toString()
            ong.nome= name

            //val date=dataInput.text.toString()
            //ong.dataProjeto= date

            val descricption=descricaoEdit.text.toString()
            ong.descricao= descricption

            val time=textHour.text.toString()
            ong.horario= time

            val address= enderecoEd.text.toString ()
            ong.endereco= address

            //val image= sendImage.text.toString ()
            //ong.fotoUrl= image

            createProject(ong)


        }

        textHour.setOnClickListener() {
            val now = Calendar.getInstance()
            val dpd = TimePickerDialog.newInstance(object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(dialog: TimePickerDialog?, hora: Int, minuto: Int, segundo: Int) {
                    var textH= "$hora:$minuto"
                    textHour.setText(textH, TextView.BufferType.EDITABLE)
                   //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }, true )
            dpd.show(fragmentManager, "Datepickerdialog")
        }

    }

    override fun onDateSet(p0: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val date = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
        dataInput.setText(date)
    }

    fun createProject(ong: ONG) {
        val retrofit = RetrofitHelper.getRetrofit(useAuth = false)
        val apiInterface = retrofit.create(ProjetosApi::class.java)
        apiInterface?.createProject(ong)?.subscribeOn(Schedulers.io())?.subscribe({
                    //
                }, { error ->
            Log.e("Erro", "Deu ruim na API")
        }
        )
    }
}
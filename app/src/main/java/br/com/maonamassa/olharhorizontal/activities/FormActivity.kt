package br.com.maonamassa.olharhorizontal.activities

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.modelos.Organizacao
import br.com.maonamassa.olharhorizontal.utils.ProjetosApi
import br.com.maonamassa.olharhorizontal.utils.RetrofitHelper
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_form.*
import java.util.*

/**
 * Created by Aluno on 28/04/2018.
 */
class FormActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setupView()
    }

    fun setupView() {


        sendImage.setOnClickListener {
            Log.e("Camera", "comecando")
            val rxPermissions = RxPermissions(this)
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe { granted ->
                        if (granted) {
                            Log.e("Camera", "deu certo")
                        } else {
                            Log.e("Camera", "Deu errado")
                        }

                    }
        }



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


        sendButton.setOnClickListener() {
            var ong = ONG()

            val name = nameEditText.text.toString()
            ong.nome = name

            //val date=dataInput.text.toString()
            //ong.dataProjeto= date

            val descricption = descricaoEdit.text.toString()
            ong.descricao = descricption

//            val time = textHour.text.toString()
//            ong.horario = time

//            val address = enderecoEd.text.toString()
//            ong.endereco = address

            //val image= sendImage.text.toString ()
            //ong.fotoUrl= image

//            val organizacao = Organizacao()
//            organizacao.cnpj = "oi"
//            organizacao.dataNasc = "2018-02-03"
//            organizacao.email = "io"
//            organizacao.endereco = "io"
//            organizacao.entidade = "oi"
//            organizacao.fotoUrl = "oi"
//            organizacao.nome = "io"
//            ong.organizacao = organizacao

//            ong.dataProjeto = "2018-02-03"
//
//            ong.latitude = "-27.59"
//
//            ong.longitude = "-48.95"


            createProject(ong)

        }

        textHour.setOnClickListener() {
            val now = Calendar.getInstance()
            val dpd = TimePickerDialog.newInstance(object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(dialog: TimePickerDialog?, hora: Int, minuto: Int, segundo: Int) {
                    var textH = "$hora:$minuto"
                    textHour.setText(textH, TextView.BufferType.EDITABLE)
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }, true)
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
            Log.e("sucesso!!", "Objeto enviado com sucesso")
        }, { error ->
            Log.e("Erro", "Deu ruim na API")
        }
        )
    }
}
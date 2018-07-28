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
import android.content.Intent
import android.graphics.Bitmap
import android.location.Geocoder
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.android.plugins.RxAndroidPlugins
import kotlinx.android.synthetic.main.activity_form.mapa


/**
 * Created by Aluno on 28/04/2018.
 */
class FormActivity : AppCompatActivity(), OnMapReadyCallback, DatePickerDialog.OnDateSetListener, TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setupView()
        mapa.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mapa.getMapAsync(this)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }

    var map: GoogleMap? = null

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val geoCoder = Geocoder(this, Locale.getDefault())
        val adrresses = geoCoder.getFromLocationName(enderecoEd.text.toString(), 1)
        if (adrresses.isEmpty()) {
            return
        }
        val adrress = adrresses.first()
        val lat = adrress.latitude
        val lon = adrress.longitude
        val coord = LatLng(lat, lon)
        val marker = MarkerOptions().position(coord)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, 17f))
        map?.addMarker(marker)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap

//        val florianopolis = LatLng( 25.00, 32.00)
//        map?.setMinZoomPreference(12f)
//        map?.moveCamera(CameraUpdateFactory.newLatLng(florianopolis))
//
//        val markerOptions = MarkerOptions()
//        markerOptions.position(florianopolis)
//        markerOptions.title("Florianópolis").snippet("Nossa cidade!")
//        map?.addMarker(markerOptions)

    }

    override fun onStart() {
        super.onStart()
        mapa.onStart()
    }

    override fun onPause() {
        super.onPause()
        mapa.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapa.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapa.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapa.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapa.onLowMemory()
    }


    val CAMERA_PIC_REQUEST = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            val image = data.extras!!.get("data") as Bitmap
            val imageview = findViewById<ImageButton>(R.id.sendImage) as ImageButton //sets imageview as the bitmap
            imageview.setImageBitmap(image)
        }
    }

    fun openCamera() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CAMERA_PIC_REQUEST)

    }

    fun setupView() {


        sendImage.setOnClickListener {
            Log.e("Camera", "comecando")
            val rxPermissions = RxPermissions(this)
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe { granted ->
                        if (granted) {
                            openCamera()
                        } else {
                            Log.e("Camera", "Deu errado")
                        }

                    }
        }

        enderecoEd.addTextChangedListener(this)

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

            val date = dataInput.text.toString()
            ong.dataProjeto = date

            val descricption = descricaoEdit.text.toString()
            ong.descricao = descricption

            val time = textHour.text.toString()
            ong.horario = time

            val address = enderecoEd.text.toString()
            ong.endereco = address

            //val image= sendImage.text.toString ()
            //ong.fotoUrl= image

            val organizacao = Organizacao()
            organizacao.cnpj = "oi"
            organizacao.dataNasc = "2018-02-03"
            organizacao.email = "io"
            organizacao.endereco = "io"
            organizacao.entidade = "oi"
            organizacao.fotoUrl = "oi"
            organizacao.nome = "io"
            ong.organizacao = organizacao

            ong.dataProjeto = "2018-02-03"

            ong.latitude = "-27.59"

            ong.longitude = "-48.95"


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
            finish()
        }, { error ->
            Log.e("Erro", "Deu ruim na API")
            finish()
        }
        )
    }
}

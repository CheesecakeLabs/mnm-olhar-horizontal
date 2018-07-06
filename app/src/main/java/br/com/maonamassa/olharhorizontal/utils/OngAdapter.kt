package br.com.maonamassa.olharhorizontal.utils

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ong.view.*
import java.text.SimpleDateFormat

/**
 * Created by CELTA-8514 on 04/11/2017.
 */

class OngAdapter(var lista: List<ONG>, var listener: OngClickListener): RecyclerView.Adapter<OngViewHolder>() {

    override fun getItemCount(): Int {
      return lista.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OngViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ong, parent, false)
        return OngViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: OngViewHolder, position: Int) {
        val ong = lista[position]
        holder.ong = ong
    }

}

class OngViewHolder(itemView: View, var listener: OngClickListener): RecyclerView.ViewHolder(itemView) {

    var ong: ONG? = null
    set(ong) {
        field = ong
        ong ?: return

        itemView.nomeOng.text = ong.nome
//        itemView.dataOng.text = SimpleDateFormat("dd/MM/yyyy").format(ong.dataProjeto)
        itemView.descricaoOng.text = ong.descricao

        Picasso.with(itemView.context).load(ong.fotoUrl).into(itemView.ongImageView)

        itemView.setOnClickListener {
            listener.onOngClicked(ong)
        }
    }
}
interface OngClickListener{
    fun onOngClicked(ong: ONG)

}





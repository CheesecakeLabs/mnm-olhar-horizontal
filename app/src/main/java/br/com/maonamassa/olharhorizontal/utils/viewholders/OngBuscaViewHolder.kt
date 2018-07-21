package br.com.maonamassa.olharhorizontal.utils.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.listemers.OngItemListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ong_busca.view.*

class OngBuscaViewHolder(view: View, val listener: OngItemListener) : RecyclerView.ViewHolder(view) {

    var ong: ONG? = null

    init {
        itemView.setOnClickListener {
            listener.ongPressionada(ong)
        }
    }

    fun bind(ong: ONG) {
        this.ong = ong
        itemView.nomeOng.text = ong.nome
        Picasso.with(itemView.context).load(ong.fotoUrl).into(itemView.ongImageView)
    }

}
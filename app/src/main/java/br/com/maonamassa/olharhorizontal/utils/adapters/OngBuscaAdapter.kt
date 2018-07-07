package br.com.maonamassa.olharhorizontal.utils.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.listemers.OngItemListener
import br.com.maonamassa.olharhorizontal.utils.viewholders.OngBuscaViewHolder

class OngBuscaAdapter(val listener: OngItemListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itens: List<ONG> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ong_busca, parent, false)
        return OngBuscaViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? OngBuscaViewHolder)?.bind(itens[position])
    }
}
package com.example.uiexamples

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.Carrera


class RecyclerView_AdapterCarreras(private var items: ArrayList<Carrera>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Carrera>? = null

    lateinit var mcontext: Context

    class CarreraHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val carrerasListView = LayoutInflater.from(parent.context).inflate(R.layout.template_carrera, parent, false)
        val sch = CarreraHolder(carrerasListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.findViewById<TextView>(R.id.tv_codigo)?.text = item?.codigo
        holder.itemView.findViewById<TextView>(R.id.tv_nombre)?.text = item?.nombre
        holder.itemView.findViewById<TextView>(R.id.tv_titulo)?.text = item?.titulo


        holder.itemView.findViewById<Button>(R.id.btn_ver_cursos).setOnClickListener{

            val i = Intent(mcontext, CursosCarrera::class.java)
            i.putExtra("Carrera",item)
            mcontext.startActivity(i)
        }
        holder.itemView.setOnClickListener {
            // val intent = Intent(mcontext, MainActivity::class.java)
            // intent.putExtra("passselectedcountry", itemsList?.get(position))
            //  mcontext.startActivity(intent)
            Log.d("Selected:", itemsList?.get(position)?.nombre.toString())

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Carrera>()
                    for (row in items) {
                        if (row.nombre.toLowerCase().contains(charSearch.toLowerCase()) || row.codigo.toLowerCase().contains(charSearch.toLowerCase())  ) {
                            resultList.add(row)
                        }
                    }
                    itemsList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemsList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsList = results?.values as ArrayList<Carrera>
                notifyDataSetChanged()
            }

        }
    }
}
package com.example.uiexamples

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.Ciclo
import com.example.uiexamples.model.Curso
import com.example.uiexamples.model.Persona

class RecyclerView_AdapterCiclos(private var items: ArrayList<Ciclo>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Ciclo>? = null

    lateinit var mcontext: Context

    class CicloHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val ciclosListView = LayoutInflater.from(parent.context).inflate(R.layout.template_ciclos, parent, false)
        val sch = CicloHolder(ciclosListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        var estado = "Inactivo"
        if(item?.estado == true) {estado = "Activo"}
        holder.itemView.findViewById<TextView>(R.id.tv_ciclo)?.text = item?.annio + "-" + item?.numero
        holder.itemView.findViewById<TextView>(R.id.tv_fechaIni)?.text = item?.fechaInicio
        holder.itemView.findViewById<TextView>(R.id.tv_fechaFin)?.text = item?.fechaFin
        holder.itemView.findViewById<TextView>(R.id.tv_estado)?.text = estado

        holder.itemView.findViewById<Button>(R.id.btnCambiarEstado).setOnClickListener{
            if(item?.estado == true) {item?.estado = false}
            else item?.estado = true
            estado = "Inactivo"
            if(item?.estado == true) {estado = "Activo"}
            holder.itemView.findViewById<TextView>(R.id.tv_estado)?.text = estado
        }
        holder.itemView.setOnClickListener {
            // val intent = Intent(mcontext, MainActivity::class.java)
            // intent.putExtra("passselectedcountry", itemsList?.get(position))
            //  mcontext.startActivity(intent)
            Log.d("Selected:", itemsList?.get(position)?.annio.toString())

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Ciclo>()
                    for (row in items) {
                        if (row.annio.toLowerCase().contains(charSearch.toLowerCase())) {
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
                itemsList = results?.values as ArrayList<Ciclo>
                notifyDataSetChanged()
            }

        }
    }
}
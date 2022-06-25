package com.example.uiexamples

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.*

class RecyclerView_AdapterGrupos (private var items: ArrayList<Grupo>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Grupo>? = null

    lateinit var mcontext: Context

    class GrupoHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val gruposListView = LayoutInflater.from(parent.context).inflate(R.layout.template_grupo, parent, false)
        val sch = GrupoHolder(gruposListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }
    fun showDialog(title : String,Message : String){
        val builder = AlertDialog.Builder(mcontext)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)


        holder.itemView.findViewById<TextView>(R.id.tv_numero)?.text = item?.numero
        holder.itemView.findViewById<TextView>(R.id.tv_curso)?.text = item?.curso
        holder.itemView.findViewById<TextView>(R.id.tv_profesor)?.text = item?.profesor
        holder.itemView.findViewById<TextView>(R.id.tv_horario)?.text = item?.horario


        holder.itemView.findViewById<Button>(R.id.btn_ver_alumnos).setOnClickListener {


            if (item?.listaMatriculas!!.size == 0) {
                showDialog("Error", "No Data Found")
            }

            val buffer = StringBuffer()
            item?.listaMatriculas!!.forEach {
                buffer.append("Alumno :" + it.estudiante + "\n")
                buffer.append("Nota :" + it.nota + "\n\n")

            }
            showDialog("Data Listing", buffer.toString())

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Grupo>()
                    for (row in items) {
                        if (row.curso.toLowerCase().contains(charSearch.toLowerCase()) || row.numero.toLowerCase().contains(charSearch.toLowerCase()) ) {
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
                itemsList = results?.values as ArrayList<Grupo>
                notifyDataSetChanged()
            }

        }
    }
}
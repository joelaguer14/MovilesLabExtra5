package com.example.uiexamples

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.Matricula
import com.example.uiexamples.model.Matriculas

class RecyclerView_AdapterRegistroNotas(private var items: ArrayList<Matricula>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Matricula>? = null

    lateinit var mcontext: Context

    class MatriculaHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val matriculasListView = LayoutInflater.from(parent.context).inflate(R.layout.template_registro_notas, parent, false)
        val sch = MatriculaHolder(matriculasListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.findViewById<TextView>(R.id.tv_estudiante)?.text = item?.estudiante
        holder.itemView.findViewById<TextView>(R.id.tv_curso)?.text = item?.curso
        holder.itemView.findViewById<EditText>(R.id.et_nota)?.setText(item?.nota)


        holder.itemView.findViewById<Button>(R.id.btn_actualizaNota).setOnClickListener {
            var matriculas: Matriculas = Matriculas.instance
            matriculas.actualizaNota(item?.curso,item?.estudiante,holder.itemView.findViewById<EditText>(R.id.et_nota)?.text)
            Toast.makeText(mcontext, "Registro Actualizado",Toast.LENGTH_LONG).show();
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Matricula>()
                    for (row in items) {
                        if (row.estudiante.toLowerCase().contains(charSearch.toLowerCase()) || row.curso.toLowerCase().contains(charSearch.toLowerCase()) ) {
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
                itemsList = results?.values as ArrayList<Matricula>
                notifyDataSetChanged()
            }

        }
    }
}
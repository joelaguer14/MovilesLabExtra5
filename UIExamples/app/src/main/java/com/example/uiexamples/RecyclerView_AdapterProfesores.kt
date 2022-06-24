package com.example.uiexamples

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.Curso
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Profesor

class RecyclerView_AdapterProfesores(private var items: ArrayList<Profesor>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Profesor>? = null

    lateinit var mcontext: Context

    class PersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val courseListView = LayoutInflater.from(parent.context).inflate(R.layout.template_profesores, parent, false)
        val sch = PersonHolder(courseListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.findViewById<TextView>(R.id.tv_codigo)?.text = item?.cedula
        holder.itemView.findViewById<TextView>(R.id.tv_nombre)?.text = item?.nombre
        holder.itemView.findViewById<TextView>(R.id.tv_creditos)?.text = item?.telefono
        holder.itemView.findViewById<TextView>(R.id.tv_horas)?.text = item?.email


        holder.itemView.setOnClickListener {
            // val intent = Intent(mcontext, MainActivity::class.java)
            // intent.putExtra("passselectedcountry", itemsList?.get(position))
            //  mcontext.startActivity(intent)
            Log.d("Selected:", itemsList?.get(position)?.cedula.toString())

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Profesor>()
                    for (row in items) {
                        if (row.nombre.toLowerCase().contains(charSearch.toLowerCase())) {
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
                itemsList = results?.values as ArrayList<Profesor>
                notifyDataSetChanged()
            }

        }
    }
}
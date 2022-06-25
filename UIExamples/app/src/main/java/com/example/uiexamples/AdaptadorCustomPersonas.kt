package com.example.uiexamples

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.Persona
import kotlin.collections.ArrayList

class RecyclerView_Adapter(private var items: ArrayList<Persona>): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var itemsList: ArrayList<Persona>? = null

    lateinit var mcontext: Context

    class PersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val personListView = LayoutInflater.from(parent.context).inflate(R.layout.templatepersonas, parent, false)
        val sch = PersonHolder(personListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.itemView.findViewById<TextView>(R.id.tv_user)?.text = item?.user
        holder.itemView.findViewById<TextView>(R.id.tv_id)?.text = item?.id
        holder.itemView.findViewById<TextView>(R.id.tv_perfil)?.text = item?.profile
        holder.itemView.findViewById<TextView>(R.id.tv_matriculador)?.text = item?.matriculador.toString()

        holder.itemView.setOnClickListener {
           // val intent = Intent(mcontext, MainActivity::class.java)
           // intent.putExtra("passselectedcountry", itemsList?.get(position))
          //  mcontext.startActivity(intent)
            Log.d("Selected:", itemsList?.get(position)?.user.toString())

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Persona>()
                    for (row in items) {
                        if (row.user.toLowerCase().contains(charSearch.toLowerCase())) {
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
                itemsList = results?.values as ArrayList<Persona>
                notifyDataSetChanged()
            }

        }
    }
}


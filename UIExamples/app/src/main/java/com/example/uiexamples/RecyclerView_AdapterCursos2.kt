package com.example.uiexamples

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.Curso

class RecyclerView_AdapterCursos2(private var items: ArrayList<Curso>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemsList: ArrayList<Curso>? = null

    lateinit var mcontext: Context

    class PersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val courseListView = LayoutInflater.from(parent.context).inflate(R.layout.template_cursos, parent, false)
        val sch = PersonHolder(courseListView)
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
        holder.itemView.findViewById<TextView>(R.id.tv_creditos)?.text = item?.creditos
        holder.itemView.findViewById<TextView>(R.id.tv_horas)?.text = item?.horas


        holder.itemView.setOnClickListener {
            // val intent = Intent(mcontext, MainActivity::class.java)
            // intent.putExtra("passselectedcountry", itemsList?.get(position))
            //  mcontext.startActivity(intent)
            Log.d("Selected:", itemsList?.get(position)?.codigo.toString())

        }
    }





    }

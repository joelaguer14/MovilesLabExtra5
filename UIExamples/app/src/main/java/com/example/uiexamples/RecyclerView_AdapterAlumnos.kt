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
import com.example.uiexamples.model.Alumno
import com.example.uiexamples.model.Ciclo
import com.example.uiexamples.model.Ciclos
import com.example.uiexamples.model.Matriculas

class RecyclerView_AdapterAlumnos(private var items: ArrayList<Alumno>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Alumno>? = null

    lateinit var mcontext: Context

    class AlumnoHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val alumnosListView = LayoutInflater.from(parent.context).inflate(R.layout.template_alumnos, parent, false)
        val sch = AlumnoHolder(alumnosListView)
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

        holder.itemView.findViewById<TextView>(R.id.tv_cedula)?.text = item?.cedula
        holder.itemView.findViewById<TextView>(R.id.tv_nombre)?.text = item?.nombre
        holder.itemView.findViewById<TextView>(R.id.tv_telefono)?.text = item?.telefono
        holder.itemView.findViewById<TextView>(R.id.tv_email)?.text = item?.email
        holder.itemView.findViewById<TextView>(R.id.tv_FecNac)?.text = item?.fecNac
        holder.itemView.findViewById<TextView>(R.id.tv_carrera)?.text = item?.carrera?.nombre

        holder.itemView.findViewById<Button>(R.id.btn_ver_historial).setOnClickListener {
                var matriculas: Matriculas = Matriculas.instance
                var matriculasEstudiante = matriculas.getMatriculasByStudent(item!!.cedula)

                if (matriculasEstudiante.count() == 0) {
                    showDialog("Error", "No Data Found")
                }

                val buffer = StringBuffer()
                matriculasEstudiante.forEach {
                    buffer.append("Curso :" + it.curso + "\n")
                    buffer.append("Nota :" + it.nota + "\n\n")

                }
                showDialog("Data Listing", buffer.toString())
            holder.itemView.setOnClickListener {

                // val intent = Intent(mcontext, MainActivity::class.java)
                // intent.putExtra("passselectedcountry", itemsList?.get(position))
                //  mcontext.startActivity(intent)
                Log.d("Selected:", itemsList?.get(position)?.nombre.toString())

            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Alumno>()
                    for (row in items) {
                        if (row.nombre.toLowerCase().contains(charSearch.toLowerCase()) || row.cedula.toLowerCase().contains(charSearch.toLowerCase()) ) {
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
                itemsList = results?.values as ArrayList<Alumno>
                notifyDataSetChanged()
            }

        }
    }
}
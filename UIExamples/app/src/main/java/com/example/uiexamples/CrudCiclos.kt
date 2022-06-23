package com.example.uiexamples

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uiexamples.model.Ciclo
import com.example.uiexamples.model.Ciclos
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Personas
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

class CrudCiclos : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var ciclos: Ciclos = Ciclos.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador:RecyclerView_AdapterCiclos
    lateinit var ciclo: Ciclo
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_ciclos)




        val searchIcon = findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)


        val cancelIcon = findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)


        val textView = findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

        lista = findViewById(R.id.lista)
        lista.layoutManager = LinearLayoutManager(lista.context)
        lista.setHasFixedSize(true)

        findViewById<SearchView>(R.id.person_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return false
            }
        })

        getListOfCiclos()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(ciclos.getCiclos(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition

                if(direction == ItemTouchHelper.LEFT){
                    ciclo = Ciclo(ciclos.getCiclos()[position].annio, ciclos.getCiclos()[position].numero,ciclos.getCiclos()[position].fechaInicio ,ciclos.getCiclos()[position].fechaFin,
                        ciclos.getCiclos()[position].estado)

                    ciclos.deleteCiclo(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, ciclo.numero +"-"+ciclo.annio + "Se eliminó...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        ciclos.getCiclos().add(position, ciclo)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterCiclos(ciclos.getCiclos())
                    lista.adapter = adaptador
                }else{
                   // val bundle = Bundle()
                   // ciclo = Persona(personas.getPersonas()[position].user, personas.getPersonas()[position].password,personas.getPersonas()[position].nombre ,personas.getPersonas()[position].id,
                    //    personas.getPersonas()[position].profile,personas.getPersonas()[position].foto)


                   // val i = Intent(this@CrudPersonas, JobAppEditForm::class.java)
                   // i.putExtra("position", position)
                   // i.putExtra("Persona",persona)
                   // startActivity(i)




                    adaptador = RecyclerView_AdapterCiclos(ciclos.getCiclos())
                    lista.adapter = adaptador
                    //getListOfPersons()
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@CrudCiclos, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@CrudCiclos, R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(this@CrudCiclos, R.color.green))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        }



        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lista)



        val add: FloatingActionButton = findViewById(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, CreatePersonForm::class.java)

            startActivity(intent)
        }


    }

    private fun getListOfCiclos() {
        val NCiclos = ArrayList<Ciclo>()
        for (c in ciclos.getCiclos()) {
            NCiclos.add(c)
        }
        adaptador = RecyclerView_AdapterCiclos(NCiclos)
        lista.adapter = adaptador
    }
}
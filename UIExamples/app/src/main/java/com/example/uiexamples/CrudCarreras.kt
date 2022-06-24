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
import com.example.uiexamples.model.Carrera
import com.example.uiexamples.model.Carreras
import com.example.uiexamples.model.Ciclo
import com.example.uiexamples.model.Ciclos
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

class CrudCarreras : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var carreras: Carreras = Carreras.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador:RecyclerView_AdapterCarreras
    lateinit var carrera: Carrera
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_carreras)




        val searchIcon = findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)


        val cancelIcon = findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)


        val textView = findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

        lista = findViewById(R.id.lista)
        lista.layoutManager = LinearLayoutManager(lista.context)
        lista.setHasFixedSize(true)

        findViewById<SearchView>(R.id.carreras_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return false
            }
        })

        getListOfCarreras()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(carreras.getCarreras(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition

                if(direction == ItemTouchHelper.LEFT){
                    carrera = Carrera(carreras.getCarreras()[position].codigo, carreras.getCarreras()[position].nombre,carreras.getCarreras()[position].titulo)

                     carreras.deleteCarrera(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, carrera.codigo +"-"+carrera.nombre + "Se eliminó...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        carreras.getCarreras().add(position, carrera)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterCarreras(carreras.getCarreras())
                    lista.adapter = adaptador
                }else{
                    val bundle = Bundle()
                    carrera = Carrera(carreras.getCarreras()[position].codigo, carreras.getCarreras()[position].nombre,carreras.getCarreras()[position].titulo)


                    val i = Intent(this@CrudCarreras, EditCarreraForm::class.java)
                    i.putExtra("position", position)
                    i.putExtra("Carrera",carrera)
                    startActivity(i)
                    finish()





                    adaptador = RecyclerView_AdapterCarreras(carreras.getCarreras())
                    lista.adapter = adaptador
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@CrudCarreras, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@CrudCarreras, R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(this@CrudCarreras, R.color.green))
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
            val intent = Intent(this, CreateCarreraForm::class.java)

            startActivity(intent)
            finish()
        }


    }

    private fun getListOfCarreras() {
        val NCarreras = ArrayList<Carrera>()
        for (c in carreras.getCarreras()) {
            NCarreras.add(c)
        }
        adaptador = RecyclerView_AdapterCarreras(NCarreras)
        lista.adapter = adaptador
    }
}
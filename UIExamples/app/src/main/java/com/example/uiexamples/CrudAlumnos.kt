package com.example.uiexamples

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import android.content.Intent
import com.example.uiexamples.model.*
import kotlin.collections.ArrayList

class CrudAlumnos : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var alumnos: Alumnos = Alumnos.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador:RecyclerView_AdapterAlumnos
    lateinit var alumno: Alumno
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_alumnos)




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

        getListOfAlumnos()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(alumnos.getAlumnos(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition

                if(direction == ItemTouchHelper.LEFT){
                    alumno = Alumno(alumnos.getAlumnos()[position].cedula, alumnos.getAlumnos()[position].nombre,alumnos.getAlumnos()[position].telefono ,alumnos.getAlumnos()[position].email,
                        alumnos.getAlumnos()[position].fecNac,alumnos.getAlumnos()[position].carrera)

                    alumnos.deleteAlumno(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, alumno.nombre  + "Se eliminó...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        alumnos.getAlumnos().add(position, alumno)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterAlumnos(alumnos.getAlumnos())
                    lista.adapter = adaptador
                }else{
                    val bundle = Bundle()
                    alumno = Alumno(alumnos.getAlumnos()[position].cedula, alumnos.getAlumnos()[position].nombre,alumnos.getAlumnos()[position].telefono ,alumnos.getAlumnos()[position].email,
                        alumnos.getAlumnos()[position].fecNac,alumnos.getAlumnos()[position].carrera)


                    val i = Intent(this@CrudAlumnos, EditAlumnoForm::class.java)
                    i.putExtra("position", position)
                    i.putExtra("Alumno",alumno)
                    startActivity(i)
                    finish()

                    adaptador = RecyclerView_AdapterAlumnos(alumnos.getAlumnos())
                    lista.adapter = adaptador
                    //getListOfPersons()
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@CrudAlumnos, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@CrudAlumnos, R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(this@CrudAlumnos, R.color.green))
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
            val intent = Intent(this, CreateAlumnoForm::class.java)

            startActivity(intent)
            finish()
        }


    }

    private fun getListOfAlumnos() {
        val Nalumnos = ArrayList<Alumno>()
        for (c in alumnos.getAlumnos()) {
            Nalumnos.add(c)
        }
        adaptador = RecyclerView_AdapterAlumnos(Nalumnos)
        lista.adapter = adaptador
    }
}
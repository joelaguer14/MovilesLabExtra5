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
import com.example.uiexamples.model.Alumno
import com.example.uiexamples.model.Alumnos
import com.example.uiexamples.model.Grupo
import com.example.uiexamples.model.Grupos
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

class CrudGrupos : AppCompatActivity(){
    private lateinit var appBarConfiguration: AppBarConfiguration
    var grupos: Grupos = Grupos.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador:RecyclerView_AdapterGrupos
    lateinit var grupo: Grupo
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_grupos)




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

        getListOfGrupos()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(grupos.getGrupos(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition

                if(direction == ItemTouchHelper.LEFT){
                    grupo = Grupo(grupos.getGrupos()[position].numero, grupos.getGrupos()[position].profesor,grupos.getGrupos()[position].curso,grupos.getGrupos()[position].horario)

                    grupos.deleteGrupo(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, grupo.numero  + "Se eliminó...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        grupos.getGrupos().add(position, grupo)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterGrupos(grupos.getGrupos())
                    lista.adapter = adaptador
                }else{
                  /*  val bundle = Bundle()
                    grupo = Grupo(grupos.getGrupos()[position].numero, grupos.getGrupos()[position].profesor,grupos.getGrupos()[position].curso)


                    val i = Intent(this@CrudGrupos, EditAlumnoForm::class.java)
                    i.putExtra("position", position)
                    i.putExtra("Alumno",alumno)
                    startActivity(i)
                    finish()

                    adaptador = RecyclerView_AdapterAlumnos(alumnos.getAlumnos())
                    lista.adapter = adaptador
                    //getListOfPersons() */
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@CrudGrupos, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@CrudGrupos, R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(this@CrudGrupos, R.color.green))
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
            val intent = Intent(this, CreateGrupoForm::class.java)

            startActivity(intent)
            finish()
        }


    }

    private fun getListOfGrupos() {
        val Nalumnos = ArrayList<Grupo>()
        for (c in grupos.getGrupos()) {
            Nalumnos.add(c)
        }
        adaptador = RecyclerView_AdapterGrupos(Nalumnos)
        lista.adapter = adaptador
    }
}
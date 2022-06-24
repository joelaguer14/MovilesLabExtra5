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
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import android.content.Intent
import com.example.uiexamples.model.Curso
import com.example.uiexamples.model.Cursos
import com.example.uiexamples.model.Profesor
import com.example.uiexamples.model.Profesores
import kotlin.collections.ArrayList

class CrudAlumnos : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var profesores: Profesores = Profesores.instance

    lateinit var lista:RecyclerView
    lateinit var adaptador:RecyclerView_AdapterProfesores
    lateinit var profesor: Profesor
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_profesores)




        val searchIcon = findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)


        val cancelIcon = findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)


        val textView = findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

        lista = findViewById(R.id.listaProfessors)
        lista.layoutManager = LinearLayoutManager(lista.context)
        lista.setHasFixedSize(true)

        findViewById<SearchView>(R.id.professor_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return false
            }
        })

        getListOfProfesores()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                        val fromPosition: Int = viewHolder.adapterPosition
                        val toPosition: Int = target.adapterPosition

                        Collections.swap(profesores.getProfesores(), fromPosition, toPosition)

                        lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                        position = viewHolder.adapterPosition

                        if(direction == ItemTouchHelper.LEFT){
                            profesor = Profesor(profesores.getProfesores()[position].cedula, profesores.getProfesores()[position].nombre,
                                profesores.getProfesores()[position].telefono ,profesores.getProfesores()[position].email)

                            profesores.deleteProfesor(position)
                            lista.adapter?.notifyItemRemoved(position)

                            Snackbar.make(lista, profesor.nombre + "Se eliminó...", Snackbar.LENGTH_LONG).setAction("Undo") {
                                profesores.getProfesores().add(position, profesor)
                                lista.adapter?.notifyItemInserted(position)
                            }.show()
                            adaptador = RecyclerView_AdapterProfesores(profesores.getProfesores())
                            lista.adapter = adaptador
                        }else{
                            val bundle = Bundle()
                            profesor = Profesor(profesores.getProfesores()[position].cedula, profesores.getProfesores()[position].nombre,
                                profesores.getProfesores()[position].telefono ,profesores.getProfesores()[position].email)


                            val i = Intent(this@CrudAlumnos, EditProfesorForm::class.java)
                            i.putExtra("position", position)
                            i.putExtra("Profesor",profesor)
                            startActivity(i)




                            adaptador = RecyclerView_AdapterProfesores(profesores.getProfesores())
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



        val add: FloatingActionButton = findViewById(R.id.addProfessor)
        add.setOnClickListener {
            val intent = Intent(this, CreateProfesorForm::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun getListOfProfesores() {
        val Nprofesores = ArrayList<Profesor>()
        for (p in profesores.getProfesores()) {
            Nprofesores.add(p)
        }
        adaptador = RecyclerView_AdapterProfesores(Nprofesores)
        lista.adapter = adaptador
    }
}
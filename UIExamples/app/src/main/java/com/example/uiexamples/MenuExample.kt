package com.example.uiexamples


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.uiexamples.model.Persona
import com.example.uiexamples.ui.gallery.GalleryFragment
import com.example.uiexamples.ui.home.HomeFragment
import com.example.uiexamples.ui.slideshow.SlideshowFragment
import com.google.android.material.navigation.NavigationView


class MenuExample : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var homeFragment: HomeFragment
    private lateinit var jobAppFragment: JobApplicationFragment
    private lateinit var galleryFragment: GalleryFragment
    private lateinit var slideshowFragment: SlideshowFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_example)

        val bundle = intent.extras
        val l = bundle?.getSerializable("Login") as Persona


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_personas, R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_example, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                setToolbarTittle("Home")
                changeFragment(HomeFragment())
            }

            R.id.nav_personas -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona
                if (l.profile == "Administrador" || l.matriculador) {
                    val i = Intent(this, CrudPersonas::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Acces denied!!",
                        Toast.LENGTH_LONG).show();
                }

            }
            R.id.nav_profesores -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona
                if (l.profile == "Administrador" || l.matriculador) {
                    val i = Intent(this, CrudProfesores::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Acces denied!!",
                        Toast.LENGTH_LONG).show();
                }
            }
            R.id.nav_ciclos -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona
                if (l.profile == "Administrador" || l.matriculador) {
                    val i = Intent(this, CrudCiclos::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Acces denied!!",
                        Toast.LENGTH_LONG).show();
                }
            }
            R.id.nav_cursos -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona
                if (l.profile == "Administrador" || l.matriculador) {
                    val i = Intent(this, CrudCursos::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Acces denied!!",
                        Toast.LENGTH_LONG).show();
                }
            }
            R.id.nav_alumnos -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona
                if (l.profile == "Administrador" || l.matriculador || l.profile=="Alumno") {
                    val i = Intent(this, CrudAlumnos::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Acces denied!!",
                        Toast.LENGTH_LONG).show();
                }
            }
            R.id.nav_carreras -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona
                if (l.profile == "Administrador" || l.matriculador) {
                    val i = Intent(this, CrudCarreras::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Acces denied!!",
                        Toast.LENGTH_LONG).show();
                }

            }
            R.id.nav_ofertaAca -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona
                if (l.profile == "Administrador" || l.matriculador || l.profile=="Profesor") {
                    val i = Intent(this, CrudGrupos::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Acces denied!!",
                        Toast.LENGTH_LONG).show();
                }

            }
            R.id.nav_logout -> {
                val i = Intent(this, LoginExample::class.java)
                startActivity(i)
                finish()
            }
            R.id.nav_registroNotas -> {
                val bundle = intent.extras
                val l = bundle?.getSerializable("Login") as Persona

                if (l.profile == "Profesor") {
                    val i = Intent(this, RegistroNotas::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(
                        this, "Acces denied!!",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

        }
        return true
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.backStackEntryCount
        if (fragments == 1) {
            finish()
            return
        }
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setToolbarTittle(title: String) {
        supportActionBar?.title = title
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
    }

}
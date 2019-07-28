package com.example.examenapplication

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.Marker



class MapActivity() : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener, GoogleMap.OnPolygonClickListener, GoogleMap.OnPolylineClickListener{

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("map", "Poligono ${p0.toString()}")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("map", "Polilinea ${p0.toString()}")
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("map", "Me voy a empezar a mover")
    }

    override fun onCameraMove() {
        Log.i("map", "Me estoy moviendo")
    }

    override fun onCameraIdle() {
        Log.i("map", "Me quedé quieto")
    }



    private lateinit var mMap: GoogleMap
    private var tienePermisosLocalizacion=false

    override fun onCreate(savedInstanceState: Bundle?) {
        JugadorHTTP().obtenerTodos()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        solicitarPermisosLocalizacion()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        JugadorHTTP().obtenerTodos()
        mMap = googleMap

        establecerConfiguracionMapa(mMap)
        establecerListenerMovimientoMapa(mMap)
        BDD.jugador1.forEach{
            val jugador = LatLng(it.latitud.toDouble(), it.longitud.toDouble())
            val titulo= it.id.toString()
            anadirMarcador(jugador,titulo)
            val zoom =17f
            moverCamaraConZoom(jugador,zoom)
        }









        val poliLineaUno= googleMap
            .addPolyline(
                PolylineOptions().clickable(true).add(

                    LatLng(-0.210462, -78.493948),
                    LatLng(-0.208218, -78.490163),
                    LatLng(-0.208583, -78.488940),
                    LatLng(-0.209377, -78.490303)

                )
            )


        val poligonoUno= googleMap
            .addPolygon(
                PolygonOptions().clickable(true).add(
                    LatLng(-0.209431, -78.490078),
                    LatLng(-0.208734, -78.488951),
                    LatLng(-0.209431, -78.488286),
                    LatLng(-0.210085, -78.489745)
                )
            )
        poligonoUno.fillColor= -0xc771c4
    }

    fun establecerListenerMovimientoMapa(map:GoogleMap){
        with(map){
            setOnCameraIdleListener (this@MapActivity)
            setOnCameraMoveStartedListener (this@MapActivity)
            setOnCameraMoveListener (this@MapActivity)
            setOnPolygonClickListener (this@MapActivity)
            setOnPolylineClickListener (this@MapActivity)
        }
    }


    fun anadirMarcador(latLng: LatLng, title:String){
        mMap.addMarker(
            MarkerOptions().
                position(latLng)
                .title(title)
        )
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float=10f){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom))

    }
    fun irACrearEstudiante(jugadorSeleccionado:Jugador){
        val intentEditar = Intent(this, DetalleEquipo::class.java)


        intentEditar.putExtra("materia_pasar", jugadorSeleccionado)
        this.startActivity(intentEditar)
        this.finish()
    }

    fun establecerConfiguracionMapa(mapa:GoogleMap){
        val contexto = this.applicationContext
        with(mapa){

            val permisoFindLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermiso=permisoFindLocation==PackageManager.PERMISSION_GRANTED
            if(tienePermiso){
                mapa.isMyLocationEnabled=true
            }
            this.uiSettings.isZoomControlsEnabled=true
            uiSettings.isMyLocationButtonEnabled=true
            this.setOnMarkerClickListener { marker ->
                val id = marker.title.toInt()
                val jugadorSeleccionado = BDD.jugador1.filter{
                    it.id==id
                }
                val jugadoAMostrar = Jugador(
                    jugadorSeleccionado[0].id!!,
                    jugadorSeleccionado[0].numeroCamiseta,
                    jugadorSeleccionado[0].nombreCamiseta,
                    jugadorSeleccionado[0].nombreCompletoJugador,
                    jugadorSeleccionado[0].poderEspecialDos,
                    jugadorSeleccionado[0].fechaIngresoEquipo,
                    jugadorSeleccionado[0].goles,
                    jugadorSeleccionado[0].latitud,
                    jugadorSeleccionado[0].longitud



                )

                irACrearEstudiante(jugadoAMostrar)

                false
            }


        }

    }
    fun solicitarPermisosLocalizacion(){
        val permisoFindLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermiso=permisoFindLocation==PackageManager.PERMISSION_GRANTED
        if (tienePermiso){
            Log.i("mapa","Tiene Permisos de FINE_LOCATION")
            this.tienePermisosLocalizacion=true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
    }
    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }
}

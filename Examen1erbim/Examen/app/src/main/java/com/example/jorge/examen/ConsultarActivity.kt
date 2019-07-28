import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jorge.examen.EquipoFutbol
import com.example.jorge.examen.ListaEquipo
import com.example.jorge.examen.R

class ConsultarActivity(
    private val listaCanchas: ArrayList<EquipoFutbol>,
    private val contexto: ListaEquipo,
    private val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<ConsultarActivity.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idTextView: TextView
        var numeroTextview: TextView
        var descTextView: TextView

        var metrosTextView: TextView
        var precioTextView: TextView
        var eliminarBoton: Button


        init {
            idTextView = view.findViewById(R.id.txt_id_zap) as TextView
            numeroTextview = view.findViewById(R.id.txt_num_can) as TextView
            descTextView = view.findViewById(R.id.txt_desc_can) as TextView
            precioTextView = view.findViewById(R.id.txt_prec_can) as TextView
            metrosTextView = view.findViewById(R.id.txt_met_can) as TextView

            eliminarBoton = view.findViewById(R.id.btn_eli_zap) as Button

            val layout = view.findViewById(R.id.lay_cam_can) as LinearLayout
            layout.setOnClickListener {
                val cancha = crearCancha(
                    idTextView.text.toString().toInt(),
                    numeroTextview.text.toString().toInt(),
                    descTextView.text.toString(),
                    precioTextView.text.toString().toDouble(),
                    metrosTextView.text.toString().toDouble()

                )
                contexto.irActulizarCancha(cancha)
            }

            eliminarBoton.setOnClickListener {

                val cancha = crearCancha(
                    idTextView.text.toString().toInt(),
                    numeroTextview.text.toString().toInt(),
                    descTextView.text.toString(),
                    precioTextView.text.toString().toDouble(),
                    metrosTextView.text.toString().toDouble()

                )
                contexto.eliminarCancha(cancha)

            }

        }
    }


    //Esta función define el template que vamos a utilizar.
    // El template esta en la carpeta de recursos res/layout -> layout
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_cancha,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    //Devuelve el número de items de la lista
    override fun getItemCount(): Int {
        return listaCanchas.size
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val cancha: Cancha = listaCanchas[position]
        myViewHolder.idTextView.text = cancha.id.toString()
        myViewHolder.numeroTextview.text = cancha.numero.toString()
        myViewHolder.precioTextView.text = cancha.precio.toString()
        myViewHolder.descTextView.text = cancha.desc.toString()
        myViewHolder.metrosTextView.text = cancha.metros.toString()


    }

    fun crearCancha(
        id: Int,
        numero: Int,
        descripcion: String,
        precio: Double,
        metros: Double
    ): Cancha {
        val cancha = Cancha(
            null,
            null,
            id,
            numero,
            descripcion,
            precio,
            metros

        )
        return cancha
    }
}
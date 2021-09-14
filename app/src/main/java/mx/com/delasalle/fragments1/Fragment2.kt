package mx.com.delasalle.fragments1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import layout.ImageClass


class Fragment2 : Fragment(R.layout.fragment_2) {

    lateinit var imagen: ImageClass
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var xPrefrences: SharedPreferences
    lateinit var arrayCon: ArrayList<ImageClass>
    lateinit var Starcon: ImageView
    lateinit var Heartcon: ImageView
    var index2: Int = 0
    var Pref = "Prefs"
    var rImg = "Referene Imgs"
    var moshi = Moshi.Builder().build()


    override fun onResume() {
        super.onResume()
        xPrefrences=requireActivity().getSharedPreferences(Pref , Context.MODE_PRIVATE )
        arrayCon=requireArguments().getParcelableArrayList("ArrayList")!!
        index2=requireArguments().getInt("index")
        imagen = arrayCon.get(index2)
        views()
    }




    private fun views() {
        imageView = requireView().findViewById(R.id.imgvDetail)
        textView = requireView().findViewById(R.id.txtvDescription)
        Starcon= requireView().findViewById(R.id.Star)
        Heartcon=requireView().findViewById(R.id.Heart)

        if(imagen.Star){
            Starcon.setImageResource(R.drawable.ic_staron)
        }else{
            Starcon.setImageResource(R.drawable.ic_staroff)
        }

        if (imagen.Heart){
            Heartcon.setImageResource(R.drawable.ic_hearton)
        }else{
            Heartcon.setImageResource(R.drawable.ic_heartoff)
        }

        Starcon.setOnClickListener {
            imagen.Star=!imagen.Star
            arrayCon[index2].Star=imagen.Star
            if(imagen.Star){
                Starcon.setImageResource(R.drawable.ic_staron)
            }else{
                Starcon.setImageResource(R.drawable.ic_staroff)
            }
            val type = Types.newParameterizedType(List::class.java, ImageClass::class.java)
            xPrefrences.edit().putString(rImg, moshi.adapter<List<ImageClass>>(type).toJson(arrayCon)).commit()
        }

        Heartcon.setOnClickListener {
            imagen.Heart=!imagen.Heart
            arrayCon[index2].Heart=imagen.Heart
            if (imagen.Heart){
                Heartcon.setImageResource(R.drawable.ic_hearton)
                MediaPlayer.create(context,R.raw.sound1).start()
            }else{
                Heartcon.setImageResource(R.drawable.ic_heartoff)
            }
            val type = Types.newParameterizedType(List::class.java, ImageClass::class.java)
            xPrefrences.edit().putString(rImg, moshi.adapter<List<ImageClass>>(type).toJson(arrayCon)).commit()
        }




        imageView.setImageResource(imagen.Img)
        textView.text = imagen.text

        imageView.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.FrameLayout,Frgment3().apply {
                arguments=Bundle().apply {
                    putParcelable("Image",imagen)
                }
            }).addToBackStack(Frgment3().tag).commit()
        }

    }



}
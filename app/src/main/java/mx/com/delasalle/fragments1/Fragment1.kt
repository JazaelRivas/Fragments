package mx.com.delasalle.fragments1

import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import layout.ImageClass


class Fragment1 : Fragment(R.layout.fragment_1) {

    lateinit var arrayImages: ArrayList<ImageClass>
    lateinit var btnInfo: Button
    lateinit var btnNext: Button
    lateinit var btnBack: Button
    lateinit var img: ImageView
    lateinit var xPrefrences: SharedPreferences
    var index: Int = 0
    var Pref = "Prefs"
    var rImg = "Referene Imgs"
    var moshi = Moshi.Builder().build()


    private fun views() {
        btnInfo = requireView().findViewById(R.id.btnShowMore)
        btnBack = requireView().findViewById(R.id.btnBack)
        btnNext = requireView().findViewById(R.id.btnNext)
        img = requireView().findViewById(R.id.img)

        arrayImages=GetPreferences()
        img.setImageResource(arrayImages[index].Img)
    }

    fun GetPreferences(): ArrayList<ImageClass> {
        val type = newParameterizedType(java.util.ArrayList::class.java, ImageClass::class.java)
        return xPrefrences.getString(rImg, null)?.let {
            return@let try {
                moshi.adapter<java.util.ArrayList<ImageClass>>(type).fromJson(it)
            } catch (e: Exception) {
                ImageClass.Frags
            }
        } ?: ImageClass.Frags
    }

    override fun onResume() {
        super.onResume()
        xPrefrences=requireActivity().getSharedPreferences(Pref , Context.MODE_PRIVATE )
        views()
        btnBack.setOnClickListener {
            if ( index == 0 ) {
                Toast.makeText(context, "No se puede ir atras", Toast.LENGTH_LONG).show()
            } else {
                index--
            }
            mostrarImagen()
        }
        btnNext.setOnClickListener {
            if ( index == (arrayImages.size-1) ) {
                Toast.makeText(context, "No se puede ir adelante", Toast.LENGTH_LONG).show()
            } else {
                index++
            }
            mostrarImagen()
        }
        btnInfo.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.FrameLayout,Fragment2().apply {
                arguments=Bundle().apply {
                    putParcelableArrayList("ArrayList",arrayImages)
                    putInt("index",index)
                }
            }).addToBackStack(Fragment2().tag).commit()
        }




    }

    private fun mostrarImagen() {
        img.setImageResource(arrayImages[index].Img)
    }


}
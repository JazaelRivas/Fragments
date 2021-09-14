package mx.com.delasalle.fragments1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import layout.ImageClass

class Frgment3 : Fragment(R.layout.fragment_frgment3) {

    lateinit var img1: ImageClass
    lateinit var imageView: ImageView

    override fun onResume() {
        super.onResume()
        img1 = requireArguments().getParcelable("Image")!!

        view()
    }

    private fun view() {
        imageView = requireView().findViewById(R.id.imgvView)

        imageView.setImageResource(img1.Img)
    }

}
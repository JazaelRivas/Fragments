package layout

import android.os.Parcelable
import androidx.lifecycle.GeneratedAdapter
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import mx.com.delasalle.fragments1.R
@JsonClass(generateAdapter = true)
@Parcelize
class ImageClass(
    var text:String="",
    var Img: Int=1,
    var Star: Boolean=false,
    var Heart: Boolean=false,

) :Parcelable {
    companion object{
        var Frags: ArrayList<ImageClass> = arrayListOf(
            ImageClass(text="Mark2", Img = R.drawable.mk2 ),
            ImageClass(text="Rx78", Img = R.drawable.rx78 ),
            ImageClass(text="Gundam", Img = R.drawable.g_logo )

        )
    }
}
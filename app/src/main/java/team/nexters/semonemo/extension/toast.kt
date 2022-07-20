package team.nexters.semonemo.extension

import android.content.Context
import android.widget.Toast

internal fun toast(
    context: Context,
    message: String,
    length: Int = Toast.LENGTH_SHORT,
) = toastBuilder(
    context = context,
    message = message,
    length = length
)

private fun toastBuilder(
    context: Context,
    message: String,
    length: Int
){
    Toast.makeText(context,message,length).show()
}
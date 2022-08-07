package team.nexters.semonemo.extension

import android.app.Activity
import android.widget.Toast

fun Activity.basicExceptionHandler(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
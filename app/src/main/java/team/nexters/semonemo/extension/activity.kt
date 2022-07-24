package team.nexters.semonemo.extension

import android.app.Activity
import android.widget.Toast

fun Activity.basicExceptionHandler(exception: Throwable) =
    Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
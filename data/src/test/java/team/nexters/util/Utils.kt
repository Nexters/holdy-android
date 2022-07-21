package team.nexters.util


import com.google.gson.Gson
import team.nexters.data.moim.model.MoimResponse
import java.io.File

internal object Utils {
    fun readResourceString(fileName: String): String {
        val file = File(javaClass.classLoader?.getResource(fileName)?.file)
        return file.bufferedReader().use {
            val str = it.readText()
            it.close()
            str
        }
    }
}
fun String.jsonStringToModel() = Gson().fromJson(this, Array<MoimResponse>::class.java).asList()
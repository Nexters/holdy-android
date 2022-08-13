package team.nexters.data.base

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import team.nexters.data.datastore.DataStoreManager
import timber.log.Timber

class HeaderInterceptor(private val dataStoreManager: DataStoreManager) : Interceptor {

    init {
        Timber.d("tag1 init=====")
    }
    val session: String = runBlocking {
        dataStoreManager.session.first()
    }.also {
        Timber.d("tag1 headerInterceptor session $it")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.addHeader("Cookie", session)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
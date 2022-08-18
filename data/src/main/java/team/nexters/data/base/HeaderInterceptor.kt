package team.nexters.data.base

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import team.nexters.data.datastore.DataStoreManager
import timber.log.Timber

class HeaderInterceptor(private val dataStoreManager: DataStoreManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val requestBuilder = original.newBuilder()
        runBlocking {
            requestBuilder.addHeader("Cookie", dataStoreManager.session.first())
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
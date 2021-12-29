package changhwan.experiment.sopthomework

import android.app.Application
import changhwan.experiment.sopthomework.data.remote.api.SignInService
import changhwan.experiment.sopthomework.data.remote.api.SignUpService
import changhwan.experiment.sopthomework.di.HeaderInterceptor
import changhwan.experiment.sopthomework.ui.viewmodel.SignViewModel
import changhwan.experiment.sopthomework.util.PreferenceUtil
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application() {

    companion object{
        lateinit var  prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()

        //shared preferences
        prefs = PreferenceUtil(applicationContext)

        startKoin {
            androidContext(this@MainApplication)
            modules(soptNetworkModule,viewModelModule)
        }
    }
}

val soptNetworkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }
    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get())
            .baseUrl("https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/")
            .build()
    }

    single<SignUpService> {
        get<Retrofit>().create(SignUpService::class.java)
    }

    single<SignInService> {
        get<Retrofit>().create(SignInService::class.java)
    }
}

val viewModelModule = module {
    viewModel {
        SignViewModel(get(),get())
    }
}
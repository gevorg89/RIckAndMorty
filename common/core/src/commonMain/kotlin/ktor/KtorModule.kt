package ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.url
import io.ktor.util.logging.Logger
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val ktorModule = DI.Module("ktorModule") {
    bind<HttpClient>() with singleton {
        HttpClient(HttpEngineFactory().createEngine()) {
//            install(Logging) {
//                logger = Logger.SIMPLE
//                level = LogLevel.ALL
//            }

//            install(JsonFeature) {
//                serializer = KotlinxSerializer(json = instance())
//            }

            install(HttpTimeout) {
                connectTimeoutMillis = 15000
                requestTimeoutMillis = 15000
            }

            defaultRequest {
                //TODO
                url("")
            }
        }
    }
}
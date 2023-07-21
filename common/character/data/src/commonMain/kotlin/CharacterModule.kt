import ktor.KtorCharacterDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import sqldelight.KtorSqlDelightDataSource

val characterModule = DI.Module("characterModule") {

    bind<KtorCharacterDataSource>() with provider {
        KtorCharacterDataSource(instance())
    }

    bind<KtorSqlDelightDataSource>() with provider {
        KtorSqlDelightDataSource()
    }

    bind<CharacterRepository>() with singleton {
        CharacterRepositoryImpl(instance(), instance())
    }
}
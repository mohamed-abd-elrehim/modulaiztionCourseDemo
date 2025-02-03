import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.call.body
import kotlinx.serialization.json.Json
import com.example.hero_datasource.network.EndPoints
import com.example.hero_datasource.network.model.HeroDto
import com.example.hero_datasource.network.model.toHero
import com.example.hero_datasource.network.service.IHeroService
import com.example.hero_domain.Hero
import io.ktor.serialization.kotlinx.json.json
/*
n Ktor, the factory pattern is useful for creating and configuring HttpClient instances in a flexible and reusable way. By centralizing the configuration in a factory, you can manage different settings (e.g., plugins, engines) and keep your code maintainable and testable
* */
class HeroServiceImpl(
    private val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            }) // Use Kotlinx JSON serialization
        }
    }
) : IHeroService {
    override suspend fun getHerosStats(): List<Hero> {
        val response: List<HeroDto> = client.get(EndPoints.HERO_STATS).body()
        return response.map { it.toHero() }
    }
}

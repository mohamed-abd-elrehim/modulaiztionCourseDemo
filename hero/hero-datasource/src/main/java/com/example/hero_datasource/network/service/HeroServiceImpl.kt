import io.ktor.client.*

import io.ktor.client.request.get
import io.ktor.client.call.body
import com.example.hero_datasource.network.EndPoints
import com.example.hero_datasource.network.model.HeroDto
import com.example.hero_datasource.network.model.toHero
import com.example.hero_datasource.network.service.IHeroService
import com.example.hero_domain.Hero


/*
n Ktor, the factory pattern is useful for creating and configuring HttpClient instances in a flexible and reusable way. By centralizing the configuration in a factory, you can manage different settings (e.g., plugins, engines) and keep your code maintainable and testable
* */
class HeroServiceImpl(
    private val client: HttpClient ,

) : IHeroService {
    override suspend fun getHerosStats(): List<Hero> {
        val response: List<HeroDto> = client.get(EndPoints.HERO_STATS).body()

        return response.map { it.toHero() }
    }
}

package seg3x02.graphqlcontactlistapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class WebSecurityConfig  {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors()
        http.csrf().disable()
        http.authorizeRequests().anyRequest().permitAll()
        return http.build()
    }
}

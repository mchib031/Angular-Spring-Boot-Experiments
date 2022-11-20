package seg3502.calculatorapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {
  @Throws(Exception::class)
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.inMemoryAuthentication()
          .passwordEncoder(passwordEncoder())
          .withUser("user1")
          .password(passwordEncoder().encode("pass1"))
          .roles("USER")
          .and()
          .withUser("user2")
          .password(passwordEncoder().encode("pass2"))
          .roles("USER")
  }

  @Throws(java.lang.Exception::class)
  override fun configure(http: HttpSecurity) {
    http.csrf().disable().authorizeRequests().
      antMatchers("/calculator/add/**").hasAnyRole("USER").
      antMatchers("/calculator/subtract/**").hasAnyRole("USER").
      antMatchers("/calculator/multiply/**").hasAnyRole("USER").
      antMatchers("/calculator/divide/**").hasAnyRole("USER").
    and().httpBasic().
    and().requiresChannel().anyRequest()
  }

  @Bean
  fun passwordEncoder(): BCryptPasswordEncoder {
    return BCryptPasswordEncoder()
  }
}
package hu.flowacademy.timetablemanager.config;

import hu.flowacademy.timetablemanager.service.authentication.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.header.HeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUDS;
    private HeaderWriter cacheControl;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                    .and()
                .csrf()
                    .disable()
                .headers()
                    .cacheControl().disable()
                    .and()
                .authorizeRequests()
                .antMatchers("/", "/login", "/registration", "/createUser", "/addactiveuser")
                    .permitAll()
                .antMatchers("/admin/**")
                    .hasAnyAuthority("ADMIN")
                .antMatchers("/**")
                    .hasAnyAuthority("USER", "ADMIN")
                .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .loginProcessingUrl("/login")
                    .successHandler(new CustomAuthenticationSuccessHandler(customUDS))
                    .failureHandler(new CustomAuthenticationFailureHandler())
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler());
    }

    private AuthenticationEntryPoint unauthorizedHandler() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUDS).passwordEncoder(passwordEncoder);
    }

    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

}

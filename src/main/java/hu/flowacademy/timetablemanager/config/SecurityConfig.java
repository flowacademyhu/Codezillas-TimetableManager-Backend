package hu.flowacademy.timetablemanager.config;

import hu.flowacademy.timetablemanager.service.CustomUserDetailsService;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.exceptionHandling().defaultAuthenticationEntryPointFor(getRestAuthEntryPoint(), new AntPathRequestMatcher("**"));
        httpSecurity
                .cors().and()
                .csrf().disable()
//                .authenticationProvider(new CustomAuthenticationProvider())
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/", "/login", "/registration", "/createUser").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .httpBasic();
    }

    private AuthenticationEntryPoint getRestAuthEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

//    @Component
//    public class CustomAuthenticationProvider implements AuthenticationProvider {
//        @Override
//        public Authentication authenticate(Authentication auth)
//                throws AuthenticationException {
//            String username = auth.getName();
//            String password = passwordEncoder.encode(auth.getCredentials().toString());
//            if ("externaluser".equals(username) && "pass".equals(password)) {
//                return new UsernamePasswordAuthenticationToken
//                        (username, password, Collections.emptyList());
//            } else {
//                throw new
//                        BadCredentialsException("External system authentication failed");
//            }
//        }
//
//        @Override
//        public boolean supports(Class<?> auth) {
//            return (UsernamePasswordAuthenticationToken.class
//                    .isAssignableFrom(auth));
//        }
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
}

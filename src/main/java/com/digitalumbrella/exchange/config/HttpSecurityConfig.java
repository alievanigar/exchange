
package com.digitalumbrella.exchange.config;

        import com.digitalumbrella.exchange.filters.JwtRequestFilter;
        import org.springframework.core.annotation.Order;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.crypto.password.NoOpPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {


    @Order(1)
    @Configuration
    class HttpBasicAuthenticationAdapter extends WebSecurityConfigurerAdapter{

            @Value("${auth.username}")
        private String username;

        @Value("${auth.password}")
        private String password;


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth)
                throws Exception
        {
            auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
                    .withUser(username).
                    password(password)
                    .roles("USER");
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable().antMatcher("/getInfoExchange/**")
                    .authorizeRequests().anyRequest().hasRole("USER")
                    //.antMatchers("/getInfoExchange/**").hasRole("USER")
                    .and()
                    .httpBasic();
        }
    }
    @Order(2)
    @Configuration
    //@EnableWebSecurity

    class HttpTokenAuthenticationAdapter extends WebSecurityConfigurerAdapter {
        @Value("${auth.username}")
        private String username;

        @Value("${auth.password}")
        private String password;

        @Autowired
        private JwtRequestFilter jwtRequestFilter;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth)
                throws Exception {
            auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
                    .withUser(username).
                    password(password)
                    .roles("USER");
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable()
                    .authorizeRequests().antMatchers("/authenticate").permitAll().
                    anyRequest().authenticated().and().
                    exceptionHandling().and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic().disable();
            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        }
    }
}
//}

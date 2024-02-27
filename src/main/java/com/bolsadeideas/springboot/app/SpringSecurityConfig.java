package com.bolsadeideas.springboot.app;

import com.bolsadeideas.springboot.app.auth.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/list/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/viewBills/**").hasAnyRole("USER")
                .antMatchers("/viewDetailsBill/**").hasAnyRole("USER")
                .antMatchers("/uploads/**").hasAnyRole("USER")
                .antMatchers("/form/**").hasAnyRole("ADMIN")
                .antMatchers("/eliminate/**").hasAnyRole("ADMIN")
                .antMatchers("/bill/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .successHandler(successHandler)
                        .loginPage("/login")
                    .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");
    }

    //Create method
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
        PasswordEncoder encoder = passwordEncoder();
        User.UserBuilder users = User.builder().passwordEncoder(encoder::encode); //Is the same return encoder.encode(password);

        builder.inMemoryAuthentication().withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
                .withUser(users.username("andrea").password("000000").roles("USER"));
    }

}

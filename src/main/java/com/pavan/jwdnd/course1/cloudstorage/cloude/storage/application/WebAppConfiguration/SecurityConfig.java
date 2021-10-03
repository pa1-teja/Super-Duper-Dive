package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebAppConfiguration;

import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(this.authenticationService).eraseCredentials(false);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/signup","/css/**","/js/**")
                .permitAll()
                .anyRequest().authenticated();


        http.formLogin().loginPage("/login").permitAll();

        http.formLogin().defaultSuccessUrl("/home?tabOption=files",true);

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();

    }
}

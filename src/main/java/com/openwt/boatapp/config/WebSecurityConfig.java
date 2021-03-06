package com.openwt.boatapp.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            .and()
                .httpBasic()
                .authenticationEntryPoint(noPopupBasicAuthenticationEntryPoint());
        // These lines are needed to use h2 console with Spring security
        //  .headers().frameOptions().sameOrigin();
        //@formatter:on
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //@formatter:off
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}password")
                .roles("USER")
            .and()
                .withUser("user2")
                .password("{noop}password")
                .roles("USER");
        //@formatter:on
    }

    /**
     * Override default Basic Auth behavior by not sending "WWW-Authentcate" header
     * which would make browser prompt for credentials
     */
    public class NoPopupBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException authException) throws IOException {
            // response.addHeader("WWW-Authenticate", "Basic realm=\"" + this.realmName +
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }

    @Bean
    public NoPopupBasicAuthenticationEntryPoint noPopupBasicAuthenticationEntryPoint() {
        NoPopupBasicAuthenticationEntryPoint entryPoint = new NoPopupBasicAuthenticationEntryPoint();
        entryPoint.setRealmName("BoatApp");
        return entryPoint;
    }
}

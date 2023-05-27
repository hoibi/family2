package com.nhnacademy.family.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/chu/delete/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/chu/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER", "ROLE_USER")
//                .requestMatchers("/project/**").authenticated()
//                .requestMatchers("/redirect-index").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                // TODO : #4 실습 - clientRegistrationRepository와 authorizedClientService를 설정해주세요.
                .clientRegistrationRepository(clientRegistrationRepository())
                .authorizedClientService(authorizedClientService())
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionFixation()
                .none()
                .and()
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .authorities("ROLE_ADMIN")
                .build();

        UserDetails member = User.withUsername("member")
                .password("{noop}member")
                .authorities("ROLE_MEMBER")
                .build();

//        UserDetails guest = User.withUsername("user")
//                .password("{noop}user")
//                .authorities("ROLE_USER")
//                .build();

        return new InMemoryUserDetailsManager(admin, member);
//        return new InMemoryUserDetailsManager(admin, member, guest);
    }


    // ToDo github
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        // TODO : #2 실습 - ClientRegistrationRepository 구현체를 생성하세요.
        //        아래 github() 메서드를 활용하세요.

        return new InMemoryClientRegistrationRepository(github());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        // TODO : #3 실습 - OAuth2AuthorizedClientService 구현체를 생성하세요.
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    private ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
//                .userNameAttributeName("name")
                // TODO #1: github에서 생성한 어플리케이션 정보를 참조해서 client_id와 client_secret을 등록하세요.
                .clientId("382cf5600d3cede615d3")
                .clientSecret("d35c4fb14f0f48f855ec1c7e9fd0886b230b0d52")
                .build();
    }

}

package com.example.UserLoginRegistration.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    DataSource dataSource;
    PasswordEncoder passwordEncoder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize)-> authorize.requestMatchers("/h2-console/**").permitAll().
                anyRequest().authenticated()).httpBasic(withDefaults()).formLogin(withDefaults()).csrf(AbstractHttpConfigurer::disable);
                return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
       daoAuthenticationProvider.setUserDetailsService(jdbcUserDetailsManager(dataSource,passwordEncoder));
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public EmbeddedDatabase datasource(){

        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("dashboard").addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource,PasswordEncoder passwordEncoder){
        UserDetails admin= User.builder().username("admin").password(passwordEncoder.encode("password")).roles("ADMIN").build();
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(admin);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//   public InMemoryUserDetailsManager users(){
//        return new InMemoryUserDetailsManager(User.withUsername("amitdhakal").password("12345").roles("USERS").build());
//    }
//

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
//        return jdbcUserDetailsManager;
//    }
//

//
//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        UserDetails randomUser= User
//                .withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USERS")
//                .build();
//        return new InMemoryUserDetailsManager(randomUser);
//    }


}


//todo
//spring security
//basic authentication and authorization
//security opensource projects watch
//Autentication Filter filter all the api request coming first layer and creates objects of Request coming
//Authentication Manager Authentication function is present in the manager it manages authentication
//Authentication Provider
//1.Password encrypt
//2.Userdetails manager
//security



// 1 security filter chain
// 2 authentication  manager
// 3 authentication provider
//4 user details manager
//5 password encoder
//
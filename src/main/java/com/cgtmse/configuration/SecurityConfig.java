package com.cgtmse.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cgtmse.service.UserDetailsServiceImpl;
import com.cgtmse.service.UserService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsServiceImpl);
//		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
    
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception{
		try{
			http
			.csrf( csrf -> csrf.disable())
			.cors( cors -> cors.disable())
//			.authorizeRequests( auth -> auth.requestMatchers("/", "/home", "/registration").permitAll())
//             // Allow everyone to access these pages
//            .anyRequest().authenticated()
//            .and()
			.authorizeHttpRequests( auth -> auth.requestMatchers("/register", "/login", "/home", "/hybrid/firstEncryption", "/hybrid/hybridEncryption" , "/save/saveData", "/hybrid/firstDecryption", "/hybrid/hybridDecryption" , "/redirectToActivity", "/save/saveData", "/home*" ,"/uploadFile", "/decryptUploadFile" ,"/registration*" , "/save/showTable" ,"/login**","/css/**", "/js/**", "/yourActivity" ).permitAll()
			                                    .anyRequest().authenticated())
			                                   // .and())                                   
//			.formLogin( form -> form.loginPage("/login")
//					                .permitAll())
					             //   .defaultSuccessUrl("/home.html", true))
			                        //.and())
			.logout( logout -> logout.invalidateHttpSession(true)
			                         .clearAuthentication(true)
			                         .logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
			                         .logoutSuccessUrl("/login?logout")
			                         .permitAll());
			
			//http.addFilterBefore( UsernamePasswordAuthenticationFilter.class);
		}catch (Exception e){
			System.out.println( e);
		}
		return http.build();
	}

}

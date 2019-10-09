package br.com.fcamara.asnpark.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource ds;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws  Exception {
		
		  auth
		  	.jdbcAuthentication() 
		  	.dataSource(ds)
		  	.authoritiesByUsernameQuery(rolesQuery)		 
		    .usersByUsernameQuery(usersQuery)
		    .passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

	// Secure the endpoins with HTTP Basic authentication
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		
		//HTTP Basic authentication 
		http.authorizeRequests()
	        .antMatchers("/h2-console/**")
	        .permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .formLogin();	       
	      http.csrf().ignoringAntMatchers("/h2-console/**");
	      http.headers().frameOptions().sameOrigin();
				
		  http 
		     .httpBasic()
		     .authenticationEntryPoint(authEntryPoint)
		     .and()
		     .authorizeRequests()
		     .antMatchers(HttpMethod.GET, "/estabelecimentos").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.POST, "/estabelecimento").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.PUT, "/estabelecimento/**").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.DELETE, "/estabelecimento/**").hasRole("ADMIN") 
		     .antMatchers(HttpMethod.GET, "/veiculos").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.POST, "/veiculo").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.PUT, "/veiculo/**").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.DELETE, "/veiculo/**").hasRole("ADMIN")
		     .antMatchers(HttpMethod.GET, "/entradaSaidaVeiculos").hasAnyRole("USER", "ADMIN")    
		     .antMatchers(HttpMethod.POST, "/entradaSaidaVeiculo").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.PUT, "/entradaSaidaVeiculo/**").hasAnyRole("USER", "ADMIN")
		     .antMatchers(HttpMethod.DELETE, "/entradaSaidaVeiculo/**").hasRole("ADMIN")
		     .and().csrf().disable()
		     .formLogin().disable();
	}
}

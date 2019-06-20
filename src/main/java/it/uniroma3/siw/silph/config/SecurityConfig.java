package it.uniroma3.siw.silph.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private Environment environment;

	private DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http
		.authorizeRequests().antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority("ADMIN")
		.anyRequest().permitAll()
		.and().formLogin()
		.defaultSuccessUrl("/admin/welcome")
		.and().logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/");*/
		http///.requiresChannel()
	     // .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
	      //.requiresSecure()
	     //.and()
		.authorizeRequests()
		.antMatchers("/admin").hasAnyAuthority("ADMIN")
		.antMatchers("/admin/welcome").hasAnyAuthority("ADMIN")
		.antMatchers("/admin/mostraFotografo").hasAnyAuthority("ADMIN")
		.antMatchers("/admin/formSaveFotografo").hasAnyAuthority("ADMIN")
		.antMatchers("/admin/formSaveFoto").hasAnyAuthority("ADMIN")
		.antMatchers("/admin/formSaveAlbum").hasAnyAuthority("ADMIN")
		.antMatchers("/admin/mostraAlbum").hasAnyAuthority("ADMIN")
		.anyRequest().permitAll()
		.and().logout()
		.and().formLogin()
		.defaultSuccessUrl("/admin/welcome")
		.and().logout()
		.logoutSuccessUrl("/")
		.permitAll();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("chiara").password(encoder.encode("chpass")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("silvia").password(encoder.encode("sipass")).roles("ADMIN");

    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder photo) throws Exception {
		photo.jdbcAuthentication().dataSource(this.buildDatasource())
		.authoritiesByUsernameQuery("SELECT username, role FROM funzionari WHERE username=?")
		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM funzionari WHERE username=?");   
	}

	@Bean
	DataSource buildDatasource() {
		// TODO Auto-generated method stub
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;	
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

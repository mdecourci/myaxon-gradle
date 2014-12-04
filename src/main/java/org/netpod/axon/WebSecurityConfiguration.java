package org.netpod.axon;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().anonymous().anyRequest().authenticated();
//		http.formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
//          .loginPage("/login")
//          .permitAll()
//          .and()
//          .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//          .permitAll();
  
  //		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**")
//				.permitAll().anyRequest().authenticated();
//		http.csrf().disable().formLogin().defaultSuccessUrl("/index.html")
//				.loginPage("/login.html").failureUrl("/login.html?error")
//				.permitAll().and().logout()
//				.logoutSuccessUrl("/login.html?logout").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
//		auth.inMemoryAuthentication().withUser("albert").password("1234")
//				.roles("USER").and().withUser("foo").password("bar")
//				.roles("USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		 web
         .ignoring()
            .antMatchers("/**");
//            .antMatchers("/resources/**");
	}
}

package org.netpod.configuration;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;


@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Inject private UserDetailsService userDetailsService;
	
	@Bean
	public PreAuthenticatedAuthenticationProvider preAuthenticatedProvider() {
		PreAuthenticatedAuthenticationProvider preAuthenticatedProvider = new PreAuthenticatedAuthenticationProvider();
		try {
			UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = 
					new UserDetailsByNameServiceWrapper<>(userDetailsService);
			
			preAuthenticatedProvider.setPreAuthenticatedUserDetailsService(wrapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preAuthenticatedProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public RequestHeaderAuthenticationFilter webSsoFilter() {
		RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
		filter.setPrincipalRequestHeader("SSO_GUID");
		try {
			filter.setAuthenticationManager(super.authenticationManagerBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filter;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(preAuthenticatedProvider()).authorizeRequests().anyRequest().hasAnyRole("DRM_Legal");
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
	protected void configure(AuthenticationManagerBuilder authManagerBuilder)
			throws Exception {
		authManagerBuilder.authenticationProvider(preAuthenticatedProvider()).userDetailsService(userDetailsService);
//		authManagerBuilder.inMemoryAuthentication().withUser("albert").password("1234")
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

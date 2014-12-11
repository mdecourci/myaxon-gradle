package com.ubs.drm.configuration.security.service;

import java.util.Arrays;

import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ubs.drm.configuration.security.User;

@Named("userDetailsService")
public class UserServiceLdapProxy implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = new User(username, Arrays.asList("DRM_Legal"));
		return user;
	}

}

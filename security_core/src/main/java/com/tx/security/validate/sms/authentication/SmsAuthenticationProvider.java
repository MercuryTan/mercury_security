package com.tx.security.validate.sms.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author tx
 * @description
 * @param  * @Param: null
 * @return
 **/
public class SmsAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken)authentication;
		String mobile = (String)smsAuthenticationToken.getPrincipal();
		UserDetails user = this.getUserDetailsService().loadUserByUsername(mobile);
		if(user == null){
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}

		SmsAuthenticationToken newSmsAuthenticationToken = new SmsAuthenticationToken(user,user.getAuthorities());
		newSmsAuthenticationToken.setDetails(authentication.getDetails());
		return newSmsAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (SmsAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}

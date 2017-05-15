package com.hk.pm.accounts.security;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.hk.pm.accounts.config.TokenConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Vineeth Kiv
 * Class to add and check authentication token
 *
 */
@Component
public class TokenAuthenticationService {

	private static TokenConfig tokenConfig;

	@Autowired
	public TokenAuthenticationService(TokenConfig tokenConfig) {
		this.tokenConfig = tokenConfig;
	}

	private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

	static void addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + tokenConfig.getExpirationTime()))
				.signWith(SignatureAlgorithm.HS512, tokenConfig.getSecretKey()).compact();
		res.addHeader(tokenConfig.getHeaderString(), tokenConfig.getTokenPrefix() + " " + JWT);
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(tokenConfig.getHeaderString());
		logger.info("Request Token: " + token);

		if (token != null) {
			String user = Jwts.parser().setSigningKey(tokenConfig.getSecretKey())
					.parseClaimsJws(token.replace(tokenConfig.getTokenPrefix(), "")).getBody().getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		return null;
	}

}

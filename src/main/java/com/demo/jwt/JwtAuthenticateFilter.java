package com.demo.jwt;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthenticateFilter extends OncePerRequestFilter{
	
	String secret="1234423198765312";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header=request.getHeader("Authorization");
		System.out.println("header..........."+header);
		if(header==null) {
			filterChain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticate=getAuthenticate(request);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		filterChain.doFilter(request, response);
		
	}
	
	public UsernamePasswordAuthenticationToken getAuthenticate(HttpServletRequest request) {
		String header=request.getHeader("Authorization");
		String token=header.replace("Bearer ", "");
		System.out.println("token..........."+token);
		if(Objects.nonNull(token)) {
			Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			String username=claims.getSubject();
			if(Objects.nonNull(username)) {
				return  new UsernamePasswordAuthenticationToken(username,null,null);
			}else {
				return null;
			}
		}
		return null;
	}

}

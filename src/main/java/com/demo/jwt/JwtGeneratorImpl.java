package com.demo.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface{

	 private String secret="1234423198765312";
	
	@Override
	public Map<String,Object> generateToken(UserDetails user) {
		String jwtToken="";
		Map<String,Object> claims=new HashMap<>();
		claims.put("name", user.getUsername());
		jwtToken=Jwts.builder().setClaims(claims).setSubject(user.getUsername()).
				setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() +(1*60*60*1000)))
		.signWith(SignatureAlgorithm.HS256, secret).compact();
		Map<String, Object> jwtTokenGen = new HashMap<>();
	    jwtTokenGen.put("token", jwtToken);
	    jwtTokenGen.put("userDetails", user);
	    return jwtTokenGen;
	}

}

package com.mhp.bootcamp.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 *
 *
 *
 *
 * JSON Web Token (JWT)
 * {
 *   "sub": "ADMIN",
 *   "name": "Alexaner Sturm",
 *   "authorities": [
 *       {"authority": "READ"},
 *       {"authority": "WRITE"}
 *
 *   ],
 *   "exp": "434344343"
 * }
 */
public class JWTAuthenticationService {

    public static void addAuthentication(final HttpServletResponse res, String name, final Collection<? extends GrantedAuthority> authorities) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(name)
                .claim("authorities", authorities);

        String jwt = jwtBuilder.signWith(SignatureAlgorithm.HS512, "Schnitzel").compact();
        res.addHeader("Authorization", "Bearer " + jwt);
    }

    public static Authentication getAuthentication(final HttpServletRequest request) {
        final String token = request.getHeader("Authorization");
        Authentication authentication = null;
        if (null != token) {
            Claims body = Jwts.parser()
                    .setSigningKey("Schnitzel")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            String userName = body.getSubject();
            Collection<LinkedHashMap<String, Object>> auths = (Collection<LinkedHashMap<String, Object>>) body.get("authorities");


            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            for (LinkedHashMap<String, Object> map : auths) {
                String role = map.get("authority").toString();
                authorities.add(new SimpleGrantedAuthority(role));
            }

            authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);
        }

        return authentication;
    }

}

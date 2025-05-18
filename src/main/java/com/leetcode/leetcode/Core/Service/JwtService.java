package com.leetcode.leetcode.Core.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.leetcode.leetcode.Core.Pojo.Coder;
import com.leetcode.leetcode.Core.Repositry.CoderRepositry;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

    private  SecretKey secretKey;
    @Lazy
    private  CoderRepositry  coderRepositry;

    public JwtService(CoderRepositry  coderRepositry) {
        this.coderRepositry  =  coderRepositry;
        SecretKey_Genrate();
    }
    private  void  SecretKey_Genrate() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            secretKey  =  keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            // hey  thos is  the  dev  Erroe  yu  hvae  to  handle  thos  
            e.printStackTrace();
        }
       
    }

    public  String  Genrate_JWT(String  user_email,String  user_id) {
            Map<String,Object>  map  =  new  HashMap<>();
            String  jti  =  UUID.randomUUID().toString();
            Coder  coder  =  coderRepositry.findByEmail(user_email).get();
            coder.setJwt_id(jti);
            coderRepositry.save(coder);
            log.info(jti);
            return  Jwts.builder()
                    .id(jti)
                    .issuedAt(new  Date(System.currentTimeMillis()))
                    .subject(user_email)
                    .claim("user_id", user_id)
                    .expiration(new Date(System.currentTimeMillis() + (2L * 60 * 60 * 1000)))
                    .claims(map)
                    .signWith(secretKey).compact();
            
    }
    public String  getUserId(String token) {
        final  Claims  claims  =  Jwts
                                        .parser()
                                        .verifyWith(secretKey)
                                        .build()
                                        .parseSignedClaims(token)
                                        .getPayload();
      //  log.info(claims.getSubject()+"   "+ claims.getExpiration());
        return claims.getSubject();
    }

    public boolean validate(UserDetails userDetails, String token) {
        // TODO Auto-generated method stub
        String  token_userId  = getUserId(token);
        return  ((userDetails.getUsername().equals(token_userId)) &&   !isTokenExpired(token) &&  isIdMatched(token));  
    }
    private  boolean  isTokenExpired(String  token) {
        final  Claims  claims  =  Jwts
                                    .parser()
                                    .verifyWith(secretKey)
                                    .build()
                                    .parseSignedClaims(token)
                                    .getPayload();
      //  log.info(claims.getSubject()+"   "+ claims.getExpiration());
        return  claims.getExpiration().before(new  Date());

    }

    private  boolean  isIdMatched(String  token){
        Coder  coder   =  coderRepositry.findByEmail(getUserId(token)).get();
        String  jwt_id  =  coder.getJwt_id();
        final  Claims  claims   =  Jwts.
                                    parser()
                                    .verifyWith(secretKey)
                                    .build()
                                    .parseSignedClaims(token)
                                    .getPayload();
        String  token_id  =  claims.getId();
        return  jwt_id.trim().equals(token_id.trim());
    }

    public  boolean  Invalidate_token(String  token) {
        try  {
            String  user_id  =  getUserId(token);
            Coder coder   =  coderRepositry.findByEmail(user_id).get();
            coder.setJwt_id("");;
            coderRepositry.save(coder);
            return  true;
        }
        catch(Exception  e) {
            return  false;
        }
        
    }
}

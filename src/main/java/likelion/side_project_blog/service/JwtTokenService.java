package likelion.side_project_blog.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private long expiration;

    /* 토큰 생성 */
    public String createToken(String userId){

        Date now=new Date();
        Date expireTime=new Date(now.getTime()+expiration);

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();

    }


    /* 토큰 파싱해서 유저ID 겟 */
    public String getUserId(String token){
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /* 유효한 토큰인지 검증 */
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /* 헤더 토큰 추출 */
    public String resolveToken(HttpServletRequest request){
        String bearerToken=request.getHeader("Authorization");
        if(bearerToken!=null&&bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }else return null;
    }
}

package Vendas.security;


import Vendas.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;


    public String gerarToken(Usuario usuario) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        // informacoes dos tokens = claims
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("emauildousuario", "usuario@gmail.com");
        claims.put("roles", "admin");

        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    public Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();

    }

    public Claims obterClams(String token) throws ExpiredJwtException {

        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClams(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
                    dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e) {

            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClams(token).getSubject();

    }
}

//    public static void main (String[] args) {
//        ConfigurableApplicationContext contexto = SpringApplication.run(application.class);
//        JwtService service = contexto.getBean(JwtService.class);
//        Usuario usuario = Usuario.builder().login("fulano").build();
//        String token = service.gerarToken(usuario);
//        System.out.println(token);
//    }
//
//}


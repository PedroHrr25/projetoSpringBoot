package Vendas.rest.controller;


import Vendas.domain.entity.Usuario;
import Vendas.exception.SenhaInvalidaException;
import Vendas.rest.dto.CredenciasDTO;
import Vendas.rest.dto.TokenDTO;
import Vendas.security.JwtService;
import Vendas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar (@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return  usuarioService.salvar(usuario);
    }

@PostMapping("/auth")
public TokenDTO autenticar (@RequestBody CredenciasDTO credencias) {
    try {
        Usuario usuario = Usuario.builder()
                .login(credencias.getLogin())
                .senha(credencias.getSenha()).build();
        UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
        String token = jwtService.gerarToken(usuario);
        return new TokenDTO(usuario.getLogin(), token);
    } catch (UsernameNotFoundException | SenhaInvalidaException e) {
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}}

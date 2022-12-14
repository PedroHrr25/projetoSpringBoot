package Vendas.service.impl;


import Vendas.domain.entity.Usuario;
import Vendas.domain.repository.UsuarioRepository;
import Vendas.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {


    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

@Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

public UserDetails autenticar (Usuario usuario){
    UserDetails user = loadUserByUsername(usuario.getLogin());
    boolean senhabatem  = encoder.matches(usuario.getSenha(), user.getPassword());
    if (senhabatem){
        return user;
    }
    throw new SenhaInvalidaException();
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado na base de dados"));

//validação para saber se o usuario é admin ou não
      String[] roles = usuario.isAdmin() ? new String[] {"ADMIN" , "USER"} : new String[] {"USER"};

      return User
              .builder()
              .username(usuario.getLogin())
              .password(usuario.getSenha())
              .roles()
              .build();
}}

package ru.levelup.junior.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.repositories.ClientsRepository;

import java.util.ArrayList;

/**
 * Created by otherz on 05.12.2019.
 */

@Component
public class ClientDetailsService implements UserDetailsService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client found = clientsRepository.findByLogin(username);
        if (found == null) {
            throw new UsernameNotFoundException("Client " + username + " not found.");
        }

        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (found.isAdmin()) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(username, found.getEncryptedPassword(), roles);
    }
}

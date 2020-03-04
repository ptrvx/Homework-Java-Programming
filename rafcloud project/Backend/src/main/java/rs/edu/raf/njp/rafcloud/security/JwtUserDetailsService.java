package rs.edu.raf.njp.rafcloud.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.edu.raf.njp.rafcloud.domain.entity.Role;
import rs.edu.raf.njp.rafcloud.domain.entity.User;
import rs.edu.raf.njp.rafcloud.domain.entity.UserEntity;
import rs.edu.raf.njp.rafcloud.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User " + s + " not found.");
        }

        return new User(user.getUsername(), user.getPassword(), Role.ROLE_USER);
    }

}

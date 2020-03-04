package rs.edu.raf.njp.rafcloud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.njp.rafcloud.domain.entity.Role;
import rs.edu.raf.njp.rafcloud.domain.entity.User;
import rs.edu.raf.njp.rafcloud.domain.entity.UserEntity;
import rs.edu.raf.njp.rafcloud.security.JwtTokenUtil;
import rs.edu.raf.njp.rafcloud.security.JwtUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil tokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @GetMapping("/logged")
    public User getLoggedInUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        Role role = Role.valueOf(a.getAuthorities().iterator().next().getAuthority());
        if (a.getPrincipal() instanceof String) {
            return new User(a.getPrincipal().toString(), "", role);
        } else {
            return (User)a.getPrincipal();
        }
    }

    @GetMapping("/logout")
    public User logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return null;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = tokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new User(userDetails.getUsername(), "", Role.ROLE_USER, token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}

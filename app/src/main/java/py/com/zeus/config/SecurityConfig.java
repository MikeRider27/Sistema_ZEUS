package py.com.zeus.config;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import py.com.zeus.db.ConectarDB;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(a -> a
                .requestMatchers("/login", "/css/**", "/js/**", "/img/**", "/actuator/health/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(f -> f
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureHandler((req, res, ex) -> {
                    req.getSession().setAttribute("loginError", ex.getMessage());
                    res.sendRedirect(req.getContextPath() + "/login?error");
                })
                .permitAll())
            .logout(l -> l.logoutUrl("/logout").logoutSuccessUrl("/login?salir").permitAll())
            // las ventanas internas son iframes del mismo origen
            .headers(h -> h.frameOptions(fo -> fo.sameOrigin()));
        return http.build();
    }

    /**
     * Autenticacion contra fn_login() de la BD (bcrypt). Tras 3 intentos fallidos se bloquea
     * el usuario 1 minuto (el legacy cerraba la aplicacion con System.exit).
     */
    @Bean
    AuthenticationProvider zeusAuthenticationProvider(ConectarDB db) {
        Map<String, Intentos> intentos = new ConcurrentHashMap<>();
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication auth) {
                String user = auth.getName() == null ? "" : auth.getName().trim().toUpperCase();
                String pass = auth.getCredentials() == null ? "" : auth.getCredentials().toString();
                if (user.isEmpty() || pass.isEmpty()) {
                    throw new BadCredentialsException("Debe completar todos los Campos");
                }
                Intentos i = intentos.get(user);
                if (i != null && i.bloqueadoHasta != null && Instant.now().isBefore(i.bloqueadoHasta)) {
                    throw new LockedException("Demasiados intentos fallidos. Espere un minuto e intente de nuevo");
                }
                Map<String, Object> r = db.one("SELECT * FROM fn_login(?, ?)", user, pass);
                if (r == null) {
                    Intentos n = intentos.merge(user, new Intentos(1, null),
                            (a, b) -> new Intentos(a.fallos + 1, null));
                    if (n.fallos >= 3) {
                        intentos.put(user, new Intentos(0, Instant.now().plusSeconds(60)));
                    }
                    throw new BadCredentialsException("El Usuario y/o la Contraseña son incorrectos o no existen");
                }
                intentos.remove(user);
                if ("ANU".equals(r.get("est_usr")) || "ANU".equals(r.get("est_emp"))) {
                    throw new DisabledException("El Usuario con el que intenta ingresar se encuentra Anulado");
                }
                Usuario u = new Usuario(r);
                return new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> c) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(c);
            }
        };
    }

    private record Intentos(int fallos, Instant bloqueadoHasta) {}

}

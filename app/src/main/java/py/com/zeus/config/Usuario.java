package py.com.zeus.config;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Datos de la sesion. Reemplaza a los static del legacy
 * (Login.user, Login.empCi, Login.emp, Login.suc, Login.sucCod, Login.per).
 */
public class Usuario implements UserDetails, Serializable {

    private final int usrCod;
    private final String user;
    private final int empCi;
    private final String emp;
    private final int sucCod;
    private final String suc;
    private final String cargo;
    private final int per; // nivel: 1 admin, 2 gerente, 3 encargado, 4 operador

    public Usuario(Map<String, Object> r) {
        this.usrCod = ((Number) r.get("usr_cod")).intValue();
        this.user = (String) r.get("usr_des");
        this.empCi = ((Number) r.get("emp_ci")).intValue();
        this.emp = (String) r.get("emp");
        this.sucCod = ((Number) r.get("suc_cod")).intValue();
        this.suc = (String) r.get("suc_des");
        this.cargo = (String) r.get("car_des");
        this.per = ((Number) r.get("car_niv")).intValue();
    }

    public int getUsrCod() { return usrCod; }
    public String getUser() { return user; }
    public int getEmpCi() { return empCi; }
    public String getEmp() { return emp; }
    public int getSucCod() { return sucCod; }
    public String getSuc() { return suc; }
    public String getCargo() { return cargo; }
    public int getPer() { return per; }

    public boolean isAdmin() { return per == 1; }
    /** puede aprobar pedidos/presupuestos (en el legacy: Login.per < 4) */
    public boolean isAprobador() { return per <= 3; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("NIV_" + per));
    }

    @Override public String getPassword() { return null; }
    @Override public String getUsername() { return user; }
}

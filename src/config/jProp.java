
package config;

import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class jProp {

  public final Properties getProperties() {
        try {
            Properties props = new Properties();
            props.load( getClass().getResourceAsStream("config.properties") );
            if (!props.isEmpty()) {                
                return props;
            } else {
                return null;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "Error al Obtener Config", JOptionPane.ERROR_MESSAGE);
            return null;
        }
  }
}
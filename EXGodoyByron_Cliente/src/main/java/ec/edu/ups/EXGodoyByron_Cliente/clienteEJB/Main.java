package ec.edu.ups.EXGodoyByron_Cliente.clienteEJB;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.edu.ups.EXGodoyByron_Servidor.bisiness.UniversidadRemoto;
import ec.edu.ups.EXGodoyByron_Servidor.modelo.Universidad;

public class Main {

	private UniversidadRemoto universidadRemoto;
	
public void intanciarRegistro() throws NamingException{
		
		try {  
		final Hashtable<String, Comparable> jndiProperties= new Hashtable<String, Comparable>();
		
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		//jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejb");  
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "byron0968718441.");
     
        
        
        final Context context = new InitialContext(jndiProperties);
        
        
        final String lookupName = "ejb:/EXGodoyByron_Servidor/UniversidadON!ec.edu.ups.EXGodoyByron_Servidor.bisiness.UniversidadRemoto";  
        
        this.universidadRemoto = (UniversidadRemoto) context.lookup(lookupName);  
          
    } catch (Exception ex) {  
        ex.printStackTrace();  
        throw ex;  
    }  

		
	}

	public void registrar(int codigo, String nombre, String direccion, String telefono) {
		Universidad u = new Universidad();
		
		u.setCodigo(codigo);
		u.setTelefono(telefono);
		u.setDireccion(direccion);
		u.setNombre(nombre);
		universidadRemoto.ingresarUniversidad(u);;
	}


	public static void main(String arg[]) {
	
	Main main=new Main();
	
	try {
		main.intanciarRegistro();
		
	
		main.registrar(1, "UPS", "Calle vieja","0968718441");
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	
}
}

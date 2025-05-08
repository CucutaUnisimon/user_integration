package co.edu.unisimon.integration.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class PowerShellService {
	
	 	private static String APP = "powershell.exe";
	    private static String USUARIO = "seguridad.informatica@unisimon.edu.co";
	    private static String PASSWORD = "Un151m0n-53gur14@4";

	    private static String conexion() {
	        return String.format("%s $password = ConvertTo-SecureString '%s' -AsPlainText -Force; $credential = New-Object System.Management.Automation.PSCredential ('%s', $password); Connect-MsolService -Credential $credential;",
	            APP,
	            PASSWORD,
	            USUARIO
	        );

	    }
	    
	    public static boolean ejecutar(String command){
	        boolean retorno=false;
	        try {
	        	
	        	System.out.println(String.format(conexion()+" %s", command));
	            Process powerShellProcess = Runtime.getRuntime().exec(String.format(conexion()+" %s", command));
	            powerShellProcess.getOutputStream().close();
	            String line;
	            boolean userNotFound = false;
	            System.out.println("Standard Output:");
	            BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
	            while ((line = stdout.readLine()) != null) {
	                System.out.println(line);
	            }
	            stdout.close();
	            System.out.println("Standard Error:");
	            BufferedReader stderr = new BufferedReader(new InputStreamReader(
	                    powerShellProcess.getErrorStream()));
	            while ((line = stderr.readLine()) != null) {
	                System.out.println(line);
	                if (line.contains("User Not Found")) {
	                    userNotFound = true;
	                }
	            }
	            stderr.close();
	            
	            if (userNotFound) {
	                retorno = false;
	            }else {
	            	retorno = true;
	            }

	            
	        } catch (Exception e) {
	            retorno = false;
	        }
	        return retorno;
	    }
	    
	    public boolean changePassword(String usuarioActual, String contrasena) {
	        boolean retorno = false;

	        retorno = ejecutar(
	                String.format(
	                        "Set-MsolUserPassword -UserPrincipalName %s -NewPassword %s -ForceChangePassword $false;",
	                        usuarioActual.toLowerCase(),
	                        contrasena
	                )
	        );
	        return retorno;
	    }
	    
	    public boolean changePasswordAD(String usuarioActual, String contrasena) {
	    	boolean retorno = false;
	    	String comando = String.format(
	    		    "powershell.exe -File C:\\script\\cambiarClaveAzureAD.ps1 -usuarioObjetivo %s -passwordNuevo %s",
	    		    usuarioActual, contrasena
	    		);
	    	retorno = ejecutar(comando);
	    	return retorno;
	    	
	    }
	    

}

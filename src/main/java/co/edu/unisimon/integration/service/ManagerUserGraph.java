package co.edu.unisimon.integration.service;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.PasswordProfile;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import okhttp3.Request;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ManagerUserGraph {
	
	String clientId = "7718402f-0d83-4a22-a005-ed1e5456b97a";
    String clientSecret = "_T4T-p187r382gO8gP2GPj_sFHEv3V6Vv_";
    String tenantId = "unisimon.edu.co";
    
    private final GraphServiceClient<Request> graphClient;
    
    public ManagerUserGraph() {
        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenantId)
                .build();

        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(
                Arrays.asList("https://graph.microsoft.com/.default"),
                clientSecretCredential
        );

        this.graphClient = GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .buildClient();
    }

	
    public void createUser(String email, String username, String nombre, String apellido, String displayName, String password) {
        // Permisos requeridos: User.ReadWrite.All, Directory.AccessAsUser.All
        /*
    	ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenantId)
                .build();

        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(
                Arrays.asList("https://graph.microsoft.com/.default"),
                clientSecretCredential
        );

        GraphServiceClient<Request> graphClient = GraphServiceClient
                .builder()
                .authenticationProvider(authProvider)
                .buildClient();
		*/
        // Datos del nuevo usuario
        PasswordProfile passwordProfile = new PasswordProfile();
        passwordProfile.password = password;
        passwordProfile.forceChangePasswordNextSignIn = false;

        User user = new User();
        user.accountEnabled = true;
        user.displayName = displayName;
        user.mailNickname = username;
        user.userPrincipalName = email;
        user.passwordProfile = passwordProfile;
        user.usageLocation = "CO";

        // Crear usuario
        User createdUser = graphClient.users()
                .buildRequest()
                .post(user);

    }
    
    
    public void changeUserPassword(String userIdOrPrincipalName, String newPassword, boolean forceChangeNextSignIn) {
        PasswordProfile passwordProfile = new PasswordProfile();
        passwordProfile.password = newPassword;
        passwordProfile.forceChangePasswordNextSignIn = forceChangeNextSignIn;

        User user = new User();
        user.passwordProfile = passwordProfile;

        graphClient.users(userIdOrPrincipalName)
                .buildRequest()
                .patch(user);

        System.out.println("Contraseña actualizada para el usuario: " + userIdOrPrincipalName);
    }
    
    public boolean userExists(String userPrincipalNameOrId) {
        try {
            User user = graphClient.users(userPrincipalNameOrId)
                    .buildRequest()
                    .get();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
}


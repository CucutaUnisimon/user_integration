package co.edu.unisimon.integration.dto;

import lombok.Data;

@Data
public class UserRequest {
	
    private String username;
    private String password;
    private String documento;
    
}

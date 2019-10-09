package  br.com.fcamara.asnpark.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ROLE")
public class Role {
      
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private int id;
	
    @Column(name = "ROLE")
    private String role;

    public Role nome(String nomeRole) {
    	this.role = nomeRole;
    	return this;
    }   
        
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
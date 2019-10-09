package br.com.fcamara.asnpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fcamara.asnpark.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
   
	public Role findByRole(String role);
}

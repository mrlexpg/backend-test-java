package br.com.fcamara.asnpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fcamara.asnpark.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   
	public User findByLogin(String login);
}

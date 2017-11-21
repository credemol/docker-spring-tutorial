package ocap.tutorial.dockerspring.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ocap.tutorial.dockerspring.entity.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(@Param("username") String username);
	User findByEmail(@Param("email") String email);
	
	List<User> findByEmailLike(@Param("email")String email);
	List<User> findByEmailStartingWith(@Param("email")String email);
	List<User> findByEmailEndingWith(@Param("email")String email);
	List<User> findByEmailContaining(@Param("email")String email);
	
	List<User> findByUsernameAndEmail(@Param("username")String username, @Param("email")String email);
	List<User> findByUsernameOrEmail(@Param("username")String username, @Param("email")String email);
}

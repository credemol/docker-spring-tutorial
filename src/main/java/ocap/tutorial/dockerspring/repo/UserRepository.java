package ocap.tutorial.dockerspring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ocap.tutorial.dockerspring.entity.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>{

}

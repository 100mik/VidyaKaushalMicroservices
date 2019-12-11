package org.sj.msapprepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepoRepository extends JpaRepository<AppRepo, AppRepoIdentity>{

	
}


package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a join a.worker w where w.userAccount.id = ?1")
	Collection<Application> findApplicationsByWorkerId(int id);

	@Query("select w from Worker w where w.userAccount.id = ?1")
	Worker findWorker(int id);

	@Query("select a from Application a where a.id = ?1")
	Application findApplicationById(int id);

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findApplicationsByJobId(int jobId);

}

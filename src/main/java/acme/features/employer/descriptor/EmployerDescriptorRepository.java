
package acme.features.employer.descriptor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptors.Descriptor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDescriptorRepository extends AbstractRepository {

	@Query("select d from Descriptor d where d.job.id = ?1")
	Descriptor findOneDescriptorByJobId(int jobId);

	@Query("select d from Descriptor d where d.id = ?!")
	Descriptor findOneDescriptorById(int id);

}

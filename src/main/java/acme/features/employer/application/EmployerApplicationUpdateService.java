
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	private EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		// TODO Auto-generated method stub
		return false;
	}

	// De la vista a la base de datos
	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	// De la base de datos a la vista
	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public Application findOne(final Request<Application> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		// TODO Auto-generated method stub

	}
}


package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes (NotBlank es NotBlank y NotNull usarlo para String solo)

	// REFERENCE NUMBER (The reference number is a unique string with a minimum of five characters
	// and a maximum of 15 characters; it’s strongly recommended, but not required, that application references should match
	// the following pattern: “EEEE-JJJJ:WWWW”, where “EEEE” and “JJJJ” have the same meaning as before, and “WWWW” is a
	// four-letter string that helps recognise the worker.)
	// Reference numbers are not required to be generated automatically by the system.

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 15)
	@Pattern(regexp = "^[A-Z]{4}\\-[A-Z]{4}\\:[A-Z]{4}$")
	private String				reference;

	// CREATION MOMENT

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	// STATUS (An application status can be either “pending” if no decision has been made, yet, “accepted” if the decision was to
	// accept it, or “rejected” if the decision was to reject it.)

	@NotBlank
	@Pattern(regexp = "^(pending|accepted|rejected)$")
	private String				status;

	// STATEMENT

	@NotBlank
	private String				statement;

	// SOME SKILLS

	@NotBlank
	private String				skills;

	// SOME QUALIFICATIONS

	@NotBlank
	private String				qualifications;

	@Valid
	@ManyToOne(optional = false)
	private Job					job;

	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;
}

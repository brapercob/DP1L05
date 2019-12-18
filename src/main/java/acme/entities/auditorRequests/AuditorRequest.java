
package acme.entities.auditorRequests;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditorRequest extends DomainEntity {

	// Serialisation Identifier ------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes-----------------------------------------------------

	@NotBlank
	String						firm;

	@NotBlank
	String						statement;

	// Relationships-----------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = true)
	Authenticated				authenticated;

}

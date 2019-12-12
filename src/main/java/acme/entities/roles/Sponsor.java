
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Sponsor extends UserRole {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				organizationName;

	@NotBlank
	private String				holder;

	@NotBlank
	private String				brand;

	@CreditCardNumber
	private String				creditCardNumber;

	@NotNull
	private Integer				expirationMonth;

	@NotNull
	private Integer				expirationYear;
}

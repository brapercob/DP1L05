
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

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

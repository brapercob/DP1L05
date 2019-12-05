
package acme.entities.bulletins;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PerezBulletin extends DomainEntity {

	//Serialisation
	private static final long	serialVersionUID	= 1L;

	//Attributes
	@NotBlank
	private String				title;

	@NotBlank
	private String				content;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

}

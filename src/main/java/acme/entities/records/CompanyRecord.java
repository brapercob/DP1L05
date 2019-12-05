
package acme.entities.records;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecord extends DomainEntity {

	//Serialization identifier

	private static final long	serialVersionUID	= 1L;

	//Atributes

	@NotBlank
	private String				companyName;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				CEOName;

	@NotBlank
	private String				activitiesDescription;

	@URL
	@NotBlank
	private String				website;

	@Pattern(regexp = "^\\+{0,1}[0-9]{0,3}\\s{0,1}\\({0,1}[0-9]{0,4}\\){0,1}\\s{0,1}[0-9]{6}[0-9]{0,4}$")
	@NotBlank
	private String				phone;

	@Email
	@NotBlank
	private String				email;

	@NotNull
	@Min(0)
	@Max(5)
	private Integer				stars;


	// Derived Atributes --------------------------------------------------------------------------

	@Transient
	public Boolean getIncorporation() {
		String name = new String(this.companyName);
		name = name.toUpperCase().trim();
		return name.endsWith("INC.") || name.endsWith("INC");
	}

}

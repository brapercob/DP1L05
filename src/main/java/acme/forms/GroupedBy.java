
package acme.forms;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupedBy {

	private List<String>	text;
	private List<String>	amount;


	public GroupedBy(final List<String> text, final List<String> amount) {
		this.text = text;
		this.amount = amount;
	}
}

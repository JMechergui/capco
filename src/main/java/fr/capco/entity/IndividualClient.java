package fr.capco.entity;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualClient extends Client {
	
	@NotBlank
	private String lastName;
	@NotBlank
    private String firstName;

    public IndividualClient(Long id, String nom, String prenom) {
        super(id);
        this.lastName = nom;
        this.firstName = prenom;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(lastName, firstName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndividualClient other = (IndividualClient) obj;
		return Objects.equals(lastName, other.lastName) && Objects.equals(firstName, other.firstName);
	}
    
}

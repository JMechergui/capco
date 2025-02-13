package fr.capco.entity;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfessionalClient extends Client {
	
	@NotBlank
    private String companyName;
	
	@NotBlank
    private String vatNumber;
	
	@NotBlank
    private String siren;
	
	@NotNull
    private double annualTurnover;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(annualTurnover, vatNumber, companyName, siren);
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
		ProfessionalClient other = (ProfessionalClient) obj;
		return Double.doubleToLongBits(annualTurnover) == Double.doubleToLongBits(other.annualTurnover)
				&& Objects.equals(vatNumber, other.vatNumber) && Objects.equals(companyName, other.companyName)
				&& Objects.equals(siren, other.siren);
	}
}
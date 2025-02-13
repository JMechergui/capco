package fr.capco.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = IndividualClient.class, name = "individual"),
    @JsonSubTypes.Type(value = ProfessionalClient.class, name = "professional")
})
@ToString
public abstract class Client {
	@NotNull
    protected Long id;
}

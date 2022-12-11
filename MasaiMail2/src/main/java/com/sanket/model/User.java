package com.sanket.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	private String email;
	
	@Pattern(regexp = "(?<firstchar>(?=[A-Za-z]))", message = "First Name must not contain numbers or special characters")
	private String firstName;
	
	@Pattern(regexp = "(?<firstchar>(?=[A-Za-z]))", message = "Last Name must not contain numbers or special characters")
	private String lastName;
	
	@NotBlank(message = "MobileNumber is required")
	@Size(min = 10, max = 10)
	private String mobileNo;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 12)
	@Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$\"")
	private String password;

	@AssertTrue(message = "Age should be not in future")
	private LocalDate dateOfBirth;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Email> listOfEmail;
	
	
}






package com.reactivespring.medibuddyapp.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("user_logins")
public class Login {
    @Id
    private Integer id;

    private String username;
    private String password;

    private String firstname;
    private String lastname;
}

package com.ipi.games.DTO;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

@Getter
@Setter
public class PlayerDTO {

    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String email;
}

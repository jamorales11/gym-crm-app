package com.epam.gymcrm.application.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto{

    String username;

    String password;

    String newPassword;

}

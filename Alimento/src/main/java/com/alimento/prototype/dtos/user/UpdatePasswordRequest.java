package com.alimento.prototype.dtos.user;

import lombok.Getter;

@Getter
public class UpdatePasswordRequest {
    private String oldPassword;

    private String newPassword;
}

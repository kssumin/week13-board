package com.jungle.board.auth.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record SignInRequest(@NotEmpty @Email String email, @NotEmpty String password) {}

package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationData {
        private String login;
        private String password;
        private DataStatus status;
}
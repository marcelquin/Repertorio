package baseAPI.API.DTO;

import baseAPI.API.Model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}

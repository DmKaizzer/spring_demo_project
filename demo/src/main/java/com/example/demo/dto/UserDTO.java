package com.example.demo.dto;

import com.example.demo.dao.User;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private String email;
    private String lastActivity;
    private Integer priority;
    private Boolean isDeleted;

    public static UserDTO parseUser(User user) {
        UserDTO userDTO = new UserDTO();
        if (user != null) {
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setEnabled(user.getEnabled());
            userDTO.setEmail(user.getEmail());
            userDTO.setLastActivity(user.getLastActivity());
            userDTO.setPriority(user.getPriority());
            userDTO.setIsDeleted(user.getIsDeleted());
        }
        return userDTO;
    }
}

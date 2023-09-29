package com.example.demo.dto;

import com.example.demo.dao.Authority;
import lombok.Data;

@Data
public class AuthorityDTO {
    private String username;
    private String authority;

    public static AuthorityDTO parseAuthorityDTO(Authority authority) {
        AuthorityDTO authorityDTO = new AuthorityDTO();
        if (authority != null) {
            authorityDTO.setUsername(authority.getUsername().getUsername());
            authorityDTO.setAuthority(authority.getAuthority());
        }
        return authorityDTO;
    }
}

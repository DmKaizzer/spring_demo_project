package com.example.demo.dto;

import com.example.demo.dao.Master;
import lombok.Data;

@Data
public class MasterDTO {

    private String name;
    private String lastName;
    private String skill;
    private String style;
    private String login;

    public static MasterDTO parseMaster(Master master) {
        MasterDTO masterDTO = new MasterDTO();
        if (master != null) {
            masterDTO.setLastName(master.getLastName());
            masterDTO.setName(master.getName());
            masterDTO.setSkill(master.getSkill());
            masterDTO.setStyle(master.getStyle());
            masterDTO.setLogin(master.getLogin().getUsername());
        }
        return masterDTO;
    }
}

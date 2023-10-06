package com.example.demo.dto;

import com.example.demo.dao.Client;
import lombok.Data;

@Data
public class ClientDTO {

    private String login;
    private Integer age;
    private String name;
    private String lastName;
    private String master;

    public static ClientDTO parseClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        if (client != null) {
            clientDTO.setAge(client.getAge());
            clientDTO.setLogin(client.getUsername().getUsername());
            clientDTO.setName(client.getName());
            clientDTO.setLastName(client.getLastName());
            clientDTO.setMaster(client.getMaster() != null ? client.getMaster().getName() : null);
        }
        return clientDTO;
    }
}

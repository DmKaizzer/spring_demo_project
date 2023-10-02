package com.example.demo.dto;

import com.example.demo.dao.Studio;
import com.example.demo.dao.StudioId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudioDTO {
    private StudioId studioId;
    private List<MasterDTO> master;

    public static StudioDTO parseStudio(Studio studio) {
        StudioDTO studioDTO = new StudioDTO();
        if (studio != null) {
            studioDTO.setStudioId(studio.getStudioId());
            studioDTO.setMaster(new ArrayList<>(studio.getMasters().stream().map(MasterDTO::parseMaster).toList()));
        }
        return studioDTO;
    }
}

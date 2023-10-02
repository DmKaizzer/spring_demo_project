package com.example.demo.services;

import com.example.demo.dao.Master;
import com.example.demo.dao.Studio;
import com.example.demo.dao.StudioId;
import com.example.demo.dto.StudioDTO;
import com.example.demo.repository.MasterRepository;
import com.example.demo.repository.StudioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudioService {

    private final StudioRepository studioRepository;
    private final MasterRepository masterRepository;

    public Studio addStudio(StudioId id) {
        Studio studio = new Studio();
        studio.setStudioId(id);
        studioRepository.save(studio);
        return studio;
    }

    public List<StudioDTO> getAll() {
        return studioRepository.findAll().stream().map(StudioDTO::parseStudio).toList();
    }

    public StudioDTO addMaster(StudioId id, Long masterId) {
        Studio studio = studioRepository.findById(id).orElse(null);
        Master master = masterRepository.findById(masterId).orElse(null);
        if (studio != null && master != null) {
            List<Master> studioMasters = studio.getMasters() != null ? new ArrayList<>(studio.getMasters()) : new ArrayList<>();
            studioMasters.add(master);
            studio.setMasters(studioMasters);
            studioRepository.save(studio);
        }
        return StudioDTO.parseStudio(studio);
    }
}

package uni.LonelyTrouvere.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.LonelyTrouvere.lab.dto.BrigadeDTO;
import uni.LonelyTrouvere.lab.entity.Brigade;
import uni.LonelyTrouvere.lab.mapper.BrigadeMapper;
import uni.LonelyTrouvere.lab.repository.BrigadeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrigadeService {
    private final BrigadeRepo repository;
    private final BrigadeMapper mapper = BrigadeMapper.INSTANCE;
    public void update(BrigadeDTO brigadeDTO){
        Optional<Brigade> brigadeRecord = repository.findById(brigadeDTO.getId());
        if(brigadeRecord.isEmpty()){
            return;
        }
        Brigade brigade = brigadeRecord.get();
        brigade.setName(brigadeDTO.getName());
        brigade.setStaticCrew(brigadeDTO.isStaticCrew());
        repository.save(brigade);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
            repository.cascadeUpdate(Id);
            repository.cascadeDelete(Id);
        }
    }

    public Optional<BrigadeDTO> get(String Id){
        Optional<Brigade> brigade = repository.findById(Id);
        return brigade.map(mapper::toDTO);
    }

    public List<BrigadeDTO> getAll(){
        List<Brigade> brigades = repository.findAll();
        List<BrigadeDTO> DTOs = new ArrayList<>();
        for (Brigade brigade : brigades){
            DTOs.add(mapper.toDTO(brigade));
        }
        return DTOs;
    }

    public List<BrigadeDTO> getByName(String name){
        Optional<List<Brigade>> brigades = repository.findByName(name);
        if(brigades.isEmpty()){
            return new ArrayList<>();
        }
        List<BrigadeDTO> DTOs = new ArrayList<>();
        for (Brigade brigade : brigades.get()){
            DTOs.add(mapper.toDTO(brigade));
        }
        return DTOs;
    }

    public void create(BrigadeDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Brigade brigade = mapper.fromDTO(dto);
        repository.save(brigade);
    }
}

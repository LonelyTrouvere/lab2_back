package uni.LonelyTrouvere.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.LonelyTrouvere.lab.dto.RaceDTO;
import uni.LonelyTrouvere.lab.entity.Race;

@Mapper
public interface RaceMapper {
    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDTO(Race obj);
    Race fromDTO(RaceDTO dto);
}

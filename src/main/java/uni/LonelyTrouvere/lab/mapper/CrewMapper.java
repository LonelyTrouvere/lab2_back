package uni.LonelyTrouvere.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.LonelyTrouvere.lab.dto.CrewDTO;
import uni.LonelyTrouvere.lab.entity.Crewmate;

@Mapper
public interface CrewMapper {
    CrewMapper INSTANCE = Mappers.getMapper(CrewMapper.class);

    CrewDTO toDTO(Crewmate obj);
    Crewmate fromDTO(CrewDTO dto);
}

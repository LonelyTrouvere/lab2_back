package uni.LonelyTrouvere.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.LonelyTrouvere.lab.dto.FlightDTO;
import uni.LonelyTrouvere.lab.entity.Flight;

@Mapper
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightDTO toDTO(Flight obj);
    Flight fromDTO(FlightDTO dto);
}

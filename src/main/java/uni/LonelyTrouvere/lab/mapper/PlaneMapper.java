package uni.LonelyTrouvere.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.LonelyTrouvere.lab.dto.PlaneDTO;
import uni.LonelyTrouvere.lab.entity.Plane;

@Mapper
public interface PlaneMapper {
    PlaneMapper INSTANCE = Mappers.getMapper(PlaneMapper.class);

    PlaneDTO toDTO(Plane obj);
    Plane fromDTO(PlaneDTO dto);
}

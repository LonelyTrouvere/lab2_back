package uni.LonelyTrouvere.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.LonelyTrouvere.lab.dto.BrigadeDTO;
import uni.LonelyTrouvere.lab.entity.Brigade;

@Mapper
public interface BrigadeMapper {
    BrigadeMapper INSTANCE = Mappers.getMapper(BrigadeMapper.class);

    BrigadeDTO toDTO(Brigade brigade);
    Brigade fromDTO(BrigadeDTO dto);
}

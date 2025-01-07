package pumppro.springboot_backend.mapper;

import pumppro.springboot_backend.model.Set;
import pumppro.springboot_backend.dtos.SetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SetMapper {

    @Mapping(source = "createdBy.id", target = "userId")
    SetDto toSetDto(Set set);

    @Mapping(source = "userId", target = "createdBy.id")
    Set toSet(SetDto setDto);
}
package pumppro.springboot_backend.mapper;

import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.dtos.UserDto;
import pumppro.springboot_backend.dtos.SignUpDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}

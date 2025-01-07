package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.SetDto;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.exception.ResourceNotFoundException;
import pumppro.springboot_backend.mapper.SetMapper;
import pumppro.springboot_backend.model.Set;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.model.Workout;
import pumppro.springboot_backend.repository.SetRepository;
import pumppro.springboot_backend.repository.UserRepository;
import pumppro.springboot_backend.repository.WorkoutRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SetService {

    private final SetRepository setRepository;
    private final SetMapper setMapper;
    private final UserRepository userRepository;

    public SetDto createSet(SetDto setDto) throws AppException {
        User user = userRepository.findById(setDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        Set set = setMapper.toSet(setDto);
        set.setCreatedBy(user);

        Set savedSet = setRepository.save(set);
        return setMapper.toSetDto(savedSet);
    }

    public List<SetDto> getAllSets() {
        return setRepository.findAll().stream()
                .map(setMapper::toSetDto)
                .collect(Collectors.toList());
    }

    public SetDto getSetById(long id) {
        Set set = setRepository.findById(id)
                .orElseThrow(() -> new AppException("Set not found", HttpStatus.NOT_FOUND));
        return setMapper.toSetDto(set);
    }

    public SetDto updateSet(long id, SetDto setDto) {
        Set set = setRepository.findById(id)
                .orElseThrow(() -> new AppException("Set not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(setDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        set.setWeight(setDto.getWeight());
        set.setReps(setDto.getReps());
        set.setCreatedBy(user);

        Set updatedSet = setRepository.save(set);
        return setMapper.toSetDto(updatedSet);
    }

    public void deleteSet(long id) {
        Set set = setRepository.findById(id)
                .orElseThrow(() -> new AppException("Set not found", HttpStatus.NOT_FOUND));
        setRepository.delete(set);
    }
}

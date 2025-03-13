package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.WorkoutTemplateDto;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.mapper.WorkoutTemplateMapper;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.model.WorkoutTemplate;
import pumppro.springboot_backend.repository.UserRepository;
import pumppro.springboot_backend.repository.WorkoutTemplateRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutTemplateService {

    private final WorkoutTemplateRepository workoutTemplateRepository;
    private final UserRepository userRepository;
    private final WorkoutTemplateMapper workoutTemplateMapper;

    public List<WorkoutTemplateDto> getAllWorkoutTemplates(Long userId) {
        List<WorkoutTemplate> templates = workoutTemplateRepository.findByCreatedById(userId);
        return templates.stream()
                .map(workoutTemplateMapper::toWorkoutTemplateDto)
                .collect(Collectors.toList());
    }

    public WorkoutTemplateDto createWorkoutTemplate(WorkoutTemplateDto workoutTemplateDto) {
        User user = userRepository.findById(workoutTemplateDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        WorkoutTemplate workoutTemplate = workoutTemplateMapper.toWorkoutTemplate(workoutTemplateDto);
        workoutTemplate.setCreatedBy(user);

        WorkoutTemplate savedTemplate = workoutTemplateRepository.save(workoutTemplate);
        return workoutTemplateMapper.toWorkoutTemplateDto(savedTemplate);
    }

    public WorkoutTemplateDto getWorkoutTemplateById(Long id) {
        WorkoutTemplate template = workoutTemplateRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout Template not found", HttpStatus.NOT_FOUND));
        return workoutTemplateMapper.toWorkoutTemplateDto(template);
    }

    public WorkoutTemplateDto updateWorkoutTemplate(Long id, WorkoutTemplateDto workoutTemplateDto) {
        WorkoutTemplate workoutTemplate = workoutTemplateRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout Template not found", HttpStatus.NOT_FOUND));

        // Set the name and description
        workoutTemplate.setName(workoutTemplateDto.getName());
        workoutTemplate.setDescription(workoutTemplateDto.getDescription()); // Ensure this is correctly mapped

        // Save and return updated DTO
        WorkoutTemplate updatedTemplate = workoutTemplateRepository.save(workoutTemplate);
        return workoutTemplateMapper.toWorkoutTemplateDto(updatedTemplate);
    }


    public void deleteWorkoutTemplate(Long id) {
        WorkoutTemplate template = workoutTemplateRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout Template not found", HttpStatus.NOT_FOUND));
        workoutTemplateRepository.delete(template);
    }
}

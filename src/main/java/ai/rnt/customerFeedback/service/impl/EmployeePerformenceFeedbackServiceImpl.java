package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceFeedbackMapper.TO_EMPLOYEE_PERFORMENCE_FEEDBACK_DTO;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceFeedbackMapper.TO_EMP_FEEDBACK;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceFeedbackMapper.TO_EMP_PERF_FEEDBACK_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.TO_EMPLOYEE_PERFORMENCE_MASTER_DTO;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION_DTOS;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.EmployeePerformenceFeedbackDTO;
import ai.rnt.customerFeedback.entity.EmployeePerformenceFeedback;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.EmployeePerformenceFeedbackRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;
import ai.rnt.customerFeedback.service.EmployeePerformenceFeedbackService;

@Service
public class EmployeePerformenceFeedbackServiceImpl implements EmployeePerformenceFeedbackService {

	@Autowired
	EmployeePerformenceFeedbackRepository employeePerformenceFeedbackRepository;

	@Autowired
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployeePerformenceFeedback() {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, employeePerformenceFeedbackRepository.findAll().stream().map(
					e -> TO_EMPLOYEE_PERFORMENCE_FEEDBACK_DTO.apply(e).orElseThrow(ResourceNotFoundException::new)));
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeePerformenceFeedbackById(Integer id) {
		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		List<EmployeePerformenceFeedback> employee = employeePerformenceFeedbackRepository
				.findByEmployeePerformenceMasterEmpPerformenceIdOrderByQuestionMasterQuestionIdAsc(id);
		map.put(ApiResponse.SUCCESS, true);
		map.put(ApiResponse.DATA, TO_EMP_PERF_FEEDBACK_DTO_LIST.apply(employee));
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeePerformenceFeedback(
			List<EmployeePerformenceFeedbackDTO> employeePerformenceFeedbackDTO) {

		List<EmployeePerformenceFeedback> st = new ArrayList<>();
		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		try {
			List<EmployeePerformenceFeedback> findById2 = employeePerformenceFeedbackRepository
					.findByEmployeePerformenceMasterEmpPerformenceId(
							employeePerformenceFeedbackDTO.get(0).getEmployeePerformenceMaster().getEmpPerformenceId());

			if (!findById2.isEmpty()) {
				map.put(ApiResponse.SUCCESS, false);
				return new ResponseEntity<>(map, HttpStatus.CREATED);
			} else {
				employeePerformenceFeedbackDTO.stream().forEach(employeePerformenceFeedback -> {
					employeePerformenceFeedback
							.setEmployeePerformenceMaster(
									TO_EMPLOYEE_PERFORMENCE_MASTER_DTO
											.apply(employeePerformenceMasterRepository
													.findById(employeePerformenceFeedback.getEmployeePerformenceMaster()
															.getEmpPerformenceId())
													.orElseThrow(ResourceNotFoundException::new))
											.orElseThrow(ResourceNotFoundException::new));
					employeePerformenceFeedback
							.setQuestionMaster(
									TO_QUESTION_DTOS
											.apply(questionRepository
													.findById(employeePerformenceFeedback.getQuestionMaster()
															.getQuestionId())
													.orElseThrow(ResourceNotFoundException::new))
											.orElseThrow(ResourceNotFoundException::new));

					st.add(employeePerformenceFeedbackRepository.save(TO_EMP_FEEDBACK.apply(employeePerformenceFeedback)
							.orElseThrow(ResourceNotFoundException::new)));
				});
				map.put(ApiResponse.SUCCESS, true);
				map.put(ApiResponse.DATA, st);
				return new ResponseEntity<>(map, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new CRMException(e);
		}

	}

}

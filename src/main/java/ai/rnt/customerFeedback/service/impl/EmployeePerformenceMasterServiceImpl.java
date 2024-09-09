package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.constants.SecurityConstant.EMPLOYEEPERFORMENCEID;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.EMPLOYEE_PERFORMANCE_MAILBOX;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.EMPLOYEE_PERFORMANCE_MAILBOX_DTO;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.TO_EMPLOYEE_PERFORMENCE_MASTER;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.TO_EMPLOYEE_PERFORMENCE_MASTER_DTO;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.TO_EMPLOYEE_PERFORMENCE_MASTER_DTO_LIST;

import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.rnt.customerFeedback.dto.EmployeePerformenceMailBoxDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterDTO;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.EmployeePerformenceFeedback;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMailBox;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceFeedbackRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMailBoxRepo;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;
import ai.rnt.customerFeedback.repository.ProjectTeamRepository;
import ai.rnt.customerFeedback.repository.RoleMasterRepository;
import ai.rnt.customerFeedback.service.EmployeePerformenceMasterService;

@Service
public class EmployeePerformenceMasterServiceImpl implements EmployeePerformenceMasterService {

	@Autowired
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	EmployeePerformenceFeedbackRepository employeePerformenceFeedbackRepository;

	@Autowired
	RoleMasterRepository roleMasterRepository;

	@Autowired
	ProjectTeamRepository projectTeamRepository;

	@Autowired
	private EmployeePerformenceMailBoxRepo performenceMailBoxRepo;

	@Autowired
	ProjectRepository projectRepository;

	@Override
	@Transactional
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeePerformenceMaster(
			EmployeePerformenceMasterDTO employeePerformenceMasterDTO) {

		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		try {
			EmployeePerformenceMaster employeePerformenceMaster = TO_EMPLOYEE_PERFORMENCE_MASTER
					.apply(employeePerformenceMasterDTO).orElse(null);
			if (Objects.nonNull(employeePerformenceMaster)) {
				EmployeePerformenceMaster save = employeePerformenceMasterRepository.save(employeePerformenceMaster);
				map.put(ApiResponse.SUCCESS, true);
				map.put(ApiResponse.DATA, save);
			}

		} catch (Exception e) {
			throw new CRMException(e);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployeesPerformence() {
		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		try {
			List<EmployeePerformenceMaster> findAll = employeePerformenceMasterRepository
					.findAll(Sort.by(Sort.Direction.DESC, EMPLOYEEPERFORMENCEID));

			findAll.stream().forEach(e -> {
				List<EmployeePerformenceFeedback> feedbackList = employeePerformenceFeedbackRepository
						.findByEmployeePerformenceMasterEmpPerformenceId(e.getEmpPerformenceId());

				if (feedbackList != null && !feedbackList.isEmpty()) {
					String formattedDate = feedbackList.get(0).getCreatedDate()
							.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

					e.setFilled(true);
					e.setFilledDate(formattedDate);
					e.setSortStatus(1);
				} else {
					e.setFilledDate(null);
					e.setFilled(false);
					e.setSortStatus(0);
				}

				e.setIsEmailSend(e.getEmployeePerformenceMailBox() != null);
			});

			List<EmployeePerformenceMasterDTO> dtoList = TO_EMPLOYEE_PERFORMENCE_MASTER_DTO_LIST.apply(findAll);

			map.put(ApiResponse.SUCCESS, true);
			map.put(ApiResponse.DATA, dtoList);
		} catch (Exception e) {
			throw new CRMException(e);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeePerformenceById(Integer id) {
		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		try {
			EmployeePerformenceMaster employeePerformenceMaster = employeePerformenceMasterRepository.findById(id)
					.orElseThrow(
							() -> new ResourceNotFoundException("EmployeePerformenceMaster", "empPerformenceId", id));

			List<EmployeePerformenceFeedback> employeePerformenceFeedbacks = employeePerformenceFeedbackRepository
					.findByEmployeePerformenceMasterEmpPerformenceId(id);

			if (!employeePerformenceFeedbacks.isEmpty()) {
				EmployeePerformenceFeedback employeePerformenceFeedback = employeePerformenceFeedbacks.get(0);
				String formattedDate = employeePerformenceFeedback.getCreatedDate()
						.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

				employeePerformenceMaster.setFilled(true);
				employeePerformenceMaster.setFilledDate(formattedDate);
			} else {
				employeePerformenceMaster.setFilled(false);
				employeePerformenceMaster.setFilledDate(null); // Or set to a default value
			}

			EmployeePerformenceMasterDTO employeePerformenceMasterDTO = TO_EMPLOYEE_PERFORMENCE_MASTER_DTO
					.apply(employeePerformenceMaster).orElseThrow(ResourceNotFoundException::new);

			map.put(ApiResponse.SUCCESS, true);
			map.put(ApiResponse.DATA, employeePerformenceMasterDTO);
		} catch (Exception e) {
			map.put(ApiResponse.SUCCESS, false);
			map.put(ApiResponse.DATA, "Failed to fetch employee performence...{} ");
			throw new CRMException(e);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateEmployeePerformenceMaster(Integer id,
			EmployeePerformenceMasterDTO employeePerformenceMasterDTO) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			TO_EMPLOYEE_PERFORMENCE_MASTER.apply(employeePerformenceMasterDTO).ifPresent(empPerformance -> {
				EmployeePerformenceMaster employeePerformenceMaster = employeePerformenceMasterRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("EmployeePerformenceMaster",
								EMPLOYEEPERFORMENCEID, id));
				Project project = projectRepository.findById(employeePerformenceMasterDTO.getProject().getProjectId())
						.orElse(null);
				employeePerformenceMaster.setProject(project);
				EmployeeMaster employeeMaster = employeeMasterRepository
						.findById(employeePerformenceMasterDTO.getEmployeeMaster().getStaffId()).orElse(null);
				employeePerformenceMaster.setEmployeeMaster(employeeMaster);
				employeePerformenceMaster.setProjectManagerName(employeePerformenceMasterDTO.getProjectManagerName());
				employeePerformenceMaster.setProjectManagerEmail(employeePerformenceMasterDTO.getProjectManagerEmail());
				resultMap.put(ApiResponse.DATA, TO_EMPLOYEE_PERFORMENCE_MASTER_DTO
						.apply(employeePerformenceMasterRepository.save(employeePerformenceMaster)));
			});

			resultMap.put(ApiResponse.MESSAGE, "updated");
			resultMap.put(ApiResponse.SUCCESS, true);
		} catch (Exception e) {
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeePerformanceMail(
			EmployeePerformenceMailBoxDTO performenceMailBoxDTO) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			if (performenceMailBoxDTO != null) {
				EmployeePerformenceMailBox findByPerformenceMailBox = performenceMailBoxRepo.findByEmployeePerformance(
						performenceMailBoxDTO.getEmployeePerformenceMaster().getEmpPerformenceId());
				if (findByPerformenceMailBox != null) {
					resultMap.put(ApiResponse.SUCCESS, false);
					resultMap.put(ApiResponse.DATA, "Mail Already Sent!");
					return new ResponseEntity<>(resultMap, HttpStatus.OK);
				}
				EmployeePerformenceMailBox employeePerformenceMailBox = EMPLOYEE_PERFORMANCE_MAILBOX
						.apply(performenceMailBoxDTO).orElse(null);
				EmployeePerformenceMailBox savedEmployeePerformenceMailBox = performenceMailBoxRepo
						.save(employeePerformenceMailBox);
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA,
						EMPLOYEE_PERFORMANCE_MAILBOX_DTO.apply(savedEmployeePerformenceMailBox));
			}

		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getSavedEmployeePerformanceMail(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			EmployeePerformenceMailBox findByPerformenceMailBox = performenceMailBoxRepo.findByEmployeePerformance(id);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, EMPLOYEE_PERFORMANCE_MAILBOX_DTO.apply(findByPerformenceMailBox));

		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

}

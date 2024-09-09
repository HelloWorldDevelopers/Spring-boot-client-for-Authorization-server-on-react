package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.EmployeeToDtoMapper.TO_EMPLOYEEMASTER_DTO;
import static ai.rnt.customerFeedback.dto_mapper.EmployeeToDtoMapper.TO_EMPLOYEE_MASTER_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.EmployeeToDtoMapper.TO_Employee;
import static ai.rnt.customerFeedback.dto_mapper.EmployeeToDtoMapper.TO_EmployeeMaster;
import static ai.rnt.customerFeedback.dto_mapper.EmployeeToDtoMapper.TO_Employees;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dao.EmployeeDaoService;
import ai.rnt.customerFeedback.dao.RoleMasterDaoService;
import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.EmployeeProfile;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeeProfileRepository;
import ai.rnt.customerFeedback.repository.RoleMasterRepository;
import ai.rnt.customerFeedback.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDaoService employeeDaoService;

	@Autowired
	EmployeeProfileRepository employeeProfileRepository;

	private final RoleMasterDaoService roleMasterDaoService;

	@Autowired
	RoleMasterRepository roleMasterRepository;

	private final EmployeeMasterRepository employeeMasterRepository;

	@Override
	public Optional<EmployeeDto> getEmployeeByUserId(String userId) {
		return TO_Employee.apply(employeeDaoService.getEmployeebyUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "userId", userId)));
	}

	@Override
	public Optional<EmployeeMaster> getById(Integer assignTo) {
		try {
			Optional<EmployeeDto> byId = employeeDaoService.getById(assignTo);
			if (byId.isPresent()) {
				return TO_EmployeeMaster.apply(byId.get());
			} else {
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAdminAndUser() {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_Employees.apply(roleMasterDaoService.getAdminAndUser()));
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployees() {
		log.info("Enter into findAll() Employee method...{} ");
		List<EmployeeMaster> employeeMasterList = new ArrayList<>();
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		LocalDate date = LocalDate.now();
		try {
			Sort sortByFirstName = Sort.by("firstName").ascending();
			List<EmployeeMaster> empList = employeeMasterRepository.findAll(sortByFirstName);
			for (EmployeeMaster employeeMaster : empList) {
				LocalDate dateOfDeparture = employeeMaster.getDepartureDate();
				if ((dateOfDeparture == null || !date.isAfter(dateOfDeparture))
						&& (employeeMaster.getEmailId()) != null) {
					employeeMasterList.add(employeeMaster);

				}
			}
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, TO_EMPLOYEE_MASTER_DTO_LIST.apply(employeeMasterList));
		} catch (Exception e) {
			log.error("Error in fetching employee details...{}", e);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(responseMap, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeeByStaffId(Integer staffid) {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		LocalDate date = LocalDate.now();
		try {
			EmployeeMaster employeeMaster = employeeMasterRepository.getById(staffid);
			LocalDate dateOfDeparture = employeeMaster.getDepartureDate();
			if ((dateOfDeparture == null || !date.isAfter(dateOfDeparture)) && (employeeMaster.getEmailId()) != null) {
				responseMap.put(ApiResponse.SUCCESS, true);
				responseMap.put(ApiResponse.DATA, TO_EMPLOYEEMASTER_DTO.apply(employeeMaster));

			}

		} catch (Exception e) {
			log.error("Error in fetching employee details...{}", e);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProfilePic(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			EmployeeProfile optional = employeeProfileRepository.findById(id).orElse(null);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, optional);
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

}

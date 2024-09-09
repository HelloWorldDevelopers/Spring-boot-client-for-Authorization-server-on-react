package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.RoleDtoMapper.TO_ROLE_DTO;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.RBAC;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.RoleMasterRepository;
import ai.rnt.customerFeedback.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleMasterRepository roleMasterRepository;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllRoles() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		try {
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, TO_ROLE_DTO.apply(roleMasterRepository.findAll()));
		} catch (Exception e) {
			log.error("Error in fetching roles details...{}", e);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@Override
	public List<RBAC> getRbacList(Integer staffId) {
		try {
			List<RBAC> rbacList = new ArrayList<>();
			List<Object[]> rabcData = roleMasterRepository.getRbacList(staffId);
			for (Object[] e : rabcData) {
				RBAC rbac = new RBAC();
				rbac.setUseCase(Objects.nonNull(e[0]) ? e[0].toString() : "");
				rbac.setReadAccess(Objects.nonNull(e[1]) ? e[1].toString() : "");
				rbac.setWriteAccess(Objects.nonNull(e[2]) ? e[2].toString() : "");
				rbac.setEditAccess(Objects.nonNull(e[3]) ? e[3].toString() : "");
				rbac.setDeleteAccess(Objects.nonNull(e[4]) ? e[4].toString() : "");
				rbac.setModuleAccess(rbac.getReadAccess().equals("Y") || rbac.getWriteAccess().equals("Y")
						|| rbac.getEditAccess().equals("Y") || rbac.getDeleteAccess().equals("Y"));
				rbacList.add(rbac);
			}
			return rbacList;
		} catch (Exception e) {
			log.error("Error in fetching RBAC of Employee...{}", e);
			throw new CRMException(e);
		}
	}

	@Override
	public List<String> getRoleList(Integer staffId) {
		return roleMasterRepository.getRoleList(staffId);
	}

}

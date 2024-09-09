package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.rnt.customerFeedback.dto.Role;
import ai.rnt.customerFeedback.entity.RoleMaster;

public class RoleDtoMapper {

	RoleDtoMapper() {
	}

	public static final Function<RoleMaster, Optional<Role>> TO_Role = e -> {
		return evalMapper(e, Role.class);
	};

	public static final Function<Collection<RoleMaster>, List<Role>> TO_ROLE_DTO = e -> newList(
			evalMapperCollection(e, Role.class));

	public static final Function<Collection<RoleMaster>, List<Role>> TO_Roles = e -> {
		return e.stream().map(dm -> TO_Role.apply(dm).get()).collect(Collectors.toList());
	};
}

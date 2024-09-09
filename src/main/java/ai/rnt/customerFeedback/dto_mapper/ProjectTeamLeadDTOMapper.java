package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.LeadReportingManagerDTO;
import ai.rnt.customerFeedback.entity.ProjectTeam;

public class ProjectTeamLeadDTOMapper {

	public static final Function<Collection<ProjectTeam>, List<LeadReportingManagerDTO>> TO_PROJECT_TEAM_DTO_LIST = e -> newList(
			evalMapperCollection(e, LeadReportingManagerDTO.class));

	public static final Function<ProjectTeam, Optional<LeadReportingManagerDTO>> TO_PROJECT_TEAM_DTO = e -> evalMapper(
			e, LeadReportingManagerDTO.class);

}

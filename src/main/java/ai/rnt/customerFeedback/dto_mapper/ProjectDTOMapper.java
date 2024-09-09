package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.ProjectDto;
import ai.rnt.customerFeedback.entity.Project;

public class ProjectDTOMapper {

	private ProjectDTOMapper() {
	}

	public static final Function<Collection<Project>, List<ProjectDto>> TO_PROJECT_DTO_LIST = e -> newList(
			evalMapperCollection(e, ProjectDto.class));

}

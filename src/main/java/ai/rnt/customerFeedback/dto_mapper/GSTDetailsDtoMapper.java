package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.GSTDetailsDto;
import ai.rnt.customerFeedback.entity.GSTDetails;

public class GSTDetailsDtoMapper {
	private GSTDetailsDtoMapper() {
	}

	public static final Function<GSTDetailsDto, Optional<GSTDetails>> TO_GST_DETAILS = e -> evalMapper(e,
			GSTDetails.class);

	public static final Function<Collection<GSTDetailsDto>, List<GSTDetails>> TO_GST_DETAILS_LIST = e -> newList(
			evalMapperCollection(e, GSTDetails.class));
}

package ai.rnt.customerFeedback.util;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.of;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionUtil {

	private CollectionUtil() {
	}

	/**
	 * This method will take any type object's and generate set.
	 * 
	 * @param <T>
	 * @param elements
	 * @return HashSet of elements if parameter if null then return empty set
	 * @exception
	 * @since version 1.0
	 */
	@SafeVarargs
	public static <T> Set<T> newSet(T... elements) {
		if (nonNull(elements))
			return of(elements).collect(toCollection(HashSet::new));
		return emptySet();
	}

	/**
	 * @param l
	 * @return
	 * @since 08-Mar-2022
	 * @version 1.3
	 */
	public static String commaSeperatedVals(List<String> l) {
		return nonNull(l) ? String.join(", ", l) : null;
	}

	/**
	 * @param <T>
	 * @param l
	 * @return
	 * @since 08-Mar-2022
	 * @version 1.3
	 */
	public static <T> List<T> removeDuplicate(List<T> l) {
		return nonNull(l) ? new ArrayList<>(new HashSet<>(l)) : new ArrayList<>();
	}

	/**
	 * @param <T>
	 * @param collection
	 * @return
	 * @since 30-Dec-2021
	 * @version 1.3
	 */
	public static <T> Set<T> newSet(Collection<T> collection) {
		if (nonNull(collection))
			return collection.stream().collect(Collectors.toSet());
		return emptySet();
	}

	/**
	 * @param addressMasters
	 * @return
	 * @version 2.0
	 */
	public static <T> List<T> newList(Collection<T> collection) {
		if (Objects.isNull(collection) || collection.isEmpty())
			return Collections.emptyList();
		return collection.stream().collect(Collectors.toList());
	}

	/**
	 * @param addressMasters
	 * @return
	 * @version 2.0
	 */
	@SafeVarargs
	public static <T> List<T> newList(T... elements) {
		return isNull(elements) || elements.length == 0 ? emptyList()
				: of(elements).collect(toCollection(ArrayList::new));
	}

}

package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CollectionUtilTest {

	@Test
	void testNewSet_WithElements() {
		Set<Integer> set = CollectionUtil.newSet(1, 2, 3);
		assertEquals(3, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertTrue(set.contains(3));
	}

	@Test
	void testNewSet_WithNullElements() {
		Set<Integer> set = CollectionUtil.newSet((Integer[]) null);
		assertNotNull(set);
		assertEquals(0, set.size());
	}

	@Test
	void testNewSet_WithCollection() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		Set<Integer> set = CollectionUtil.newSet(list);
		assertEquals(3, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertTrue(set.contains(3));
	}

	@Test
	void testNewSet_WithNullCollection() {
		Set<Integer> set = CollectionUtil.newSet((List<Integer>) null);
		assertNotNull(set);
		assertEquals(0, set.size());
	}

	@Test
	void testNewList_WithElements() {
		List<Integer> list = CollectionUtil.newList(1, 2, 3);
		assertEquals(3, list.size());
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
	}

	@Test
	void testNewList_WithNullElements() {
		List<Integer> list = CollectionUtil.newList((Integer[]) null);
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	@Test
	void testRemoveDuplicate() {
		List<Integer> list = Arrays.asList(1, 2, 3, 2, 1);
		List<Integer> result = CollectionUtil.removeDuplicate(list);
		assertEquals(3, result.size());
		assertEquals(1, result.get(0));
		assertEquals(2, result.get(1));
		assertEquals(3, result.get(2));
	}

	@Test
	void testCommaSeperatedVals_WithNonNullList() {
		List<String> list = Arrays.asList("a", "b", "c");
		String result = CollectionUtil.commaSeperatedVals(list);
		assertEquals("a, b, c", result);
	}

	@Test
	void testCommaSeperatedVals_WithNullList() {
		String result = CollectionUtil.commaSeperatedVals(null);
		assertNull(result);
	}
}

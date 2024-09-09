package ai.rnt.customerFeedback.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;

class CrudServiceTest {

    private CrudServiceImpl crudService;

    @BeforeEach
    void setUp() {
        crudService = new CrudServiceImpl();
    }

    @Test
    void testSave() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.save(new Object());
        });
    }

    @Test
    void testSaveAll() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.saveAll(Collections.emptyList());
        });
    }

    @Test
    void testEdit() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.edit(new Object());
        });
    }

    @Test
    void testEditAll() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.editAll(Collections.emptyList());
        });
    }

    @Test
    void testDelete() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.delete(1);
        });
    }

    @Test
    void testDeleteById() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.deleteById(1);
        });
    }

    @Test
    void testGetById() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.getById(1);
        });
    }

    @Test
    void testGetEntityById() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.getEntityById(1);
        });
    }

    @Test
    void testGetByIds() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.getByIds(1);
        });
    }

    @Test
    void testGetEntitysByIds() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.getEntitysByIds(1);
        });
    }

    @Test
    void testGetOneByExample() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.getOneByExample(Example.of(new Object()));
        });
    }

    @Test
    void testGetAllByExample() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.getAllByExample(Example.of(new Object()));
        });
    }

    @Test
    void testGetAll() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.getAll();
        });
    }

    @Test
    void testExistsById() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            crudService.existsById(1);
        });
    }

    private static class CrudServiceImpl implements CrudService<Object, Object> {
    }


}

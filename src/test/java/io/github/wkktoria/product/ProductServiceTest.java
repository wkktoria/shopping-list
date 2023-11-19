package io.github.wkktoria.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private static ProductService SUT;

    @BeforeEach
    public void init() {
        MockRepository mockRepository = new MockRepository();
        SUT = new ProductService(mockRepository);
    }

    @Test
    public void test_findAll_returnsListOfProducts() {
        // given

        // when
        var result = SUT.findAll();

        // then
        assert (!result.isEmpty());
    }

    @Test
    public void test_addProduct_addsProduct() {
        // given
        var currentSize = SUT.findAll().size();
        var product = new ProductDTO("Test", 1);

        // when
        var result = SUT.addProduct(product);

        // then
        assertEquals(currentSize + 1, SUT.findAll().size());
        assertTrue(SUT.findAll().contains(result));
    }

    @Test
    public void test_deleteProduct_existingId_deletesProduct() {
        // given
        var currentSize = SUT.findAll().size();
        int id = 1;

        // when
        SUT.deleteProduct(id);

        // then
        assertEquals(currentSize - 1, SUT.findAll().size());
    }

    @Test
    public void test_deleteProduct_existingId_returnsTrue() {
        // given
        int id = 1;


        // when
        var result = SUT.deleteProduct(id);

        // then
        assertTrue(result);
    }

    @Test
    public void test_deleteProduct_nonExistingId_returnsTrue() {
        // given
        int id = -1;


        // when
        var result = SUT.deleteProduct(id);

        // then
        assertFalse(result);
    }

    @Test
    public void test_toggleBought_changesBoughtValue() {
        // given
        int id = 1;
        var currentBought = SUT.findAll().get(id).getBought();

        // when
        var result = SUT.toggleBought(id);

        // then
        assertEquals(!currentBought, result.getBought());
    }
}
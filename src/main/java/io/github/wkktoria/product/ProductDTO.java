package io.github.wkktoria.product;

class ProductDTO {
    private String name;
    private Integer quantity;

    ProductDTO() {

    }

    ProductDTO(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

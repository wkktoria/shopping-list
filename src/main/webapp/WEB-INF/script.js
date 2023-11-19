const API_URL = 'http://localhost:8080/api';
const PRODUCTS_API_URL = `${API_URL}/products`;

const addProductForm = document.getElementById('addProductForm');
const productsCollection = document.getElementById('allProducts');

fetchProducts();

function fetchProducts() {
    fetch(PRODUCTS_API_URL)
        .then(processOkResponse)
        .then(renderProducts);
}

function renderProducts(products) {
    productsCollection.innerHTML = '';
    products.forEach(addProduct);
}

function addProduct(product) {
    const productContainer = document.createElement('div');
    productContainer.classList.add('product');
    productContainer.setAttribute('data-id', product.id);

    const hName = document.createElement('h3');
    const pQuantity = document.createElement('p');
    const checkbox = document.createElement('input');
    const button = document.createElement('button');

    hName.textContent = `${product.name}`;

    pQuantity.textContent = `x${product.quantity}`;

    checkbox.type = 'checkbox';
    checkbox.checked = product.bought;

    button.classList.add('btn', 'btn-delete');
    button.textContent = 'Delete';

    productContainer.append(hName);
    productContainer.append(pQuantity);
    productContainer.append(checkbox)
    productContainer.append(button);

    productsCollection.append(productContainer);
}

addProductForm.addEventListener('submit', (event) => {
    event.preventDefault();

    fetch(PRODUCTS_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        }, body: JSON.stringify({
            name: addProductForm.elements.name.value,
            quantity: addProductForm.elements.quantity.value,
        })
    }).then(processOkResponse)
        .then(addProduct)
        .then(() => addProductForm.reset());
});

productsCollection.addEventListener('click', (event) => {
    event.preventDefault();

    let deleteButtonPressed = event.target.className === 'btn btn-delete';

    if (deleteButtonPressed) {
        let id = event.target.parentElement.dataset.id;

        fetch(`${PRODUCTS_API_URL}/${id}`, {
            method: 'DELETE'
        }).then(processOkResponse)
            .then(fetchProducts);
    }
});

function processOkResponse(response = {}) {
    if (response.ok) {
        if (response.status === 204) {
            return;
        }
        return response.json();
    }

    throw new Error(`Status not 200 (${response.status})`);
}
const API_URL = 'http://localhost:8080/api';
const PRODUCTS_API_URL = `${API_URL}/products`;

getProducts()

const addProductForm = document.getElementById('addProductForm');
const addProductBtn = document.getElementById('addProductBtn');

addProductBtn.addEventListener('click', (event) => {
    event.preventDefault();

    const formObj = {
        name: addProductForm.elements.name.value,
        quantity: addProductForm.elements.quantity.value
    };

    fetch(PRODUCTS_API_URL, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: JSON.stringify(formObj)
    })
        .then(processOkResponse)
        .then(createProduct)
        .then(() => addProductForm.reset())
        .catch(console.warn);
});

function getProducts() {
    fetch(PRODUCTS_API_URL)
        .then(processOkResponse)
        .then(products => products.forEach(createProduct));
}

function createProduct(product) {
    const container = document.createElement('div');
    container.classList.add('product');

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

    container.append(hName);
    container.append(pQuantity);
    container.append(checkbox)
    container.append(button);

    document.getElementById('allProducts').append(container)
}

function processOkResponse(response = {}) {
    if (response.ok) {
        return response.json();
    }
    throw new Error(`Status not 200 (${response.status})`);
}
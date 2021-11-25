document.addEventListener("DOMContentLoaded", () => {

    const getMostSoldProduct = async() => {
        const productBox = document.querySelector("#product");

        try {
            productBox.innerHTML = `
            <div class="spinner-border text-light" role="status"></div>
            `
            const url = "/tickets/most-sold-product"
            const response = await fetch(url);
            const product = await response.json();
console.log(response, product);
            productBox.innerHTML = '';

            productBox.innerHTML += `
            <div class="card text-white bg-dark mb-3">
                <div class="card-body">
                    <h5 class="card-title">Nombre: ${product.name}</h5>
                    <h5 class="card-title">Monto total: $${product.amount}</h5>
                    <h5 class="card-title">Cantidad: ${product.quantity}</h5>
                    <h5 class="card-title">Precio por unidad: $${product.pricePerUnit}</h5>
                </div>
            </div>
            `   
        } catch (response) {
            console.log("Algo falló.");
        }
    }

    (async () => {
        try {
            await getMostSoldProduct();
        } catch (e) {
        }
    })();
})
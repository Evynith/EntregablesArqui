document.addEventListener("DOMContentLoaded", () => {

    const getMostSoldProduct = async() => {
        const productBox = document.querySelector("#product");

        try {
            productBox.innerHTML = `
            <div class="spinner-border text-light" role="status"></div>
            `
            const url = "/tickets/most-sold-product"
            const response = await fetch(url);
            const {name, amount, quantity, prizePerUnit} = await response.json();
            productBox.innerHTML = '';

            // product.forEach(({name, amount, quantity, prizePerUnit}) => {
            //     productBox.innerHTML += `
            //     <div class="card text-white bg-dark mb-3">
            //         <div class="card-body">
            //             <h5 class="card-title">Nombre: ${name}</h5>
            //             <h5 class="card-title">Monto total: ${amount}</h5>
            //             <h5 class="card-title">Cantidad: ${quantity}</h5>
            //             <h5 class="card-title">Precio por unidad: ${prizePerUnit}</h5>
            //         </div>
            //     </div>
            //     `   
            // })
            productBox.innerHTML += `
            <div class="card text-white bg-dark mb-3">
                <div class="card-body">
                    <h5 class="card-title">Nombre: ${name}</h5>
                    <h5 class="card-title">Monto total: $${amount}</h5>
                    <h5 class="card-title">Cantidad: ${quantity}</h5>
                    <h5 class="card-title">Precio por unidad: $${prizePerUnit}</h5>
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
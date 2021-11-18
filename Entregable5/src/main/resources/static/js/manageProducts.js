document.addEventListener("DOMContentLoaded", () => {

    const getProducts = async() => {
        const productList = document.querySelector("#product-list");

        try {
            productList.innerHTML = `
            <div class="spinner-border text-light" role="status"></div>
            `
            const url = "/products"
            const response = await fetch(url);
            const products = await response.json();
            productList.innerHTML = '';

            products.forEach(({id, name, price, stock}) => {
                productList.innerHTML += `
                <div class="d-flex flex-column justify-content-center align-items-center mt-5">
                    <form method="post" class="bg-dark text-white rounded p-4">
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Nombre del producto</label>
                            <input type="text" class="form-control" aria-describedby="emailHelp" name="name" value="${name}" data-name="${id}">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Precio por unidad</label>
                            <input type="number" class="form-control" name="price" value="${price}" data-price="${id}">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Cantidad de stock</label>
                            <input type="number" class="form-control" name="stock" value="${stock}" data-stock="${id}">
                        </div>
                        <div class="d-flex justify-content-around">
                            <button type="submit" class="btn btn-success modifyProduct" data-btn="${id}">Modificar</button>
                            <button type="reset" class="btn btn-primary">Restablecer</button>
                        </div>
                        <button class="btn btn-danger deleteProduct" data-btn="${id}">Eliminar</button>
                        <p class="status mt-3" data-status="${id}"></p>
                    </form>
                </div>
                `  
            })	
        } catch (response) {
            console.log("Algo falló.");
        }
    }

    const deleteProduct = async(e) => {
        e.preventDefault();
        const status = document.querySelector(`[data-status="${e.target.dataset.btn}"]`);
        setStatusMessage(status, "Eliminando producto..", "bg-danger");
        const id = e.target.dataset.btn;
        try {
			const url = `/products/${id}`;
            const response = await fetch(url, {
                "method": "DELETE",
                "headers": {"Content-Type": "application/json"}
            });
            main();
		} catch (response) {
            setStatusMessage(status, "No se ha podido eliminar.", "bg-danger");
        }
    }

    const modifyProduct = async(e) => {
        e.preventDefault();
        const status = document.querySelector(`[data-status="${e.target.dataset.btn}"]`);
        setStatusMessage(status, "Modificando producto..", "bg-primary");
        const id = document.querySelector(`[data-btn="${e.target.dataset.btn}"]`).dataset.btn;
        const data = {
            "name": document.querySelector(`[data-name="${e.target.dataset.btn}"]`).value,
            "price": document.querySelector(`[data-price="${e.target.dataset.btn}"]`).value,
            "stock": document.querySelector(`[data-stock="${e.target.dataset.btn}"]`).value,
        }
        try {
			const url = `/products/${id}`;
            const response = await fetch(url, {
                "method": "PUT",
                "headers": {"Content-Type": "application/json"},
                "body": JSON.stringify(data) 
            });
            main();
		} catch (response) {
            setStatusMessage(status, "No se ha podido modificar.", "bg-danger");
        }
    }

    const setStatusMessage = (status, msg, style) => {
        status.className = '';
        status.innerHTML = msg;
        status.classList.add(style);
        status.classList.add("mt-3");
        status.classList.add("font-weight-bold");
        status.classList.add("text-white");
    }

    const main = async() => {
        await getProducts();
        const modifyBtns = document.querySelectorAll(".modifyProduct");
        modifyBtns.forEach((btn) => {
            btn.addEventListener("click", modifyProduct)
        })
    
        const deleteBtns = document.querySelectorAll(".deleteProduct");
        deleteBtns.forEach((btn) => {
            btn.addEventListener("click", deleteProduct)
        })
    }

    (async () => {
        try {
            await main();
        } catch (e) {
        }
    })();

})
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

            products.forEach(({name, prize}, i) => {
                i++;
                productList.innerHTML += `
                <div class="card text-white bg-dark mb-3">
                    <div class="card-body">
                        <h5 class="card-title">${name}</h5>
                        <p class="card-text">Precio por unidad: $${prize}</p>
                        <div class="d-flex">
                            <button type="button" class="btn btn-danger btn-sm remove" data-remove="${i}">-</button>
                                <div class="input-group input-group-sm">
                                    <input type="number" value="0" data-input="${i}" class="form-control text-center" aria-label="Small" aria-describedby="inputGroup-sizing-sm"/>
                                </div>
                            <button type="button" class="btn btn-success btn-sm add" data-sum="${i}">+</button>
                        </div>
                        <a href="#" class="btn btn-success mt-3 addProduct" data-addCart="${i}">Añadir</a>
                    </div>
                </div>
                `   
            })	
        } catch (response) {
            console.log("Algo falló.");
        }
    }

    const getCart = async(id) => {
        const cartList = document.querySelector("#cart-list");

        try {
            cartList.innerHTML = `
            <div class="spinner-border text-light" role="status"></div>
            `
            const url = `/tickets/cart/${id}`
            const response = await fetch(url);
            const products = await response.json();
            cartList.innerHTML = '';

            let totalAmount = 0;
            products.forEach(({id, name, quantity, amount}) => {
                totalAmount += amount;
                cartList.innerHTML += `
                <div class="card text-white bg-dark mb-3">
                    <div class="card-body">
                        <h5 class="card-title">${name}</h5>
                        <p class="card-text">Cantidad: ${quantity}</p>
                        <p class="card-text">Precio: $${amount}</p>
                        <a href="#" class="btn btn-danger cartRemove" dataset-product-id="${id}">Quitar 1</a>
                    </div>
                </div> 
                `   
            })
            cartList.innerHTML += `   
            <p class="text-success text-center" style="font-weight: bold">Monto total: $${totalAmount}</p>
            `   
        } catch (response) {
            console.log("Algo falló.");
        }
    }

    // const removeProduct = async(id) => {
    //     const cartList = document.querySelector("#cart-list");
    //     console.log(id);
    //     try {
    //         cartList.innerHTML = `
    //         <div class="spinner-border text-light" role="status"></div>
    //         `
    //         const data = {
    //             "quantity": 1,
    //             "product": document.querySelector(`[data-product-id="${id}"]`).dataset.productId
    //         }
    //         const url = `/tickets/cart/${id}`
    //         const response = await fetch(url, {
    //             "method": "PUT",
    //             "headers": {"Content-Type": "application/json"},
    //             "body": JSON.stringify(data) 
    //         });
    //         main();  
    //     } catch (response) {
    //         console.log("Algo falló.");
    //     }
    // }

    const addProduct = async() => {

    }

    const main = async() => {
        const id = 21;
        await getProducts();
        await getCart(id);
        // const removeBtns = document.querySelectorAll(".cartRemove");
        // removeBtns.forEach((btn) => {
        //     btn.addEventListener("click", (e) => {
        //         e.preventDefault();
        //         removeProduct(id);
        //     });
        // })
        const addProductBtns = document.querySelectorAll(".addProduct");
        addProductBtns.forEach((btn) => {
            btn.addEventListener("click", (e) => {
                e.preventDefault();
                addProduct();
            })
        })

        const sumBtns = document.querySelectorAll(".add");
        sumBtns.forEach((btn) => {
            btn.addEventListener("click", (e) => {
                const input = document.querySelector(`[data-input="${e.target.dataset.sum}"]`);
                input.value++;
            })
        })

        const removeBtns = document.querySelectorAll(".remove");
        removeBtns.forEach((btn) => {
            btn.addEventListener("click", (e) => {
                const input = document.querySelector(`[data-input="${e.target.dataset.remove}"]`);
                if (input.value > 0)
                    input.value--;
            })
        })
    }

    (async () => {
        try {
            main();
        } catch (e) {
        }
    })();
})
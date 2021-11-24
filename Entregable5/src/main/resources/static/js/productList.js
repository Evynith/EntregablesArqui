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

            products.forEach(({id, name, price}, i) => {
                i++;
                productList.innerHTML += `
                <div class="card text-white bg-dark mb-3">
                    <div class="card-body">
                        <h5 class="card-title">${name}</h5>
                        <p class="card-text">Precio por unidad: $${price}</p>
                        <div class="d-flex">
                            <button type="button" class="btn btn-danger btn-sm remove" data-remove="${i}">-</button>
                                <div class="input-group input-group-sm">
                                    <input type="number" value="0" data-input="${i}" data-name="${name}" data-id="${id}" class="form-control text-center" aria-label="Small" aria-describedby="inputGroup-sizing-sm"/>
                                </div>
                            <button type="button" class="btn btn-success btn-sm add" data-sum="${i}">+</button>
                        </div>
                    </div>
                </div>
                `   
            })	
        } catch (response) {
            console.log("Algo falló.");
        }
    }

    const getClients = async() => {
        const clientList = document.querySelector("#client-list");

        // try {
            clientList.innerHTML = `
            <div class="spinner-border text-light" role="status"></div>
            `
            const url = "/clients";
            const response = await fetch(url);
            const clients = await response.json();
            clientList.innerHTML = '';

            clients.forEach(({id, name, surname}) => {
                let sel = document.createElement("option");
                sel.setAttribute("value", id);
                sel.innerHTML += `${surname}, ${name}` 
                clientList.appendChild(sel);
            })	
        // } catch (response) {
        //     console.log("Algo falló.");
        // }
    }

    const buy = async(e) => {
        e.preventDefault();
        const data = {
            "client": {
                "id": document.querySelector("#client-list").value
            },
            "products": []
        }

        console.log("cli:",  document.querySelector("#client-list").value);
        let isProducts = false;
        let isClient = false;
        const productInputs = document.querySelectorAll("input");
        productInputs.forEach((input) => {
            if (input.value != 0) {
                isProducts = true;
                const product = {
                    "id": input.dataset.id,
                    "quantity": input.value
                }
                data.products.push(product);
            }
        })
        if(data.client.id != null){
            isClient = true;
        }

        if (isProducts && isClient) {
            try {
                const url = `/tickets`
                const response = await fetch(url, {
                    "method": "POST",
                    "headers": {"Content-Type": "application/json"},
                    "body": JSON.stringify(data) 
                });
            } catch (response) {
                console.log("Algo falló.");
            }
        }
    }

    const checkSum = (e) => {
        e.preventDefault();
        const input = document.querySelector(`[data-input="${e.target.dataset.sum}"]`);
        if (input.value < 3)
            input.value++;

        input.addEventListener("change", () => {
            if (input.value > 3)
                input.value = 0;
        })  
    }

    const checkRemove = (e) => {
        e.preventDefault();
        const input = document.querySelector(`[data-input="${e.target.dataset.remove}"]`);
        if (input.value > 0) 
            input.value--;

        input.addEventListener("change", () => {
            if (input.value < 0)
                input.value = 0;
        })
    }

    const main = async() => {
        await getProducts();
        await getClients();

        const sumBtns = document.querySelectorAll(".add");
        sumBtns.forEach((btn) => {
            btn.addEventListener("click", (e) => {
                checkSum(e);
            })
        })

        const removeBtns = document.querySelectorAll(".remove");
        removeBtns.forEach((btn) => {
            btn.addEventListener("click", (e) => {
                checkRemove(e);
            })
        })

        document.querySelector("#buy").addEventListener("click", buy);
    }

    (async () => {
        try {
            main();
        } catch (e) {
        }
    })();
})
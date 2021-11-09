document.addEventListener("DOMContentLoaded", () => {

    const addProduct = async(product, status) => {
        setStatusMessage(status, "Agregando producto..", "bg-primary");
        try {
			const url = '/products';
            const response = await fetch(url, {
                "method": "POST",
                "headers": {"Content-Type": "application/json"},
                "body": JSON.stringify(product) 
            });
            setStatusMessage(status, "Se ha agregado correctamente.", "bg-success");
		} catch (response) {
            setStatusMessage(status, "No se ha podido agregar.", "bg-danger");
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

    document.querySelector("#addProduct").addEventListener("click", (e) => {
        e.preventDefault();
        const status = document.querySelector("#status");
        const name = document.querySelector("input[name=name]").value;
        const price = document.querySelector("input[name=price]").value;
        const stock = document.querySelector("input[name=stock]").value;

        if (name != '' && price != 0 && stock != 0) 
            addProduct({name, price, stock}, status);
        else 
            setStatusMessage(status, "Te faltan rellenar datos", "bg-danger");
    });
})
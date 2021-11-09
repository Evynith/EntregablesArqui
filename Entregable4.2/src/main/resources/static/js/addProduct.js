document.addEventListener("DOMContentLoaded", () => {

    const addProduct = async (e) => {
        e.preventDefault();
        const status = document.querySelector("#status");
        setStatusMessage(status, "Agregando producto..", "bg-primary");
        const data = {
            "name": document.querySelector("input[name=name]").value,
            "prize": document.querySelector("input[name=prize]").value,
            "stock": document.querySelector("input[name=stock]").value,
        }
        
        try {
			const url = '/products';
            const response = await fetch(url, {
                "method": "POST",
                "headers": {"Content-Type": "application/json"},
                "body": JSON.stringify(data) 
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

    document.querySelector("#addProduct").addEventListener("click", addProduct);
})
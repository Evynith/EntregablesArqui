document.addEventListener("DOMContentLoaded", () => {

    const addClient = async(clientData, status) => {
        setStatusMessage(status, "Agregando cliente..", "bg-primary");
        try {
			const url = '/clients';
            const response = await fetch(url, {
                "method": "POST",
                "headers": {"Content-Type": "application/json"},
                "body": JSON.stringify(clientData) 
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

    document.querySelector("#addClient").addEventListener("click", (e) => {
        e.preventDefault();
        const status = document.querySelector("#status");
        const name = document.querySelector("input[name=name]").value;
        const surname = document.querySelector("input[name=surname]").value;

        if (name != '' && surname != '') 
            addClient({name, surname}, status);
        else 
            setStatusMessage(status, "Te faltan rellenar datos", "bg-danger");
    });
})
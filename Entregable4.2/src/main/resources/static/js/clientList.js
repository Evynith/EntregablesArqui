document.addEventListener("DOMContentLoaded", () => {

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
                clientList.innerHTML += `
                <div class="d-flex flex-column justify-content-center align-items-center mt-5">
                    <form method="post" class="bg-dark text-white rounded p-4">
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Nombre del producto</label>
                            <input type="text" class="form-control" aria-describedby="emailHelp" name="name" value="${name}" data-name="${id}">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Precio por unidad</label>
                            <input type="text" class="form-control" name="surname" value="${surname}" data-surname="${id}">
                        </div>
                        <div class="d-flex justify-content-around">
                            <button type="submit" class="btn btn-success modifyClient" data-btn="${id}">Modificar</button>
                            <button type="reset" class="btn btn-primary">Restablecer</button>
                        </div>
                        <button class="btn btn-danger deleteClient" data-btn="${id}">Eliminar</button>
                        <p class="status mt-3" data-status="${id}"></p>
                    </form>
                </div>
                `  
            })	
        // } catch (response) {
        //     console.log("Algo falló.");
        // }
    }

    const deleteClient = async(e) => {
        e.preventDefault();
        const status = document.querySelector(`[data-status="${e.target.dataset.btn}"]`);
        setStatusMessage(status, "Eliminando cliente..", "bg-danger");
        const id = e.target.dataset.btn;
        try {
			const url = `/clients/${id}`;
            const response = await fetch(url, {
                "method": "DELETE",
                "headers": {"Content-Type": "application/json"}
            });
            main();
		} catch (response) {
            setStatusMessage(status, "No se ha podido eliminar.", "bg-danger");
        }
    }

    const modifyClient = async(e) => {
        e.preventDefault();
        const status = document.querySelector(`[data-status="${e.target.dataset.btn}"]`);
        setStatusMessage(status, "Modificando cliente..", "bg-primary");
        const id = document.querySelector(`[data-btn="${e.target.dataset.btn}"]`).dataset.btn;
        const data = {
            "name": document.querySelector(`[data-name="${e.target.dataset.btn}"]`).value,
            "surname": document.querySelector(`[data-surname="${e.target.dataset.btn}"]`).value,
        }
        try {
			const url = `/clients/${id}`;
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
        await getClients();
        const modifyBtns = document.querySelectorAll(".modifyClient");
        modifyBtns.forEach((btn) => {
            btn.addEventListener("click", modifyClient)
        })
    
        const deleteBtns = document.querySelectorAll(".deleteClient");
        deleteBtns.forEach((btn) => {
            btn.addEventListener("click", deleteClient)
        })
    }

    (async () => {
        try {
            await main();
        } catch (e) {
        }
    })();

})
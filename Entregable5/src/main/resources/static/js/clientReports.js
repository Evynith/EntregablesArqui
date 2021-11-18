document.addEventListener("DOMContentLoaded", () => {

    const getClientReports = async() => {
        const clientList = document.querySelector("#client-list");

        try {
            clientList.innerHTML = `
            <div class="spinner-border text-light" role="status"></div>
            `
            const url = "/tickets/client-reports"
            const response = await fetch(url);
            const clients = await response.json();
            clientList.innerHTML = '';

            clients.forEach(({name, surname, amount}) => {
                clientList.innerHTML += `
                <div class="card text-white bg-dark mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Nombre: ${name}</h5>
                        <h5 class="card-title">Apellido: ${surname}</h5>
                        <h5 class="card-title">Monto total: $${amount}</h5>
                    </div>
                </div>
                `   
            })	
        } catch (response) {
            console.log("Algo falló.");
        }
    }

    (async () => {
        try {
            await getClientReports();
        } catch (e) {
        }
    })();
})
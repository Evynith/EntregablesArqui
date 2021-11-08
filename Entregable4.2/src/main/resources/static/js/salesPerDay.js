document.addEventListener("DOMContentLoaded", () => {

    const getSalesPerDay = async() => {
        const salesPerDay = document.querySelector("#sales-per-day-list");

        try {
            salesPerDay.innerHTML = `
            <div class="spinner-border text-light" role="status"></div>
            `
            const url = "/tickets/sales-per-day"
            const response = await fetch(url);
            const sales = await response.json();
            salesPerDay.innerHTML = '';

            sales.forEach(({day, name, quantity, amount}) => {
                salesPerDay.innerHTML += `
                <div class="card text-white bg-dark mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Día: ${day}</h5>
                        <h5 class="card-title">Nombre: ${name}</h5>
                        <h5 class="card-title">Cantidad: $${quantity}</h5>
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
            await getSalesPerDay();
        } catch (e) {
        }
    })();
})
document.addEventListener("DOMContentLoaded", () => {

    document.querySelector("#buscarBtn").addEventListener("click", async(e) => {
        e.preventDefault();
        if (document.querySelector("#table").classList.contains("d-none"))  
        	document.querySelector("#table").classList.remove("d-none");
        
        await getEstudiantePorLibreta();
    });

	const getEstudiantePorLibreta = async() => {
        const tableBody = document.querySelector("#bodyTable");
        tableBody.innerHTML = '';
        const numLibreta = document.querySelector("#libreta").value;
        try {
			const url = `api/estudiantes/libreta/${numLibreta}`;
            const response = await fetch(url);
			const {dni, libreta, nombre, apellido, genero, edad, ciudad} = await response.json();

            tableBody.innerHTML +=
                `<tr>
                    <td>${JSON.stringify(dni)}</td>
                    <td>${JSON.stringify(libreta)}</td>
                    <td>${JSON.stringify(nombre)}</td>
                    <td>${JSON.stringify(apellido)}</td>
                    <td>${JSON.stringify(genero)}</td>
                    <td>${JSON.stringify(edad)}</td>
                    <td>${JSON.stringify(ciudad)}</td>
                </tr>`

		} catch (response) {
			console.log("Algo falló.");
		}
	}
});
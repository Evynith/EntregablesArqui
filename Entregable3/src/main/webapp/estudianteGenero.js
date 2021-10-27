document.addEventListener("DOMContentLoaded", () => {

    document.querySelector("#buscarBtn").addEventListener("click", async(e) => {
        e.preventDefault();
        if (document.querySelector("#table").classList.contains("d-none"))  
            document.querySelector("#table").classList.remove("d-none");
        
        await getEstudiantePorGenero();
    });

	const getEstudiantePorGenero = async() => {
        const tableBody = document.querySelector("#bodyTable");
        tableBody.innerHTML = '';
        const genero = document.querySelector("#genero").value;
        try {
			const url = `api/estudiantes/genero/${genero}`;
            const response = await fetch(url);
			const estudiantes = await response.json();

            estudiantes.forEach(({dni, libreta, nombre, apellido, genero, edad, ciudad}, i) => {
                i++;
                tableBody.innerHTML +=
                    `<tr>
                        <td>${i}</td>
                        <td>${JSON.stringify(dni)}</td>
                        <td>${JSON.stringify(libreta)}</td>
                        <td>${JSON.stringify(nombre)}</td>
                        <td>${JSON.stringify(apellido)}</td>
                        <td>${JSON.stringify(genero)}</td>
                        <td>${JSON.stringify(edad)}</td>
                        <td>${JSON.stringify(ciudad)}</td>
                    </tr>`
            })

		} catch (response) {
			console.log("Algo falló.");
		}
	}
});
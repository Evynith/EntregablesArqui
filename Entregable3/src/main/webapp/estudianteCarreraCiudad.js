document.addEventListener("DOMContentLoaded", () => {

    document.querySelector("#buscarBtn").addEventListener("click", async(e) => {
        e.preventDefault();
        if (document.querySelector("#table").classList.contains("d-none"))  
            document.querySelector("#table").classList.remove("d-none");
        
        await getEstudianteCarreraCiudad();
    });

    const getEstudianteCarreraCiudad = async() => {
        const tableBody = document.querySelector("#bodyTable");
        tableBody.innerHTML = '';
        const carrera = document.querySelector("#carrera").value;
        const ciudad = document.querySelector("#ciudad").value;
        try {
			const url = `api/carreras/carrera-ciudad/${carrera}/${ciudad}`;
            const response = await fetch(url);
			const estudiantes = await response.json();

            estudiantes.forEach(({estudiante}, i) => {
                i++;
                tableBody.innerHTML +=
                    `<tr>
                        <td>${i}</td>
                        <td>${JSON.stringify(carrera)}</td>
                        <td>${JSON.stringify(estudiante.dni)}</td>
                        <td>${JSON.stringify(estudiante.libreta)}</td>
                        <td>${JSON.stringify(estudiante.nombre)}</td>
                        <td>${JSON.stringify(estudiante.apellido)}</td>
                        <td>${JSON.stringify(estudiante.genero)}</td>
                        <td>${JSON.stringify(estudiante.edad)}</td>
                        <td>${JSON.stringify(estudiante.ciudad)}</td>
                    </tr>`
            })

		} catch (response) {
			console.log("Algo falló.");
		}
	}
})
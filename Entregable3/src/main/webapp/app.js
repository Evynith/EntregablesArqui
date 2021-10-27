document.addEventListener("DOMContentLoaded", () => {

    document.querySelector("#reporteBtn").addEventListener("click", async(e) => {
		e.preventDefault();
		document.querySelector("#reportTable").classList.toggle("d-none");
		reporteBtn.innerHTML = checkTextBtn(reporteBtn.innerHTML);
        await getReporte();
    });

	document.querySelector("#orderedStudentsBtn").addEventListener("click", async(e) => {
		e.preventDefault();
		document.querySelector("#orderedStudentsTable").classList.toggle("d-none");
		orderedStudentsBtn.innerHTML = checkTextBtn(orderedStudentsBtn.innerHTML);
		await getEstudiantesOrdenados();
	});

	document.querySelector("#orderedCareersBtn").addEventListener("click", async(e) => {
		e.preventDefault();
		document.querySelector("#orderedCareersTable").classList.toggle("d-none");
		orderedCareersBtn.innerHTML = checkTextBtn(orderedCareersBtn.innerHTML);
		await getCarrerasOrdenados();
	});

	const getCarrerasOrdenados = async() => {
		try {
			const url = "api/carreras/con-estudiantes-ordenados"
            const response = await fetch(url);
			const carreras = await response.json();
			const tableBody = document.querySelector("#orderedCareersBody");
			tableBody.innerHTML = '';

			carreras.forEach(({carrera, cantidadEstudiantes}, i) => {
				i++;
				tableBody.innerHTML +=
				 	`<tr>
					 	<th scope='row'>${i}</th>
					    <td>${JSON.stringify(carrera)}</td>
					   	<td>${JSON.stringify(cantidadEstudiantes)}</td>
					 </tr>`
       	 	})	
		} catch (response) {
			console.log("Algo falló.");
		}
	}

	const getEstudiantesOrdenados = async() => {
		try {
			const url = "api/estudiantes"
            const response = await fetch(url);
			const carreras = await response.json();
			const tableBody = document.querySelector("#orderedStudentsBody");
			tableBody.innerHTML = '';

			carreras.forEach(({nombre, apellido, edad, genero}, i) => {
				i++;
				tableBody.innerHTML +=
				 	`<tr>
					 	<th scope='row'>${i}</th>
					    <td>${JSON.stringify(nombre)}</td>
					   	<td>${JSON.stringify(apellido)}</td>
					   	<td>${JSON.stringify(edad)}</td>
						<td>${JSON.stringify(genero)}</td>
					 </tr>`
       	 	})	
		} catch (response) {
			console.log("Algo falló.");
		}
	}

    const getReporte = async() => {
        try {
			const url = "api/carreras/reporte"
            const response = await fetch(url);
			const carreras = await response.json();
			const tableBody = document.querySelector("#reportTableBody");
			tableBody.innerHTML = '';

			carreras.forEach(({carrera, anio, inscriptos, egresados}, i) => {
				i++;
				tableBody.innerHTML +=
				 	`<tr>
					 	<th scope='row'>${i}</th>
					    <td>${JSON.stringify(carrera)}</td>
					   	<td>${JSON.stringify(anio)}</td>
					   	<td>${JSON.stringify(inscriptos)}</td>
						<td>${JSON.stringify(egresados)}</td>
					 </tr>`
       	 	})	
        } catch (response) {
            console.log("Algo falló.");
        }
    }

	const checkTextBtn = (text) => {
		return text == "Ver" ? "Ocultar" : "Ver"; 
	}

});
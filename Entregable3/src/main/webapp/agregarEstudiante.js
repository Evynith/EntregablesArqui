document.addEventListener("DOMContentLoaded", () => {
    
	const agregarEstudiante = async(e) => {
        e.preventDefault();
        const status = document.querySelector("#status");
        setStatusMessage(status, "Agregando..", "bg-primary");
        const data = {
            "nombre": document.querySelector("input[name=nombre]").value,
            "apellido": document.querySelector("input[name=apellido]").value,
            "dni": document.querySelector("input[name=dni]").value,
            "libreta": document.querySelector("input[name=libreta]").value,
            "genero": document.querySelector("select[name=genero]").value,
            "edad": document.querySelector("input[name=edad]").value,
            "ciudad": document.querySelector("input[name=ciudad]").value,
        }

        try {
			const url = `api/estudiantes`;
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

    document.querySelector("#agregarBtn").addEventListener("click", agregarEstudiante);
});
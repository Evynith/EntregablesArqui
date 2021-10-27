document.addEventListener("DOMContentLoaded", () => {
    
	const matricularEstudiante = async(e) => {
        e.preventDefault();
        const status = document.querySelector("#status");
        setStatusMessage(status, "Matriculando..", "bg-primary");
        const data = {
            "carrera": document.querySelector("select[name=carrera]").value,
            "libreta": document.querySelector("input[name=libreta]").value,
        }

        try {
			const url = `api/carreras`;
            const response = await fetch(url, {
                "method": "POST",
                "headers": {"Content-Type": "application/json"},
                "body": JSON.stringify(data) 
            });
            setStatusMessage(status, "Se ha matriculado correctamente.", "bg-success");
		} catch (response) {
            setStatusMessage(status, "No se ha podido matricular.", "bg-danger");
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

    document.querySelector("#matricularBtn").addEventListener("click", matricularEstudiante);
});
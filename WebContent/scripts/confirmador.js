function confirmar(id){
	let resposta = confirm("Confirma a exclusão desse contato ?")
	if(resposta == true){
		window.location.href = "delete?id=" + id
	}
}
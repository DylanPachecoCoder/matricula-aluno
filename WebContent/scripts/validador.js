function validar(){
	let nome = frmAluno.nome.value
	let fone = frmAluno.fone.value
	
	if(nome === ""){
		alert("Preencha o campo Nome")
		frmAluno.nome.focus()
		return false
	}else if(fone === ""){
		alert("Preencha o campo Fone")
		frmAluno.fone.focus()
		return false
	}else{
		document.forms["frmAluno"].submit()
	}
}
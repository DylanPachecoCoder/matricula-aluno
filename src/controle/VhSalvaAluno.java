package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Aluno;
import dominio.Cidade;
import dominio.Curso;
import dominio.Documento;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Periodo;
import dominio.Semestre;
import dominio.SemestreEnum;
import dominio.TipoDocumento;
import dominio.TipoEndereco;

public class VhSalvaAluno implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
//		Professor professor = new Professor("Rodrigo", "08/12/1995", endereco, null, "qualificacao");
//		Materia materia = new Materia("ES3", null, StatusMateria.ANDAMENTO, semestre, professor);
//		List<Materia> materias = new ArrayList<Materia>();
//		materias.add(materia);
		
//		Estado estado = new Estado("SP");
//		Cidade cidade = new Cidade("Poá", estado);
//		Endereco endereco = new Endereco("casa", 366, "08560050", "", cidade, TipoEndereco.CASA);
//		Semestre semestre = new Semestre("2021", "1");
//		Documento documento = new Documento("", new Date(), TipoDocumento.CPF);
//		ArrayList<Documento> documentos = new ArrayList<>();
//		documentos.add(documento);
//		Curso curso = new Curso("ADS", "");
//		Aluno aluno = new Aluno("Dylan", "08/12/1995", endereco, "123456", "654321", semestre, curso);
		
		
		Estado estado = new Estado(request.getParameter("estado"));
		Cidade cidade = new Cidade(request.getParameter("cidade"), estado);
		Endereco endereco = new Endereco();
		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setCep(request.getParameter("cep"));
		endereco.setNumero(Integer.valueOf(request.getParameter("numero")));
		endereco.setComplemento(request.getParameter("complemento"));
		endereco.setCidade(cidade);
		endereco.setTpEndereco(request.getParameter("tipoEndereco"));
		
		Curso curso = new Curso();
		curso.setDescricao(request.getParameter("curso"));
		curso.setPeriodo(request.getParameter("periodo"));
		
		Semestre semestre = new Semestre();
		semestre.setAno(request.getParameter("Ano"));
		semestre.setSemestreEnum(request.getParameter("semestre"));
		
		
		Aluno aluno = new Aluno();
		aluno.setNome(request.getParameter("nome"));
		aluno.setDataNascimento(request.getParameter("dt_Nasc"));
		aluno.setCpf(request.getParameter("cpf"));
		aluno.setRg(request.getParameter("rg"));
		aluno.setSemestreInicial(semestre);
		aluno.setEndereco(endereco);
		aluno.setCurso(curso);
		
		return aluno;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		PrintWriter out;
		try {
			out = response.getWriter();
			if (resultado != null) {
				out.println(resultado);
			} else {
				response.sendRedirect("main");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}

}

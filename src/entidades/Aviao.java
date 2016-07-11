package entidades;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Aviao {

	private String 	id,
					empresa,
					origem,
					destino,
					tempo,
					problemaGasol,
					problemaMec
					;
	
	public Aviao(String id, String empresa, String origem, String destino, String problemaGasol, String problemaMec) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.origem = origem;
		this.destino = destino;
		
		this.problemaGasol = problemaGasol;
		this.problemaMec = problemaMec;
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        setTempo(sdf.format(cal.getTime()));
        
    
	}
	
	public Aviao() {
		super();
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String isProblemaGasol() {
		return problemaGasol;
	}
	public void setProblemaGasol(String problemaGasol) {
		this.problemaGasol = problemaGasol;
	}
	public String isProblemaMec() {
		return problemaMec;
	}
	public void setProblemaMec(String problemaMec) {
		this.problemaMec = problemaMec;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

}

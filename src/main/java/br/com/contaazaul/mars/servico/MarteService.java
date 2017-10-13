package br.com.contaazaul.mars.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MarteService implements Serializable{
	private static final long serialVersionUID = -6666997767061739238L;
	
	public MarteService(){
		this.largura=5;
		this.comprimento=5;
	}
	
	public String moverRobo(String comando){
		String valido = comando.toUpperCase();
		if(!validarComando(valido)){
			return null;
		}
		
		if(comandos.isEmpty()  || ! valido.equals(comandos.get(comandos.size()-1)))
			comandos.add(valido);
		
		char direcaoOlho='N';
		int posicaoX=0;
		int posicaoY=0;
		for(int x = 0; x <comandos.size();x++){
			char lista[] = comandos.get(x).toCharArray();
			boolean caminhar = false;
			for(int i = 0;i < lista.length;i++){
				char comandoAtual = lista[i];
				caminhar = (comandoAtual == 'M');
				direcaoOlho=mudarOlho(comandoAtual,direcaoOlho);
				
				if(caminhar){
					if(direcaoOlho=='W')
						posicaoX--;
					if(direcaoOlho=='E')
						posicaoX++;
					if(direcaoOlho=='N')
						posicaoY++;
					if(direcaoOlho=='S')
						posicaoY--;
				}

				if(posicaoY < 0 || posicaoX < 0)
					return null;
				if(posicaoY > this.comprimento || posicaoX > this.largura)
					return null;
			}			
		}
		return "("+posicaoX+","+posicaoY+","+direcaoOlho+")";
	}
	private char mudarOlho(char comandoAtual,char olho){
		if(comandoAtual =='L'){
			if(olho=='N')
				return 'W';
			if(olho=='S')
				return 'E';
			if(olho=='E')
				return 'N';
			if(olho=='W')
				return 'S';
		}else if(comandoAtual =='R'){
			if(olho=='N')
				return 'E';
			if(olho=='S')
				return 'W';
			if(olho=='E')
				return 'S';
			if(olho=='W')
				return 'N';
		}
		return olho;
	}
	private boolean validarComando(String comando){
		if(comando == null || comando.isEmpty())
			return false;
		char lista[] = comando.toCharArray();
		boolean valido;
		int qtdM = 0;
		for(int i=0; i < lista.length; i++){
			valido = false;
			if(lista[i]=='L'){
				valido = true;
				qtdM=0;
			}
			if(lista[i]=='R'){
				valido = true;
				qtdM=0;
			}
			if(lista[i]=='M'){
				valido = true;
				qtdM++;
			}
			if(qtdM > this.largura || qtdM > this.comprimento){
				return false;
			}
			if(!valido)
				return false;
		}
		return true;
	}
	private Integer largura;
	private Integer comprimento;
	private List<String> comandos = new ArrayList<String>();
}

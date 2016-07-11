package entidades;
import entidades.*;
import java.util.*;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TableModelDecolagem extends AbstractTableModel {

		public  List<Aviao> linhasDecolagens;
		private String[] colunasDecolagens = new String[] {"Voo",
				"Empresa", "Origem", "Destino", "Espera", "Prob.Gas", "Prob.Mec"};

		private List<Aviao> teste;
		
		//###### METHODS ###############
		
		public void addDecolagem(Aviao aviao) {

			this.linhasDecolagens.add(aviao);
			fireTableDataChanged();
        }
		
		public void removeDecolagem(int indiceLinha) {
                this.linhasDecolagens.remove(indiceLinha);
                fireTableDataChanged();
        }
		
		public Aviao getDecolagem(int rowIndex){
			return this.linhasDecolagens.get(rowIndex);
		}
		
		
		
		
		public List<Aviao> findAll(){
			return linhasDecolagens;
		}
		public int getRowIndex(Aviao aviao){
			
			teste = new ArrayList<Aviao>();
			teste.add(aviao);
			int index = 0;
			for (Aviao plane1 : teste){
				for (Aviao plane2 : findAll()){
					if (plane1.equals(plane2)){
							return index;			
					}
				}
					index++;
			}	
			return 1000;
		}
		public Aviao procurarId(String id){
			for(Aviao aviao : findAll())
				if (aviao.getId().equals(id))
					return aviao;
			return null;
		}
		
        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public int getRowCount() {
            return linhasDecolagens.size();
        }

        @Override
        public String getColumnName(int columnIndex) {

            return colunasDecolagens[columnIndex];
        }


        
        @Override
		public Object getValueAt(int rowIndex, int columnIndex) {

            switch (columnIndex) {
                case 0:
                    return linhasDecolagens.get(rowIndex).getId();
                case 1:
                    return linhasDecolagens.get(rowIndex).getEmpresa();
                case 2:
                    return linhasDecolagens.get(rowIndex).getOrigem();
                case 3:
                    return linhasDecolagens.get(rowIndex).getDestino();
                case 4:
                    return linhasDecolagens.get(rowIndex).getTempo();
                case 5:
                	return linhasDecolagens.get(rowIndex).isProblemaGasol();
                case 6:
                	return linhasDecolagens.get(rowIndex).isProblemaMec();
                default:
                    return null;
            }	
		}

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {

                case 0:
                    return String.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return String.class;
                case 4:
                    return String.class;
                case 5:
                    return String.class;
                case 6:
                    return String.class;
                default:

                    return null;
            }
        }

		
		

        

        public void addListaDePostos(List<Aviao> aviao) {
            //int tamanhoAntigo = getRowCount();
            linhasDecolagens.addAll(aviao);
            fireTableDataChanged();
            //fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
        }
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
        	if (columnIndex<5){
        		return false;
        	}
        	else{
        		return true;	
        	}
            
        }
        public void limpar() {
            linhasDecolagens.clear();
            fireTableDataChanged();
        }

        public boolean isEmpty() {
            return linhasDecolagens.isEmpty();
        }

        public Object getObject(int index) {
            return linhasDecolagens.get(index);
        }
        
        public TableModelDecolagem() {
			this.linhasDecolagens = new ArrayList<Aviao>();
		}
		public TableModelDecolagem(List<Aviao> linhasDecolagens) {
			super();
			this.linhasDecolagens = new ArrayList<Aviao>(linhasDecolagens);
		}
}

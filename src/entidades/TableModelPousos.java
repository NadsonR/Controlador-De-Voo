package entidades;
import entidades.*;
import java.util.*;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

public class TableModelPousos extends AbstractTableModel {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		private String[] colunasPousos = new String[] {"Voo",
				"Empresa", "Origem", "Destino", "Pedido Pouso", "Prob.Gas", "Prob.Mec"};
		private static List<Aviao> linhasPousos ;
		
		public TableModelPousos() {
			super();
			linhasPousos = new ArrayList<Aviao>();
		}
		public TableModelPousos(List<Aviao> linhasPousos) {
			super();
			this.linhasPousos = new ArrayList<Aviao>(linhasPousos);
		}
		
		public Aviao getPousos(int rowIndex){
			return this.linhasPousos.get(rowIndex);
		}
		
		public void vaiParaPrimeiro(Aviao aviao){

		}
		
		public List<Aviao> findAll(){
			return linhasPousos;
		}
		
		public Aviao procurarId(String id){
			for(Aviao aviao : findAll())
				if (aviao.getId().equals(id))
					return aviao;
			return null;
		}
		
		
        @Override
        public int getColumnCount() {
            return colunasPousos.length;
        }

        @Override
        public int getRowCount() {
            return linhasPousos.size();
        }

        @Override
        public String getColumnName(int columnIndex) {

            return colunasPousos[columnIndex];
        }

        ;


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

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
            Aviao aviao = linhasPousos.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return aviao.getId();
                case 1:
                    return aviao.getEmpresa();
                case 2:
                    return aviao.getOrigem();
                case 3:
                    return aviao.getDestino();
                case 4:
                    return aviao.getTempo();
                case 5:
                	return aviao.isProblemaGasol();
                case 6:
                	return aviao.isProblemaMec();
                default:
                    return null;
            }	
		}
		public void addPouso(Aviao aviao) {
            linhasPousos.add(aviao);
			fireTableDataChanged();
        }

        public void removeAviaoPouso(int indiceLinha) {
            if (indiceLinha < linhasPousos.size()) {
                linhasPousos.remove(indiceLinha);
    			fireTableDataChanged();
            }
        }

        public void addListaDePousos(List<Aviao> aviao) {
            int tamanhoAntigo = getRowCount();
            linhasPousos.addAll(aviao);
            fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
        }

        public void limpar() {
            linhasPousos.clear();
            fireTableDataChanged();
        }

        public boolean isEmpty() {
            return linhasPousos.isEmpty();
        }

        public Object getObject(int index) {
            return linhasPousos.get(index);
        }

}

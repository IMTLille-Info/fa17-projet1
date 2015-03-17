package uvinfo.bomberman;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeleDynamiqueObjet extends AbstractTableModel {



	private final ArrayList<String> pseudos = new ArrayList<String>();
	 
	    private final String[] entetes = {"Pseudo"};
	
	    public int getRowCount() {
	        return pseudos.size();
	    }
	 
	    public int getColumnCount() {
	        return entetes.length;
	    }
	 
	    public String getColumnName(int columnIndex) {
	        return entetes[columnIndex];
	    }
	 

	 
	    public void addjoueur(String pseudo) {
	    	pseudos.add(pseudo);
	 
	        fireTableRowsInserted(pseudos.size(), pseudos.size());
	    }

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
	 
}

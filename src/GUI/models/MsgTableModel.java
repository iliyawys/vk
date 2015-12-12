/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author dev
 */
public class MsgTableModel extends AbstractTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Message> msg;

    public MsgTableModel(List<Message> msg) {
        this.msg = msg;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Дата";
            case 1:
                return "";
            case 2:
                return "Сообщение";
        }
        return "";
    }

    public int getRowCount() {
        return msg.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Message message = msg.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return message.getFormatedDate();
            case 1:
                return message.getFrom();
            case 2:
                return message.getBody();
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
    

}
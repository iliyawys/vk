/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.models;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author dev
 */
public class FlightTableRenderer extends JTextArea implements TableCellRenderer {

    public FlightTableRenderer() {
        setLineWrap(true);
        setWrapStyleWord(false);
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        setText(value.toString());

        //if (((Integer) table.getValueAt(row, 3)).intValue() < 1) {
        setBackground(Color.GRAY);
        setForeground(Color.WHITE);

        String data = (String) value.toString();
        int lineWidth = this.getFontMetrics(this.getFont()).stringWidth(data);
        int lineHeight = this.getFontMetrics(this.getFont()).getHeight();
        int rowWidth = table.getCellRect(row, column, true).width;

        int newRowHeight = (int) ((lineWidth / rowWidth) * (lineHeight)) + lineHeight * 2;
        if (table.getRowHeight(row) != newRowHeight) {
            table.setRowHeight(row, newRowHeight);
        }
        this.setText(data);

        return this;

    }
}

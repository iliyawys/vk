/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import API.API;
import GUI.models.FlightTableRenderer;
import GUI.models.MsgTableModel;
import GUI.models.Users;
import GUI.models.UsersTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author dev
 */
public class Messager implements GUI_Interface {

    private MsgTableModel msgModel;
    private int i = 0;
    private JSONObject v;
    private JTable table;
    private JTable list;

    private static volatile Messager instance;

    public static Messager getInstance() {
        Messager localInstance = instance;
        if (localInstance == null) {
            synchronized (API.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Messager();
                }
            }
        }
        return localInstance;
    }

    private Messager() {
    }

    public JPanel show(JPanel msg_form) {
        ArrayList<Users> users = API.getInstance().getUserList();

        JSONArray uArray = API.getInstance().getFriends();
        /*refact!!!*/
        for (int i = 0; i < uArray.length(); i++) {
            v = uArray.getJSONObject(i);
            Integer online = (Integer) v.get("online");
            if (online == 1) {
                users.add(new Users(v.get("first_name") + " " + v.get("last_name"), "", online, (Integer) v.get("uid")));
            }
        }
        for (int i = 0; i < uArray.length(); i++) {
            v = uArray.getJSONObject(i);
            Integer online = (Integer) v.get("online");
            if (online == 0) {
                users.add(new Users(v.get("first_name") + " " + v.get("last_name"), "", online, (Integer) v.get("uid")));
            }
        }

        TableModel model = new UsersTableModel(users);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    int sel = table.getSelectedRow();
                    int id = (Integer) table.getModel().getValueAt(sel, 3);
                    API.getInstance().getMsg(id);
                }
            }
        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                Integer status = (Integer) table.getModel().getValueAt(row, 2);
                if (status == 1) {
                    setBackground(Color.BLACK);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(170);
        table.setPreferredScrollableViewportSize(new Dimension(170, 270));
        table.setMaximumSize(new Dimension(170, 10));
        msg_form.add(new JScrollPane(table));

        msgModel = new MsgTableModel(API.getInstance().getMessage());
        list = new JTable(msgModel);

        list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn listCol = list.getColumnModel().getColumn(0);
        listCol.setPreferredWidth(40);
        TableColumn listCol2 = list.getColumnModel().getColumn(1);
        listCol2.setPreferredWidth(40);
        TableColumn listCol3 = list.getColumnModel().getColumn(2);
        listCol3.setPreferredWidth(450);
        list.setMaximumSize(new Dimension(170, 10));

        JPanel chat = new JPanel();
        chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));

        JTextArea sendField = new JTextArea();

        chat.add(new JScrollPane(list), BorderLayout.CENTER);
        chat.add(sendField);
        msg_form.add(chat);

        return msg_form;
    }

    public void updateMsg() {
        this.msgModel = new MsgTableModel(API.getInstance().getMessage());
//        this.msgModel.fireTableDataChanged();
        this.list.setModel(msgModel);
        list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        TableColumn listCol = list.getColumnModel().getColumn(0);
        listCol.setPreferredWidth(140);
        listCol.setResizable(false);
        TableColumn listCol2 = list.getColumnModel().getColumn(1);
        listCol2.setPreferredWidth(40);
        listCol2.setResizable(false);
        TableColumn listCol3 = list.getColumnModel().getColumn(2);
        listCol3.setPreferredWidth(395);
        listCol3.setResizable(false);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                list.getColumnModel().getColumn(2).setCellRenderer(new VariableRowHeightRenderer());
            }
        });
//        list.setDefaultRenderer(Object.class, new FlightTableRenderer());
        list.setMaximumSize(new Dimension(170, 10));
        list.getColumnModel().getColumn(2).setCellRenderer(new FlightTableRenderer());
    }

    public static class VariableRowHeightRenderer extends JLabel implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(String.valueOf(value));
            if (getPreferredSize().height > 1) {
                table.setRowHeight(row, getPreferredSize().height + 10);
            }
            return this;
        }
    }
}

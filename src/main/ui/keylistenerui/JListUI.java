package ui.keylistenerui;

import javax.swing.*;
import java.awt.*;

// Class to construct a nice looking JList
public class JListUI extends JList {

    // EFFECTS: creates the JList with the given ListModel
    public JListUI(DefaultListModel<String> listModel) {
        super(listModel);
        initJListUI();
        setCustomCellRenderer();
    }

    // MODIFIES: this
    // EFFECTS: sets JList properties
    private void initJListUI() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLayoutOrientation(JList.VERTICAL);
        setFixedCellHeight(35);
        setVisibleRowCount(5);
        setFont(new Font("Arial", Font.PLAIN, 14));
        setBackground(Color.white);
        setForeground((new Color(30, 140, 250)));
    }

    // MODIFIES: this
    // EFFECTS: customizes the highlight colour of the selected JList element
    private void setCustomCellRenderer() {
        this.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(new Color(113, 179, 255, 255));
                } else {
                    c.setBackground(list.getBackground());
                }
                return c;
            }
        });
    }
}
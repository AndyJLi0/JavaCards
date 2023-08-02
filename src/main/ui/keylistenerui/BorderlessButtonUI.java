package ui.keylistenerui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

// BorderlessButtonUI class
class BorderlessButtonUI extends BasicButtonUI {
    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
    }
}

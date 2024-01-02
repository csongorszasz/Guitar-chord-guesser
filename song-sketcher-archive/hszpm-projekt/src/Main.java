import ui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
//        new MainFrame();
        JFrame frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton button = new JButton("Open Menu");
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItem1 = new JMenuItem("Submenu 1");
        JMenuItem menuItem2 = new JMenuItem("Submenu 2");

        JMenu submenu = new JMenu("More Submenus");
        JMenuItem submenuItem1 = new JMenuItem("Submenu 3");
        JMenuItem submenuItem2 = new JMenuItem("Submenu 4");
        submenu.add(submenuItem1);
        submenu.add(submenuItem2);

        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.add(submenu);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(button, 0, button.getHeight());
            }
        });

        JPanel panel = new JPanel();
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }
}

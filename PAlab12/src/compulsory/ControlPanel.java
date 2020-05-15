package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * The control panel of the application. Allows the creation of a new component.
 * It has 3 elements:
 * The leftmost is a text field for specifying the class name of the desired component (such as "javax.swing.JButton");
 * The middle text field allows the specification of the component's text
 * The rightmost element will create and add the component to the design panel
 */
public class ControlPanel extends JPanel {
    private JButton button;
    private JTextField componentName;
    private JTextField componentText;
    private DesignPanel designPanel;

    public ControlPanel(DesignPanel designPanel) {
        this.designPanel = designPanel;

        componentName = new JTextField("Enter component",30);
        componentText = new JTextField("component text",30);
        this.add(componentName,BorderLayout.WEST);
        this.add(componentText,BorderLayout.CENTER);

        button = new JButton("Create & Add");
        this.add(button,BorderLayout.EAST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class component = Class.forName(componentName.getText());
                    Class[] signature = new Class[] {String.class};
                    Constructor constructor = component.getConstructor(signature);
                    Component instance = (Component) constructor.newInstance(componentText.getText());
                    designPanel.add(instance);
                    designPanel.revalidate();

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (NoSuchMethodException ex) {
                    Class component = null;
                    try {
                        component = Class.forName(componentName.getText());
                        Constructor constructor = component.getConstructor();
                        Component instance = (Component) constructor.newInstance(componentText.getText());
                        designPanel.add(instance);
                        designPanel.revalidate();

                    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        });
    }
}

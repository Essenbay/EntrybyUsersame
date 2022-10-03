package DialogBoxes.MyExample;


import javax.swing.*;
import java.awt.*;

public class MyTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new MyFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200,200);
            frame.setVisible(true);
        });
    }
}

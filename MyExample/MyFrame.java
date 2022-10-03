package MyExample;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class MyFrame extends JFrame {
    private TextField userName;
    private TextField email;
    private JButton confirmButton;
    private JTextField serverText;
    public MyFrame(){
        JPanel fieldPanel = new JPanel();
        JLabel userNameLabel = new JLabel("Username: ");
        userName = new TextField(10);
        JLabel emailLabel = new JLabel("Email: ");
        email = new TextField(10);

        fieldPanel.add(userNameLabel);
        fieldPanel.add(userName);
        fieldPanel.add(emailLabel);
        fieldPanel.add(email);


        JPanel confirmPanel = new JPanel();
        Action submitAction = new SubmitAction();
        submitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));

        confirmButton = new JButton("Submit");
        confirmButton.getActionMap().put("SubmitAction", submitAction);
        confirmButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                (KeyStroke) submitAction.getValue(Action.ACCELERATOR_KEY), "SubmitAction");

        confirmButton.setSize(this.getWidth() / 4, this.getHeight() / 4);
        confirmButton.setMinimumSize(new Dimension(50,50));
        confirmPanel.add(confirmButton);

        add(fieldPanel, BorderLayout.CENTER);
        add(confirmPanel, BorderLayout.SOUTH);
    }

    private Object[] getPostList(){
        return new String[]{"Manager", "Developer", "Tester"};
    }

    private String getPostByIndex(int index){
        return switch (index) {
            case 0 -> "Manager";
            case 1 -> "Developer";
            case 2 -> "Tester";
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    private class SubmitAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Objects.equals(userName.getText(), "guest")){
                int result = JOptionPane.showConfirmDialog(MyFrame.this, "Are you sure you want to come in as a guest?",
                        "Welcome, guest!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(MyFrame.this, "Welcome, guest!", "Welcome!", JOptionPane.PLAIN_MESSAGE);
                }
            }
            else if(!email.getText().contains("@mail.ru")){
                JOptionPane.showMessageDialog(MyFrame.this,
                        "Error! Wrong domain", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(Objects.equals(userName.getText(), "admin")){
                serverText = new JTextField();
                String server = JOptionPane.showInputDialog(MyFrame.this, "Enter the the server:");
                String message = "Welcome, admin with email " + email.getText() + " to " + serverText.getText() + " server";
                if(server.equals("Almaty")){
                    JOptionPane.showMessageDialog(MyFrame.this, message, "Welcome!", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(MyFrame.this,
                            "No such server", "Incorrect server",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                int postIn = JOptionPane.showOptionDialog(MyFrame.this, "Choose your posts", "Select your post", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, getPostList(), getPostList()[0]);
                JOptionPane.showMessageDialog(MyFrame.this, "Welcome, " + userName.getText() + " with " + getPostByIndex(postIn) + " post.", "Welcome!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

}

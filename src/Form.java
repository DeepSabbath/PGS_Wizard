import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amadeusz on 21.04.2016.
 */
public class Form extends JFrame {

    int counter = 0;        // zmienna liczy, który ekran powinien zostać wyświetlony
    JTextField data = new JTextField();     // pole do wpisywania wartości
    JLabel label, lastName, address, number, warning;
    JButton nextStepBTN, prevStepBTN;

    public Form()
    {

        setLayout(null);
        setSize(380, 280);
        init();
        setVisible(true);

    }

    public static void main(String[] args)
    {
        Form form = new Form();
        form.setDefaultCloseOperation(form.EXIT_ON_CLOSE);
    }

    public void init()
    {
        label = new JLabel("First Name: ");
        label.setBounds(50, 50, 250, 25);
        add(label);

        data.setText(Person.getFirstName());
        data.setBounds(150, 50, 100, 25);
        add(data);

        warning = new JLabel();
        warning.setForeground(Color.red);
        warning.setBounds(50,100,250,25);
        add(warning);

        nextStepBTN = new JButton("Next");              //button odpalający następny ekran
        nextStepBTN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nextStep();
            }
        });
        nextStepBTN.setBounds(250,190,80,32);
        add(nextStepBTN);

        prevStepBTN = new JButton("Prev");              //button odpalający poprzedni ekran
        prevStepBTN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                prevStep();
            }
        });
        prevStepBTN.setBounds(50,190,80,32);
        add(prevStepBTN);
    }

    private void nextStep()             // funkcja wyświelta odpowiedni ekran w zależności od zmiennej counter
    {
        if (counter==0)
        {
            if(validateData(data.getText()))
            {
                Person.setFirstName(data.getText());
                label.setText("Last Name");
                data.setText(Person.getLastName());
                setWarning("");
                counter++;
            } else
            {
                setWarning("First name cannot be empty");
            }
        } else if(counter==1)
        {
            if(validateData(data.getText()))
            {
                Person.setLastName(data.getText());
                label.setText("Address");
                data.setText(Person.getAddress());
                setWarning("");
                counter++;
            } else
            {
                setWarning("Last name cannot be empty");
            }
        } else if(counter==2)
        {
            if(validateData(data.getText()))
            {
                Person.setAddress(data.getText());
                label.setText("Phone number");
                data.setText((String.valueOf(Person.getNumber())));
                setWarning("");
                counter++;
            } else
            {
                setWarning("Address cannot be empty");
            }
        }else
        {
            try {                                                  // wykonuje się jeśli podany number jest liczbą
                Person.setNumber(Integer.parseInt(data.getText()));
                remove(data);
                label.setText("First name: " + Person.getFirstName());
                warning.setVisible(false);
                lastName = new JLabel("Last name: " + Person.getLastName());
                lastName.setBounds(50, 70, 250, 25);
                add(lastName);
                address = new JLabel("Address: " + Person.getAddress());
                address.setBounds(50, 90, 250, 25);
                add(address);
                number = new JLabel("Phone number: " + String.valueOf(Person.getNumber()));
                number.setBounds(50, 110, 250, 25);
                add(number);
                remove(nextStepBTN);
                remove(prevStepBTN);
                repaint();
            } catch (NumberFormatException e)                       // wyświetla informację o błędzie jeśli podano numer w złym formacie
            {
                warning.setText("Number may contain only digit from 0 to 9 ");
                warning.setVisible(true);
                add(warning);
                repaint();
            }
        }
    }

    private void prevStep()                     // wyświelta odpowiedni ekran w zależności od zmiennej counter
    {
        if (counter==1)
        {
            Person.setLastName(data.getText());
            label.setText("First Name");
            data.setText(Person.getFirstName());
            counter--;
        } else if (counter==2)
        {
            Person.setAddress((data.getText()));
            label.setText("Last name");
            data.setText(Person.getLastName());
            counter--;
        } else if(counter==3)
        {
            try {                                                              // wykonuje się jeśli podany number jest liczbą
                Person.setNumber(Integer.parseInt(data.getText()));
                label.setText("Address");
                data.setText(Person.getAddress());
                warning.setVisible(false);
                counter--;
            } catch (NumberFormatException e)                                   // wyświetla informację o błędzie jeśli podano numer w złym formacie
            {
                warning.setText("Number may contain only digit from 0 to 9 ");
                warning.setVisible(true);
                add(warning);
                repaint();
            }
        }
    }

    private boolean validateData(String s)
    {
        if(s.isEmpty()) return false;
        return true;
    }

    private void setWarning(String s)
    {
        if(s.isEmpty())
        {
            warning.setVisible(false);
        } else
        {
            warning.setText(s);
            warning.setVisible(true);
        }
        repaint();
    }
}

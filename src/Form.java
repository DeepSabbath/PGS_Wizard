import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amadeusz on 21.04.2016.
 */
public class Form extends JFrame {

    int counter = 0;        // zmienna liczy, który ekran powinien zostać wyświetlony
    JTextField data = new JTextField();     // pole do wpisywania danych (oprócz ulicy)
    JTextField data2 = new JTextField();     // pole do wpisywania danych (ulica)
    JLabel label, lastName, address, number, warning, street;
    JButton nextStepBTN, prevStepBTN;
    Person person = new Person();

    public Form()
    {
        setLayout(null);
        setSize(380, 280);
        setLocation(getCenter());
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
        label = new JLabel("First name: ");
        label.setBounds(50, 50, 250, 25);
        add(label);

        data.setText(person.getFirstName());
        data.setBounds(150, 50, 100, 25);
        add(data);

        data2.setBounds(150, 80, 100, 25);
        data2.setVisible(false);
        add(data2);

        street = new JLabel();
        street.setBounds(50, 80, 250, 25);
        add(street);

        warning = new JLabel();
        warning.setForeground(Color.red);
        warning.setBounds(50,110,250,25);
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

    private void nextStep()             // metoda wyświelta odpowiedni ekran w zależności od zmiennej counter
    {
        if (counter==0)
        {
            if(validateData(data.getText()))            //metoda validateData zwraca true jeżeli pole zostało wypełnione
            {
                person.setFirstName(data.getText());
                label.setText("Last name");
                data.setText(person.getLastName());
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
                person.setLastName(data.getText());
                label.setText("City");
                street.setText("Street");
                data.setText(person.getCity());
                data2.setText(person.getStreet());
                setWarning("");
                street.setVisible(true);
                data2.setVisible(true);
                counter++;
            } else
            {
                setWarning("Last name cannot be empty");
            }
        } else if(counter==2)
        {
            if(validateData(data.getText()) && validateData(data2.getText()))
            {
                person.setCity(data.getText());
                person.setStreet(data2.getText());
                data2.setVisible(false);
                street.setVisible(false);
                label.setText("Phone number");
                data.setText((String.valueOf(person.getNumber())));
                setWarning("");
                counter++;
            } else
            {
                setWarning("Street and city cannot be empty");
            }
        }else
        {
            try {                                                  // wykonuje się jeśli podany number jest liczbą
                person.setNumber(Integer.parseInt(data.getText()));
                remove(data);
                label.setText("First name: " + person.getFirstName());
                warning.setVisible(false);
                lastName = new JLabel("Last name: " + person.getLastName());
                lastName.setBounds(50, 70, 250, 25);
                add(lastName);
                address = new JLabel("Address: " + person.getStreet() + "  " + person.getCity());
                address.setBounds(50, 90, 250, 25);
                add(address);
                number = new JLabel("Phone number: " + String.valueOf(person.getNumber()));
                number.setBounds(50, 110, 250, 25);
                add(number);
                remove(nextStepBTN);
                remove(prevStepBTN);
                repaint();
            } catch (NumberFormatException e)                       // wyświetla informację o błędzie jeśli podano numer w złym formacie
            {
                setWarning("Number may contain only digit from 0 to 9 ");
                add(warning);
                repaint();
            }
        }
    }

    private void prevStep()                     // wyświelta odpowiedni ekran w zależności od zmiennej counter
    {
        if (counter==1)
        {
            if(validateData(data.getText()))
            {
                person.setLastName(data.getText());
                label.setText("First Name");
                data.setText(person.getFirstName());
                setWarning("");
                counter--;
            } else
            {
                setWarning("Last name cannot be empty");
            }
        } else if (counter==2)
        {
            if(validateData(data.getText())&&validateData(data2.getText()))
            {
                person.setCity((data.getText()));
                person.setStreet(data2.getText());
                label.setText("Last name");
                data.setText(person.getLastName());
                data2.setVisible(false);
                street.setVisible(false);
                setWarning("");
                counter--;
            } else
            {
                setWarning("Street and city cannot be empty");
            }
        } else if(counter==3)
        {
            try {                                                       // wykonuje się jeśli podany number jest liczbą
                person.setNumber(Integer.parseInt(data.getText()));
                label.setText("City");
                street.setText("Street");
                data.setText(person.getCity());
                data2.setText(person.getStreet());
                street.setVisible(true);
                data2.setVisible(true);
                setWarning("");
                counter--;
            } catch (NumberFormatException e)                            // wyświetla informację o błędzie jeśli podano numer w złym formacie
            {
                setWarning("Number may contain only digit from 0 to 9 ");
                add(warning);
                repaint();
            }
        }
    }

    private boolean validateData(String s)                      // metoda sprawdzająca czy pole nie jest puste
    {
        if(s.isEmpty()) return false;
        return true;
    }

    private void setWarning(String s)                           // metoda ustawiająca lub usuwająca ostrzeżenie
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

    private Point getCenter()                                   // metoda wyliczająca środek ekranu
    {
        int width=Toolkit.getDefaultToolkit().getScreenSize().width;
        int height=Toolkit.getDefaultToolkit().getScreenSize().height;

        int xCenter=(width - 380)/2;
        int yCenter=(height - 280)/2;

        Point p =new Point();
        p.setLocation(xCenter, yCenter);

        return p;
    }
}

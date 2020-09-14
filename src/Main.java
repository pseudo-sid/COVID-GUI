import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

class date {
    int dd, mm, yy;

    date(String s) {
        this.dd = Integer.parseInt("" + s.charAt(0) + s.charAt(1));
        this.mm = Integer.parseInt("" + s.charAt(3) + s.charAt(4));
        this.yy = Integer.parseInt("" + s.charAt(6) + s.charAt(7) + s.charAt(8) + s.charAt(9));
    }

    int month_days[] = {0,31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    String ret_date() {
        return ("" + dd + "/" + mm + "/" + yy);
    }

    int diff(date c) {
        int days = 0;
        int m = c.mm;
        int d = c.dd;
        if (m >= this.mm) {
            while (m > this.mm) {
                days += d;
                d = 0;
                m--;
                d += month_days[m];
            }
            days += d - this.dd;
        }
        if (m == this.mm)
            return days;
        return -1;
    }
}

class Patient{
    String name;
    int age;
    char tower;
    date d;
    date rec_d;

    int month_days[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30 , 31};
    Patient(String name, int age, char tower, String dat){
        this.name = name;
        this.age = age;
        this.tower = tower;
        d = new date(dat);
        rec_d = new date(dat);
        rec_d.dd += 21;
        if(rec_d.dd > month_days[rec_d.mm]) {
            rec_d.dd -= month_days[rec_d.mm];
            rec_d.mm += 1;
        }
    }
}

public class Main {
    public static void main(String[] args){
        Swing obj = new Swing();
    }
}

class Swing extends JFrame{

    public Patient[] tower_A = new Patient[5];
    public Patient[] tower_B = new Patient[5];
    public Patient[] tower_C = new Patient[3];
    public Patient[] tower_D = new Patient[7];

    int month_days[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30 , 31};
    void reset_data(String[][] arr){
        for(int i = 0; i <20; i++)
            for(int j = 0; j <6; j++)
                arr[i][j] = "";

    }
    void set_data(String[][] arr, String name, String age, String tower, String rep, String rec, String act, int ind){
        arr[ind][0] = name;
        arr[ind][1] = age;
        arr[ind][2] = tower;
        arr[ind][3] = rep;
        arr[ind][4] = rec;
        arr[ind][5] = act;
    }
    Swing(){
        {
            tower_A[0] = new Patient("Flora", 6, 'A', "01/04/2020");
            tower_A[1] = new Patient("Caery", 72, 'A', "01/06/2020");
            tower_A[2] = new Patient("Bob", 74, 'A', "04/07/2020");
            tower_A[3] = new Patient("Smith", 89, 'A', "07/08/2020");
            tower_A[4] = new Patient("Robertz", 50, 'A', "09/08/2020");

            tower_B[0] = new Patient("Denys", 24, 'B', "01/04/2020");
            tower_B[1] = new Patient("David", 7, 'B', "14/06/2020");
            tower_B[2] = new Patient("Pearson", 47, 'B', "04/06/2020");
            tower_B[3] = new Patient("Anderson", 62, 'B', "27/07/2020");
            tower_B[4] = new Patient("Julie", 86, 'B', "02/05/2020");

            tower_C[0] = new Patient("Jim", 42, 'C', "18/05/2020");
            tower_C[1] = new Patient("Rachel", 48, 'C', "24/07/2020");
            tower_C[2] = new Patient("Thomas", 21, 'C', "11/06/2020");

            tower_D[0] = new Patient("Hazel", 87, 'D', "23/06/2020");
            tower_D[1] = new Patient("Kevim", 37, 'D', "05/06/2020");
            tower_D[2] = new Patient("Tom", 67, 'D', "20/06/2020");
            tower_D[3] = new Patient("Mary", 17, 'D', "21/06/2020");
            tower_D[4] = new Patient("Johnson", 10, 'D', "01/08/2020");
            tower_D[5] = new Patient("Edith", 42, 'D', "07/06/2020");
            tower_D[6] = new Patient("John", 95, 'D', "01/06/2020");
        }

        setTitle("Student's Survey");
        setSize(960, 540);
        setVisible(true);

        JLabel jLabel = new JLabel("COVID-19 cases analysis in our society", 0);
        //BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        FlowLayout flowLayout = new FlowLayout();
        setLayout(flowLayout); jLabel.setFont(jLabel.getFont().deriveFont(40.0f));


        JLabel jLabel2 = new JLabel("Enter the date in the exact format (dd/mm/yyyy) (eg. 08/08/2020 and not 8/8/2020):", 0 );
        jLabel2.setFont(jLabel2.getFont().deriveFont(20.0f));

        JTextField jTextField1 = new JTextField(5);
        jTextField1.setFont(jLabel2.getFont().deriveFont(20.0f));

        add(jLabel);
        add(jLabel2);
        add(jTextField1);


        JCheckBox a,b,c,d;
        a = new JCheckBox("Tower A");
        b = new JCheckBox("Tower B");
        c = new JCheckBox("Tower C");
        d = new JCheckBox("Tower D");
        add(a); add(b); add(c); add(d);
        String [] header={"Patient Name","Age", "Tower", "Reported on", "Recovery Date", "Active Status"};

        String[][] data = {{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""}};
        DefaultTableModel tableModel = new DefaultTableModel(data, header);

        JTable table = new JTable(tableModel);
        tableModel.setDataVector(data, header);
        JScrollPane pane = new JScrollPane(table);



        /*
        tableModel.addColumn("Patient Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Tower");
        tableModel.addColumn("Reported on");
        tableModel.addColumn("Recovery Date");
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        */

        Button button = new Button();
        button.setLabel("Generate data");
        add(button);
        add(new JScrollPane(table));

        JLabel active = new JLabel("");
        JLabel recovered = new JLabel("");
        add(active);
        add(recovered);


        button.addActionListener(e->{
            String dt = jTextField1.getText();
            dt = dt.strip();
            if(dt.length() != 10 || dt.charAt(2) != '/' || dt.charAt(5) != '/')
                JOptionPane.showMessageDialog(null, "Wrong format, Enter again!");
            else{
                date cd = new date(dt);
                if(cd.mm > 8 || cd.mm < 4 || cd.yy != 2020)
                    JOptionPane.showMessageDialog(null, "Date out of range. Please Enter within April'20 and Aug'20 only!");
                else if(cd.dd < 1 || cd.dd > month_days[cd.mm])
                    JOptionPane.showMessageDialog(null, "Incorrect date entered!");
                else{
                    reset_data(data);
                    tableModel.setDataVector(data,header);
                    table.addRowSelectionInterval(0, 19);
                    table.setBackground(Color.WHITE);
                    table.setBackground(Color.WHITE);
                    int ct = 0;
                    int ac = 0;
                    int r = 0;
                    if(a.isSelected()){
                        for(int i =0; i < 5; i++){
                            int v = tower_A[i].d.diff(cd);
                            String act;
                            if(v >= 0){
                                if(v >= 21) {
                                    act = "Recovered";
                                    r++;
                                }
                                else {
                                    ac++;
                                    act = "Active";
                                }
                                set_data(data, tower_A[i].name, ""+tower_A[i].age, "" + tower_A[i].tower, tower_A[i].d.ret_date(), tower_A[i].rec_d.ret_date(), act,ct);
                                ct++;
                            }
                        }
                    }
                    if(b.isSelected()){
                        for(int i =0; i < 5; i++){
                            int v = tower_B[i].d.diff(cd);
                            String act;
                            if(v >= 0){
                                if(v >= 21) {
                                    act = "Recovered";
                                    r++;
                                }
                                else {
                                    ac++;
                                    act = "Active";
                                }
                                set_data(data, tower_B[i].name, ""+tower_B[i].age, "" + tower_B[i].tower, tower_B[i].d.ret_date(), tower_B[i].rec_d.ret_date(),act, ct);
                                ct++;
                            }
                        }
                    }

                    if(c.isSelected()){
                        for(int i =0; i < 3; i++){
                            int v = tower_C[i].d.diff(cd);
                            String act;
                            if(v >= 0){
                            if(v >= 21) {
                                act = "Recovered";
                                r++;
                            }
                            else {
                                ac++;
                                act = "Active";
                            }
                                set_data(data, tower_C[i].name, ""+tower_C[i].age, "" + tower_C[i].tower, tower_C[i].d.ret_date(), tower_C[i].rec_d.ret_date(),act, ct);
                                ct++;
                            }
                        }
                    }
                    if(d.isSelected()){
                        for(int i =0; i < 7; i++){
                            int v = tower_D[i].d.diff(cd);
                            String act;
                            if(v >= 0){
                                if(v >= 21) {
                                    act = "Recovered";
                                    r++;
                                }
                                else {
                                    ac++;
                                    act = "Active";
                                }
                                set_data(data, tower_D[i].name, ""+tower_D[i].age, "" + tower_D[i].tower, tower_D[i].d.ret_date(), tower_D[i].rec_d.ret_date(), act, ct);
                                ct++;
                            }
                        }
                    }
                    active.setText("No. of active cases: " + ac +"    ");
                    recovered.setText("No. of recovered cases: " + r);
                    tableModel.setDataVector(data, header);
                    for(int i = 0; i < 20; i++){
                        if(data[i][5] == "Active"){
                            table.addRowSelectionInterval(i, i);
                            table.setSelectionBackground(Color.PINK);
                            table.setSelectionForeground(Color.red);
                        }

                    }


                }

            }

        });

        setDefaultCloseOperation(3);
    }
}
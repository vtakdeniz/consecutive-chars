/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consecutive_Chars;

import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



/**
 *
 * @author veliakdeniz
 */
public class MultiLinked {

    private Node head;

    public String findPath() {
        String path = null;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath();
        }
        return path;
    }

    public void consecutiveChars() {
        int counter = 0;
        char holder = ' ';
        boolean space = true;
        try (FileReader fr = new FileReader(findPath())) {
            int c = 0;
            while ((c = fr.read()) != -1) {
                char ch = (char) c;
                if (Character.isLetter(ch)) {
                    ch = Character.toLowerCase(ch);
                   
                    boolean b = true;
                    if ((b = addMain(ch)) != false) {

                        if (counter > 0) {
                            if (space) {
                                addSide(getNode(holder), ch);

                            }
                            space = true;
                            holder = ch;

                        } else {
                            holder = ch;
                            counter++;
                        }
                    } else {
                        if (space) {
                            addSide(getNode(holder), ch);

                        }
                        space = true;
                        holder = ch;
                    }

                }
                space = Character.isLetter(ch);
            }
            printAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            int counterMain = 0;
            int counterSide = 0;
            Node temp = head;
            SideNode tempSide = head.side;
            while (temp.nextNode != null) {
                System.out.println("///////// For Node " + counterMain + " contains -> " + temp.data + " side nodes are :");
                counterMain++;
                if (isSideEmpty(temp)) {
                    System.out.println("Side node is empty");
                    System.out.println("\n");
                } else {

                    printSide(temp.data);
                    System.out.println("\n");
                }
                temp = temp.nextNode;
            }
            System.out.println("///////// For Node " + counterMain + " contains -> " + temp.data + " side nodes are :");
            printSide(temp.data);
            System.out.println("\n");

        }
    }

    public boolean addMain(char c) {
        if (isEmpty()) {
            Node n = new Node();
            n.data = c;
            head = n;
            return true;
        } else {
            Node temp = head;
            boolean b = false;
            while (temp.nextNode != null) {
                if (temp.data == c) {
                    b = true;

                    return false;
                }
                temp = temp.nextNode;
            }
            if (head.data == c) {
                b = true;
                return false;
            }
            if (b == false) {
                if (temp.data != c) {
                    Node n = new Node();
                    n.data = c;
                    temp.nextNode = n;
                    return true;
                } else {
                    return false;
                }

            }
        }
        return false;
    }
    
    public void addSide(Node n, char c) {
        if (isSideEmpty(n)) {
            n.side = new SideNode();
            n.side.data = c;
            n.side.counter = 1;
        } else {
            SideNode temp = n.side;

            boolean b = false;
          
            while (temp.nextSide != null) {
                if (temp.data == c) {

                    temp.counter++;

                    b = true;

                    break;
                }
                temp = temp.nextSide;
            }

            if (b == false) {
                if (temp.data == c) {
                    temp.counter++;
                } else {
                    SideNode s = new SideNode();
                    s.data = c;
                    s.counter = 1;
                    temp.nextSide = s;
                }
            }

        }

    }

    public void addSide(char n1, char c) {
        Node n = getNode(n1);
        if (isSideEmpty(n)) {
            n.side = new SideNode();
            n.side.data = c;
            n.side.counter = 1;
        } else {
            SideNode temp = n.side;

            boolean b = false;
          
            while (temp.nextSide != null) {
                System.out.println(temp.data);
                if (temp.data == c) {

                    temp.counter++;
                    b = true;
                    break;
                }
                temp = temp.nextSide;
            }

            if (b == false) {
                if (temp.data == c) {
                    temp.counter++;
                } else {
                    SideNode s = new SideNode();
                    s.data = c;
                    s.counter = 1;
                    temp.nextSide = s;
                }
            }

        }

    }


    public Node getNode(char c) {
        Node temp = head;
        while (temp.nextNode != null) {
            if (temp.data == c) {

                break;
            }
            temp = temp.nextNode;
        }
        return temp;
    }

    
    public void printSide(char c) {
        if (isSideEmpty(getNode(c))) {
            System.out.println("Side node is empty");
        } else {
            SideNode temp = getNode(c).side;
            int counter = 0;
            System.out.println("For " + c + " Side Node " + counter + " = " + temp.data + " , " + temp.counter);
            counter++;
            while (temp.nextSide != null) {
                temp = temp.nextSide;
                System.out.println("For " + c + " Side Node " + counter + " = " + temp.data + " , " + temp.counter);

                counter++;
            }
        }
    }

   
    public void printMain() {
        Node temp = head;
        int counter = 0;
        if (isEmpty()) {
            System.out.println("List is Empty");
        } else {
            while (temp.nextNode != null) {

                System.out.println("Node" + " " + counter + " : " + temp.data);
                temp = temp.nextNode;
                counter++;
            }
            System.out.println("Node" + " " + counter + " : " + temp.data);
        }
    }

    boolean isEmpty() {
        return head == null;
    }

    boolean isSideEmpty(Node n) {
        return n.side == null;
    }

    public int biggestRepeat() {

        Node temp = head;
        Node values;
        SideNode side = head.side;
        int biggest = 0;
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {

            while (temp.nextNode != null) {
                side = temp.side;

                if (!isSideEmpty(temp)) {

                    while (side.nextSide != null) {

                        if (side.counter > biggest) {
                            biggest = side.counter;
                        }
                        side = side.nextSide;
                    }

                    if (side.counter > biggest) {

                        biggest = side.counter;
                    }

                }
                temp = temp.nextNode;
            }
            side = temp.side;
            if (!isSideEmpty(temp)) {
                while (side.nextSide != null) {
                    if (side.counter > biggest) {

                        biggest = side.counter;

                    }
                    side = side.nextSide;
                }
                if (side.counter > biggest) {

                    biggest = side.counter;

                }
            }

        }
        return biggest;
    }

    public int lowestRepeat() {

        Node temp = head;
        SideNode side = head.side;
        int lowest = side.counter;
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {

            while (temp.nextNode != null) {
                side = temp.side;

                if (!isSideEmpty(temp)) {

                    while (side.nextSide != null) {

                        if (side.counter < lowest) {
                            lowest = side.counter;
                        }
                        side = side.nextSide;
                    }

                    if (side.counter < lowest) {

                        lowest = side.counter;
                    }

                }
                temp = temp.nextNode;
            }
            side = temp.side;
            if (!isSideEmpty(temp)) {
                while (side.nextSide != null) {
                    if (side.counter < lowest) {

                        lowest = side.counter;

                    }
                    side = side.nextSide;
                }
                if (side.counter < lowest) {

                    lowest = side.counter;

                }
            }

        }
        return lowest;

    }


    public void ardisikKarakterler(char c) {

        if (isSideEmpty(getNode(c))) {
            System.out.println("Side node is empty");
        } else {
            SideNode temp = getNode(c).side;
            int counter = 0;
            System.out.println("For " + c + " Side Node " + counter + " = " + temp.data + " , " + temp.counter);
            counter++;
            while (temp.nextSide != null) {
                temp = temp.nextSide;
                System.out.println("For " + c + " Side Node " + counter + " = " + temp.data + " , " + temp.counter);

                counter++;
            }
        }

    }

    public void enCokArdisik() {
        System.out.println("Words that follow each other the most :");
        int biggest = biggestRepeat();
        Node temp = head;
        SideNode side = head.side;

        if (isEmpty()) {
            System.out.println("List is empty");
        } else {

            while (temp.nextNode != null) {
                side = temp.side;

                if (!isSideEmpty(temp)) {

                    while (side.nextSide != null) {

                        if (side.counter >= biggest) {
                            System.out.println(temp.data + " , " + side.data + " : " + side.counter);
                        }
                        side = side.nextSide;
                    }

                    if (side.counter >= biggest) {
                        System.out.println(temp.data + " , " + side.data + " : " + side.counter);

                    }

                }
                temp = temp.nextNode;
            }
            side = temp.side;
            if (!isSideEmpty(temp)) {
                while (side.nextSide != null) {
                    if (side.counter >= biggest) {
                        System.out.println(temp.data + " , " + side.data + " : " + side.counter);
                    }
                    side = side.nextSide;
                }
                if (side.counter >= biggest) {
                    System.out.println(temp.data + " , " + side.data + " : " + side.counter);
                }
            }

        }

    }


    public void enCokArdisik(char k) {
        Node temp = getNode(k);
        int big = 0;
        SideNode side = temp.side;
        if (isSideEmpty(temp)) {
            System.out.println("There are no words after " + temp.data);
        } else {
            System.out.println("Most repetitive words for " + temp.data + " : ");
            while (side != null) {
                if (side.counter > big) {
                    big = side.counter;
                }
                side = side.nextSide;
            }
            side = temp.side;
            while (side != null) {
                if (side.counter >= big) {
                    System.out.println(side.data);
                }
                side = side.nextSide;
            }

        }
    }

    public void enAzArdisik() {
        System.out.println("Words that follow each other the least :");
        int lowest = lowestRepeat();
        Node temp = head;
        SideNode side = head.side;

        if (isEmpty()) {
            System.out.println("List is empty");
        } else {

            while (temp.nextNode != null) {
                side = temp.side;

                if (!isSideEmpty(temp)) {

                    while (side.nextSide != null) {

                        if (side.counter <= lowest) {
                            System.out.println(temp.data + " , " + side.data + " : " + side.counter);
                        }
                        side = side.nextSide;
                    }

                    if (side.counter <= lowest) {
                        System.out.println(temp.data + " , " + side.data + " : " + side.counter);

                    }

                }
                temp = temp.nextNode;
            }
            side = temp.side;
            if (!isSideEmpty(temp)) {
                while (side.nextSide != null) {
                    if (side.counter <= lowest) {
                        System.out.println(temp.data + " , " + side.data + " : " + side.counter);
                    }
                    side = side.nextSide;
                }
                if (side.counter <= lowest) {
                    System.out.println(temp.data + " , " + side.data + " : " + side.counter);
                }
            }

        }

    }

    public void enAzArdisik(char k) {
        Node temp = getNode(k);

        SideNode side = temp.side;
        int low = temp.side.counter;
        if (isSideEmpty(temp)) {
            System.out.println("There are no words after " + temp.data);
        } else {
            System.out.println("Least repetitive words for " + temp.data + " : ");
            while (side != null) {
                if (side.counter < low) {
                    low = side.counter;
                }
                side = side.nextSide;
            }
            side = temp.side;
            while (side != null) {
                if (side.counter <= low) {
                    System.out.println(side.data);
                }
                side = side.nextSide;
            }

        }

    }

}

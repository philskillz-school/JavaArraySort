package src;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class ArraySortGUI extends Frame {
    int max = 10;
    int[] arr = new int[max];
    
    Button b_FeldFuellen, b_FeldSortieren, b_QuickSort, b_Test;
    Image[] bildFeld= new Image[max];
    
    public ArraySortGUI() {
        super("-----  Sortier-Demo  -----");
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
                System.exit(0);
            }
        });
        
        setLayout(null);
        
        b_FeldFuellen = new Button("Feld füllen");
        b_FeldFuellen.setBounds(100,300,120,30);
        add(b_FeldFuellen);
        
        b_FeldSortieren = new Button("BubbleSort");
        b_FeldSortieren.setBounds(300,300,120,30);
        add(b_FeldSortieren);
        
        b_QuickSort = new Button("Quick Sort");
        b_QuickSort.setBounds(500,300,120,30);
        add(b_QuickSort);
        
        System.out.println("- - - - - - - - - - - - - ");
    }
    
    void updateGui() {
        repaint(1);
        update(getGraphics());
    }

    void fillArrayRandom() {
        for (int i = 0; i<arr.length; i++) {
            arr[i] = (int) (Math.random()*9+1);
        }
        updateGui();
    }

    void bubbleSort(){
        Boolean switched = true;
        
        for(int j = 0; j < arr.length && switched; j++) {
            switched = false;
            
            for (int i = 0; i<arr.length-1-j; i++) {
                if (arr[i+1] < arr[i]) { // switch
                    int next = arr[i+1];
                    arr[i+1] = arr[i]; // set next to current bcs smaller
                    arr[i] = next; // set current to next
                    switched = true;
                    updateGui();
                }
                
                System.out.println("Pause");
                pause(300);
            }   
        }
    }
 
    void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    void quickSort()
    {
        System.out.println("Unsorted array: " + Arrays.toString(arr));

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length);

        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();
            if (end - start < 2) {
                continue;
            }

            // partitioning part
            int position = start + ((end - start) / 2);
            int low = start;
            int high = end - 2;
            int piv = arr[position];
            swap(position, end - 1);
            updateGui();
            pause(300);        
            
            while (low < high) {
                if (arr[low] < piv) {
                    low++;
                } else if (arr[high] >= piv) {
                    high--;
                } else {
                    swap(low, high);
                    updateGui();
                    pause(300);
                }
            }
            position = high;
            if (arr[high] < piv) {
                position++;
            }
            swap(end - 1, position);
            updateGui();
            pause(300);
            // end partitioning part

            stack.push(position + 1);
            stack.push(end);
            stack.push(start);
            stack.push(position);
            updateGui();
        }
    }

    public void paint(Graphics g){
        for (int i=0; i<arr.length; i++){
            bildFeld[i]=getToolkit().getImage("sort" + arr[i] + ".jpg");
            g.drawImage(bildFeld[i], 0 + i*75, 40, this);
        }
    }

    public void pause(int dauer){        // ausnahmsweise!!!
        long alteZeit, neueZeit;
        Date datum = new Date();
        alteZeit=datum.getTime();
        neueZeit = alteZeit;
        
        while (neueZeit < (alteZeit + dauer)) {
            datum = new Date();
            neueZeit=datum.getTime();
        }
    }
    
    public boolean action(Event e, Object o) { //total veraltet und nicht objektorientiert !!!
        if (e.target == b_FeldFuellen) 
            fillArrayRandom();
        else if (e.target == b_FeldSortieren) 
            bubbleSort();
        else if (e.target == b_QuickSort)
            quickSort();
        return true;
    }
    
    public static void main(String[] sdfs){
        ArraySortGUI fs = new ArraySortGUI();
        fs.setSize(800, 400);
        fs.setVisible(true);
    }
}
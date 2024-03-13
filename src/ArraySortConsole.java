package src;

public class ArraySortConsole
{
    int max = 10;
    int[] arr = new int[max];
    
    void fillArrayRandom() {
        for (int i = 0; i<arr.length; i++) {
            arr[i] = (int) (Math.random()*9+1);
        }
    }
    
    void printArray() {
        for (int i = 0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    void sortArray() {
        Boolean switched = true;
        
        for(int j = 0; j < arr.length && switched; j++) {
            switched = false;
            for (int i = 0; i<arr.length-1-j; i++) {
                if (arr[i+1] < arr[i]) { // switch
                    int next = arr[i+1];
                    arr[i+1] = arr[i]; // set next to current bcs smaller
                    arr[i] = next; // set current to next
                    switched = true;
                }
            }   
        }
    }
    
    public ArraySortConsole()
    {
        fillArrayRandom();
        System.out.println("Original array:");
        printArray();
        sortArray();
        System.out.println("Sorted array:");
        printArray();
    }

    public static void main(String[] args) {
        new ArraySortConsole();
    }
}

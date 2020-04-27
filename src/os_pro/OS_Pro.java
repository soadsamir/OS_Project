package os_pro;

import java.util.ArrayList;
import java.util.Scanner;

public class OS_Pro {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter scenario number : ");
        int c = reader.nextInt();
        if (c == 1) {

                                                           //scenario 1
            System.out.print("Enter size of memory : ");
            int size = reader.nextInt();
            boolean f = true;
            System.out.print("Enter no. of processes : ");
            int n = reader.nextInt();
            int arr[] = new int[n];
            int count = 0;
            for (int i = 0; i < n; i++) {
                System.out.print("Enter the size of process " + i + " : ");
                arr[i] = reader.nextInt();

            }

            for (int i = 0; i < n; i++) {
                if (count + arr[i] > size) {
                    f = false;
                    break;
                }
                System.out.println("proccess " + i + " starts at " + count + " and ends at " + (count + arr[i] - 1));
                count += arr[i];

            }
            if (f == false) {
                System.out.println("Remainning processes are waiting ");
            }

        } else if (c == 2) {

                                                                   //scenario 2
            System.out.print("Enter size of memory : ");
            int size = reader.nextInt();
            int N_pages = 0;
            System.out.print("Enter no.of processes : ");
            int n = reader.nextInt();
            int processes[] = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter no.of pages in process " + i + " : ");
                processes[i] = reader.nextInt();
                N_pages += processes[i];
            }
            int Memory[] = new int[size];

            ArrayList<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(i);
            }
            for (int count = 0; count < size; count++) {
                Memory[count] = list.remove((int) (Math.random() * list.size()));    //random allocation of pages
            }

            for (int i = 0; i < size; i++) {
                if (Memory[i] >= N_pages) {
                    Memory[i] = -1;
                }
                System.out.println(i + "  " + Memory[i]);   //print all Memory frames
            }
            System.out.println("");
            int p = 0;
            for (int i = 0; i < n; i++) {
                for (int j = p; j < processes[i] + p; j++) {
                    for (int s = 0; s < size; s++) {
                        if (j == Memory[s]) {
                            System.out.println("page " + (j - p) + " of process " + i + " is in frame " + s);

                        }
                    }

                }

                p += processes[i];
            }
            System.out.print("Frame List : { ");
            for (int i = 0; i < size; i++) {
                if (Memory[i] == -1) {
                    System.out.print(i + " ");
                }

            }

            System.out.println("}");

        } //scenario 3
        else if (c == 3) {
            int frames[] = {-1, -1, -1};
            System.out.print("Enter no.of pages : ");
            int n = reader.nextInt();

            int pages[] = new int[n];
            for (int i = 0; i < n; i++) {
                pages[i] = i;
            }
            System.out.print("Enter no.of pages in sequance : ");
            int s = reader.nextInt();
            int sequance[] = new int[s];
            System.out.print("Enter the sequence : ");
            for (int i = 0; i < s; i++) {
                sequance[i] = reader.nextInt();

            }

            for (int i = 0; i < s; i++) {
                if (sequance[i] >= n) {
                    System.arraycopy(sequance, i + 1, sequance, i, s - i - 1);
                    s--;
                }
            }
            int seq[] = new int[s];
            System.arraycopy(sequance, 0, seq, 0, s);     //only valid pages

    //FIFO Algorithm
            int faults = 0;
            int p = 0;

            for (int i = 0; i < s; i++) {
                if (p > 2) {
                    p = 0;
                }
                boolean f = false;
                for (int j = 0; j < 3; j++) {
                    if (seq[i] == frames[j]) {
                        f = true;
                        break;
                    }

                }
                if (f == false) {

                    frames[p] = seq[i];
                    faults++;
                    p++;

                }
                for (int j = 0; j < 3; j++) {
                    System.out.print(frames[j] + " ");
                }
                System.out.println("");

            }
            System.out.println("Number of faults is : " + faults);

            System.out.println("page table ");
            System.out.println("P.No " + "     " + "V_Bit");
            boolean f = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {

                    if (pages[i] == frames[j]) {
                        f = true;
                    }
                }

                System.out.print(pages[i] + "     ");
                if (f) {
                    System.out.println("      V");
                } else {
                    System.out.println("     I");
                }
                f = false;
            }
        } else {
            System.out.println("Wrong scenario");
        }
    }
}

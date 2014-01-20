/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

/**
 *
 * @author RPM
 */


import java.util.Random;


public class Suffle {

    int[] arr;
	int total;
	int current;

	public Suffle(int totalElement)
	{
		arr = new int[totalElement];
		total = totalElement;
		current = 0;

		for(int i=0; i<totalElement; i++)
			arr[i] = i+1;
	}

    public void suffle()
    {
        Random rnd = new Random();
        int j;

        for(int i=0; i<total; i++) {
            j = rnd.nextInt(total);
        	int temp = arr[i];
        	arr[i] = arr[j];
        	arr[j] = temp;
        }

        Print();
    }

    public void Print()
    {
        for(int n : arr)
        {
            System.out.printf("%d\n", n);
        }
    }

    public int nextInt()
    {
    	return arr[ current = (current + 1) % total ];
    }

    public int[] getArray()
    {
    	return arr;
    }
}
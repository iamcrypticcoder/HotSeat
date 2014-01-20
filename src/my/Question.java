/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

/**
 *
 * @author RPM
 */

import java.io.*;

public class Question implements Serializable {
	public String questionTitle;
	public String options[];
	private int answer;

	public Question()
	{

	}

	public Question(String ques, String op1, String op2, String op3, String op4, int ans)
	{
		questionTitle = ques;

		options = new String[4];
		options[0] = op1;
		options[1] = op2;
		options[2] = op3;
		options[3] = op4;

		answer = ans;
	}

	public int Answer()
	{
		return answer;
	}

	public void printQuestion()
	{
		System.out.println(questionTitle);
        for(int i=0; i<4; i++)
            System.out.println( i+1 + " : " + options[i]);
        System.out.println("Answer is : " + options[answer-1]);
	}

}

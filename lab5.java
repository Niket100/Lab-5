package test;
import java.util.*;



import javax.swing.*;

import java.lang.*;
import java.lang.reflect.Array;
import java.io.*;
import java.util.Arrays;

class Student
{
	public int roll_no;
	public ArrayList<String> val;
	public int size;
	public Student(int a)
	{
		roll_no=a;
		val=new ArrayList<String>();
		size=0;
	}
	public void add(String s)
	{
		val.add(s);
		size++;
	}
	public void disp()
	{
		System.out.print(roll_no+" ");
		for(String h:val)
		{
			System.out.print(h+" ");
		}
		System.out.println();
			
	}
}

class node<T extends Comparable<T> > implements Comparable< node <T> >
{
	private T val;
	public node<T> left,right;
	public void set_right(node<T> ob)
	{
		right=ob;
	}
	public void set_left(node<T> ob)
	{
		left=ob;
	}
	public node(T a)
	{
		val=a;
		left=right=null;
	}
	public node<T> get_right()
	{
		return right;
	}
	public node<T> get_left()
	{
		return left;
	}
	public T get_val()
	{
		return val;
	}
	@Override
	public int compareTo(node<T> ob) 
	{
		return this.val.compareTo(ob.val);
	}
}


class tree<T extends Comparable < T> >
{
	private node<T> root;
	private int size;
	public ArrayList<node <T> > li;
	public T sum;
	public void add(node <T> ob)
	{
		node <T> r=root;
		node <T> te=root;
		while(te!=null)
		{
			r=te;
			if(r.compareTo(ob)==-1)
					te=r.get_right();
			else
				te=r.get_left();
		}
		if(r.compareTo(ob)==-1)
			r.right=ob;
		else
			r.left=ob;
		
	}
	public void in_order(node <T> ob)
	{
		if(ob.get_left()!=null)
			in_order(ob.get_left());
		li.add(ob);
		if(ob.get_right()!=null)
			in_order(ob.get_right());
	}
	public node <T> get_root()
	{
		return root;
	}
	public tree(T n,int s)
	{
		size=s;
		root=new node(n);
		li=new ArrayList<node<T>>();
	}
	public void disp()
	{
		node <T> te=root;
		for(node<T> s: li)
		{
			System.out.print(s.get_val()+" ");
		}
	}
	public void assign(Student[] ob,String s)
	{
		int i,tem=0;
		i=0;
		for(node<T> h: li)
		{
			if(h.compareTo(root)==0)
				tem=i;
			i++;
		}
		ob[tem].add(s);
		ob[tem].size++;
	}
	
}


 public class lab5 
 {
 	
	
	public static void main(String[] args) throws IOException
	{
		Reader.init(System.in);
		BSTFilesBuilder ri=new BSTFilesBuilder();
		
		int n,i,j,m,te,tem,s1;
		String s,jk,s3;
		float a,s2;
		System.out.println("Enter Number of students");
		n=Reader.nextInt();
		System.out.println("Enter Number of trees");
		m=Reader.nextInt();
		ri.createBSTFiles(n, m);
		
		Student[] rec=new Student[n];
		for(i=0;i<n;i++)
		{
			rec[i]=new Student(i+1);
		}
		for(i=1;i<=m;i++)
		{
			InputStream inp=new FileInputStream("./src/"+i+".txt");
			Reader.init(inp);
			s=Reader.next();
			//Reader.nextLine();
			te=Reader.nextInt();
			int cho=0;
			if(s.equals("Integer"))
			{
				tem=Reader.nextInt();
				tree<Integer> t=new tree<Integer>(tem,te);
				s1=tem;
				for(j=0;j<te-1;j++)
				{
					tem=Reader.nextInt();
					node<Integer> ob=new node<Integer>(tem);
					t.add(ob);
					s1+=tem;
				}
				t.in_order(t.get_root());
				t.assign(rec, Integer.toString(s1));
			
			}
			
			if(s.equals("Float"))
			{
				a=Reader.nextFloat();
				tree<Float> t=new tree<Float>(a,te);
				s2=a;
				for(j=0;j<n-1;j++)
				{
					a=Reader.nextFloat();
					node<Float> ob=new node<Float>(a);
					t.add(ob);
					s2+=a;
				}
				t.in_order(t.get_root());
				t.assign(rec, Float.toString(s2));
			}
			if(s.equals("String"))
			{
				jk=Reader.next();
				String tm=jk;
				int temp;
				tree<String> t=new tree<String>(jk,te);
				String[] sd=new String[n];
				sd[0]=new String();
				sd[0]=jk;
				for(j=1;j<n;j++)
				{
					jk=Reader.next();
					node<String> ob=new node<String>(s);
					t.add(ob);
					sd[j]=new String();
					sd[j]=jk;
				
				}
				Arrays.sort(sd);
				s3=sd[0];
				temp=0;
				for(j=1;j<n;j++)
				{
					if(tm.equals(sd[j]))
						temp=j;
					s3+=sd[j];
				}
				rec[temp].add(s3);
				rec[temp].size++;
				
				
			}
		
			
			
		}
		int cho=0;
		
		for(j=0;j<n;j++)
		{
			if(rec[j].size!=0)
			{
				rec[j].disp();
			}
			else
				cho++;
		}	
		if(cho!=0)
			System.out.println(cho);

		
	}
 }
 class Reader {
	    static BufferedReader reader;
	    static StringTokenizer tokenizer;
	    /** call this method to initialize reader for InputStream */
	    static void init(InputStream input) {
	        reader = new BufferedReader(
	                new InputStreamReader(input) );
	        tokenizer = new StringTokenizer("");
	    }
	 
	    /** get next word */
	    static String next() throws IOException {
	        while ( ! tokenizer.hasMoreTokens() ) {
	            //TODO add check for if necessary
	            tokenizer = new StringTokenizer(
	                    reader.readLine() );
	        }
	        return tokenizer.nextToken();
	    }
	 
	    static int nextInt() throws IOException {
	        return Integer.parseInt( next() );
	    }
	    static float nextFloat() throws IOException {
	        return Float.parseFloat( next() );
	    }
	    static double nextDouble() throws IOException {
	        return Double.parseDouble( next() );
	    }
	}
	
	
	
	
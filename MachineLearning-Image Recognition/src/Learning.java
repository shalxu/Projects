import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 
 * This program takes in MINST representations of handwritten numbers, and tries machine learning recognition by attributing each pixel to the most correlated letter
 * @author Shal Xu
 *
 */
public class Learning {
	static Hypothesis[] h=new Hypothesis[10];
	final static double a=0.1;

	public static void main(String[] args){
		for(int i=0;i<10;i++){
			h[i]=new Hypothesis();
			for(int j=0;j<784;j++){
				h[i].w[j]=0.0;
			}
		}
		Learning.train();
		System.out.println("The rate of correct recognition is " +Learning.test());
	}
	
	/**e
	 * The training method reads the training image/label files, makes some predictions, and accordingly alters the Hypothesis array.
	 */
	static void train() {
		
		FileInputStream inImage = null;
		FileInputStream inLabel = null;

		try {
			inImage = new FileInputStream("train-images.idx3-ubyte");
			inLabel = new FileInputStream("train-labels.idx1-ubyte");

			int magicNumberImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());
			int numberOfImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());
			int numberOfRows = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());
			int numberOfColumns = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());

			int magicNumberLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8)
					| (inLabel.read());
			int numberOfLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8)
					| (inLabel.read());
			if (magicNumberImages != 2051)
				System.out.println("Image Magic Number Error");
			if (numberOfImages != 60000)
				System.out.println("Number of Images Error");
			if (numberOfRows != 28)
				System.out.println("Image Row Error");
			if (numberOfColumns != 28)
				System.out.println("Image Column Error");
			if (magicNumberLabels != 2049)
				System.out.println("Label Magic Number Error");
			if (numberOfLabels != 60000)
				System.out.println("Number of Labels Error");

			for (int n = 0; n < 60000; n++) {
				int label = inLabel.read();
				System.out.println("New training target: " + label);
				double[] pixel = new double[784];
				double[] prediction = new double[10];
				
				//Linear regression learning algorithm
				for (int i = 0; i < 784; i++) {
					pixel[i] = Learning.standardize(inImage.read());
					for (int j = 0; j < 10; j++) {
						prediction[j] += h[j].w[i] * pixel[i];
					}
				}
				for (int j = 0; j < 10; j++) {
					prediction[j]=prediction[j]/784;
				}
				System.out.println("prediction:"+max(prediction));
				for (int i = 0; i < 784; i++)
					for (int j = 0; j < 10; j++) {
						if (j == label) {
							h[j].w[i] += a * (1 - prediction[j]) * pixel[i];
						} else {
							h[j].w[i] += a * (0 - prediction[j]) * pixel[i];
						}
					}
				
				//Logistic regression learning algorithm (only choose one algorithm to use for learning)
				/*
				for (int i = 0; i < 784; i++) {
					pixel[i] = Learning.standardize(inImage.read());
					for (int j = 0; j < 10; j++) {
						prediction[j] += 1/(1+Math.exp(h[j].w[i]*pixel[i]));
					}
				}
				for (int j = 0; j < 10; j++) {
					prediction[j]=prediction[j]/784;
				}
				System.out.println("prediction:"+max(prediction));
				for (int i = 0; i < 784; i++)
					for (int j = 0; j < 10; j++) {
						if (j == label) {
							h[j].w[i] += a * (1 - prediction[j])*prediction[j]*(1-prediction[j]) * pixel[i];
						} else {
							h[j].w[i] += a * (0 - prediction[j])*prediction[j]*(1-prediction[j]) * pixel[i];
						}
					}*/
			}
		} catch (Exception e) {
			System.out.println("Learning Error");
			e.printStackTrace();
		}
	}
	
	/**
	 * The test method reads test label/image files, makes predictions, and compares them to the right results.
	 * @return The ratio of correctly predicted results.
	 */
	static double test(){
		int count=0;
	
		FileInputStream inImage = null;
		FileInputStream inLabel = null;
		try{
			inImage = new FileInputStream("t10k-images.idx3-ubyte");
			inLabel = new FileInputStream("t10k-labels.idx1-ubyte");
			int magicNumberImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());
			int numberOfImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());
			int numberOfRows = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());
			int numberOfColumns = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8)
					| (inImage.read());

			int magicNumberLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8)
					| (inLabel.read());
			int numberOfLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8)
					| (inLabel.read());
			if (magicNumberImages != 2051)
				System.out.println("Image Magic Number Error");
			if (numberOfImages != 10000)
				System.out.println("Number of Images Error");
			if (numberOfRows != 28)
				System.out.println("Image Row Error");
			if (numberOfColumns != 28)
				System.out.println("Image Column Error");
			if (magicNumberLabels != 2049)
				System.out.println("Label Magic Number Error");
			if (numberOfLabels != 10000)
				System.out.println("Number of Labels Error");

			for (int n = 0; n < 10000; n++) {
				int label = inLabel.read();
				double[] pixel = new double[784];
				double[] prediction = new double[10];
				for (int i = 0; i < 784; i++) {
					pixel[i] = Learning.standardize(inImage.read());
					for (int j = 0; j < 10; j++) {
						prediction[j] += h[j].w[i] *pixel[i];
					}
				}
				Learning.draw(pixel);
				if(max(prediction)==label)
					count++;
				System.out.println("Testing target: "+label);
				System.out.println("Predicted Result: "+max(prediction));
			}
		}
		catch(Exception e){
			System.out.println("Testing Error");
			e.printStackTrace();
		}
		return count/10000.0;
	}
	
	/**
	 * To standardize 0-255 shade values to a double between 0 and 1.
	 */
	private static double standardize(int i){
		if(i/256.0<0||i/256.0>=1) System.out.println("error!" +i);
		return i/256.0;
	}
	
	/**
	 * Return the index of max value in a double array
	 * @param p double array
	 * @return index of max value.
	 */
	private static int max(double[] p){
		int temp=0;
		for(int i=1;i<p.length;i++){
			if(p[i]>p[temp])
				temp=i;
		}
		return temp;
	}
	
	/**
	 * Hypothesis object, with a double value for each of the pixel. One hypothesis is only linked to one possible recognition result.
	 * @author Shal Xu
	 *
	 */
	private static class Hypothesis{
		double[] w=new double[784];
	}
	
	/**
	 * Given a array of 784 double pixel values, output in console the ASCII letter representation.
	 * @param pixels
	 */
	private static void draw (double[] pixels){
		String shades= "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,^`'. ";
		StringBuilder string= new StringBuilder(784);
		for(int i=0;i<28;i++){
			for(int j=0;j<28;j++){
				string.append(shades.charAt((int)Math.floor(shades.length()*(0.9999999999999-pixels[i*28+j]))));	
			}
			string.append("\n");
		}
		System.out.println(string.toString());
	}
}

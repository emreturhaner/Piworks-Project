import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PiworksProject {

	static Random rand = new Random();
	static int summaryOfTriangle = 0;
	static List<Node> referenceTriangle = new ArrayList<Node>();
	static List<Node> originalTriangle = new ArrayList<Node>();
	static Scanner textScanner, limitScanner;
	static int limitOfTriangle;

	public static void main(String[] args) {

		/* to construct triangle with numbers from a text file   */
		
		List<Integer> list = getNumbersFromFile();
		setTriangle(list);

		/* if you want to observe how algorithm works under pressure -like big
		   numbers of element at your examlple- you can use codeblock below. */

		// System.out.println("Please enter triangle's depth: ");
		// limitScanner = new Scanner(System.in);
		// limitOfTriangle = limitScanner.nextInt();
		// limitScanner.close();
		// setTriangleWithLimit();	// setting triangle's elements for limit.
		// displayTriangle();		// displaying triangle.

		setOriginalTriangle();
		findMaxSumOfTriangle(referenceTriangle
				.get(referenceTriangle.size() - 1));
		System.out.println("Summary Of Triangle: " + summaryOfTriangle);
	}

	// ** checking number is prime or not**
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	// **retrieve element's value from reference triangle**
	public static int getReferenceTriangleValue(int i, int j) {
		for (Node node : referenceTriangle) {
			if (node.getI() == i && node.getJ() == j) {
				return node.getValue();
			}
		}
		return 0;
	}

	// **setting given text file's number into reference triangle**
	public static void setTriangle(List<Integer> list) {
		int current = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j <= i; j++) {
				if (current < list.size()) {
					Node n = new Node();
					n.setI(i);
					n.setJ(j);
					n.setValue(list.get(current));
					current++;
					referenceTriangle.add(n);
				}
			}
		}
	}

	// **reading number from text file**
	public static List<Integer> getNumbersFromFile() {

		textScanner = new Scanner(System.in);
		try {
			textScanner = new Scanner(new File("text.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (textScanner.hasNext()) {
			if (textScanner.hasNextInt()) {
				list.add(textScanner.nextInt());
			}
		}

		return list;
	}

	// **setting original triangle to load original number's and check these are
	// prime or not**
	public static List<Node> setOriginalTriangle() {
		for (Node n : referenceTriangle) {
			Node newNode = new Node();
			newNode.setI(n.getI());
			newNode.setJ(n.getJ());
			newNode.setValue(n.getValue());
			originalTriangle.add(newNode);
		}
		return originalTriangle;
	}

	// **retrieve reference triangle's element itself**
	public static Node getReferenceTriangle(int i, int j) {
		for (Node node : referenceTriangle) {
			if (node.getI() == i && node.getJ() == j) {
				return node;
			}
		}
		return null;
	}

	// **retrieve original triangle's element itself
	public static Node getOriginalTriangle(int i, int j) {
		for (Node node : originalTriangle) {
			if (node.getI() == i && node.getJ() == j) {
				return node;
			}
		}
		return null;
	}

	// **replacing element's value as new given element's value
	public static void setReferenceTriangleValue(int i, int j, int value) {
		for (Node node : referenceTriangle) {
			if (node.getI() == i && node.getJ() == j) {
				node.setValue(value);
			}
		}
	}

	// **setting triangle's element**
	public static void setTriangleWithLimit() {
		for (int i = 0; i < limitOfTriangle; i++) {
			for (int j = 0; j <= i; j++) {
				Node node = new Node();
				int listSize = referenceTriangle.size();
				node.setI(i);
				node.setJ(j);
				while (listSize + 1 != referenceTriangle.size()) {
					int randNumber = rand.nextInt(20) + 3;
					node.setValue(randNumber);
					if (!referenceTriangle.contains(node)) {
						referenceTriangle.add(node);
					}
				}
			}
		}
	}

	// **display triangle's content**
	public static void displayTriangle() {
		for (int i = 0; i < limitOfTriangle; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(getReferenceTriangleValue(i, j) + " ");
			}
			System.out.println("");
		}
	}

	/*
	 * finding maximum summary which has many possible path by using recursive
	 * function. basically it gets last element of triangle to go upwards and
	 * diagonally till reachs first element of triangle. it checks original
	 * triangle's number whether is prime or not then sums through reference
	 * triangle. it uses bottom-up way to the top, from right to left. we need
	 * original tree to load original values of elements. reference tree's
	 * elements is changing every single line. it's like tree structure. compare
	 * two childs to find bigger value then add it to parent's value. while
	 * reference triangle calculating that, original tree's checking node's
	 * original value is prime or not.
	 */
	public static void findMaxSumOfTriangle(Node node) {
		Node left = getReferenceTriangle(node.getI(), node.getJ() - 1);
		Node right = node;
		Node parent = new Node();
		if (getReferenceTriangle(node.getI() - 1, node.getJ() - 1) != null) { /*
																			 * to
																			 * chech
																			 * if
																			 * it
																			 * reachs
																			 * top
																			 * of
																			 * reference
																			 * triangle
																			 */
			int maxValue = 0;
			while (left != null) { /*
									 * till reach leftmost element of reference
									 * triangle do transaction. when there's no
									 * children stop and go up line
									 */
				if (!isPrime(getOriginalTriangle(left.getI(), left.getJ()) // checks
																			// if
																			// childs
																			// are
						.getValue()) // prime or not
						&& !isPrime(getOriginalTriangle(right.getI(),
								right.getJ()).getValue())) {
					maxValue = Math.max(left.getValue(), right.getValue());

				} else if (!isPrime(getOriginalTriangle(left.getI(),
						left.getJ()).getValue())) {
					maxValue = left.getValue();
				} else if (!isPrime(getOriginalTriangle(right.getI(),
						right.getJ()).getValue())) {
					maxValue = right.getValue();
				} else if (isPrime(getOriginalTriangle(right.getI(),
						right.getJ()).getValue())
						&& isPrime(getOriginalTriangle(left.getI(), left.getJ())
								.getValue())) {
					maxValue = 0;
				}

				parent = getReferenceTriangle(right.getI() - 1, // relocate
						right.getJ() - 1); // parent

				if (parent.getI() == 0 && parent.getJ() == 0) { /*
																 * controlling
																 * if it is
																 * first element
																 * of triangle.
																 */
					setReferenceTriangleValue(parent.getI(), parent.getJ(),
							(parent.getValue() + maxValue)); /*
															 * sums child's
															 * maxValue and
															 * parent's value
															 */
				} else if (!isPrime(getOriginalTriangle(parent.getI(), // controlling
						parent.getJ()).getValue())) { // if
														// parent is prime or
														// not
					setReferenceTriangleValue(parent.getI(), parent.getJ(), // then
																			// sums
																			// up
							(parent.getValue() + maxValue));

				}
				Node temp = getReferenceTriangle(left.getI(), left.getJ() - 1);
				right = left;
				left = temp;
			}

			findMaxSumOfTriangle(getReferenceTriangle(node.getI() - 1,
					node.getJ() - 1));

		} else {
			summaryOfTriangle = node.getValue();
		}

	}
}




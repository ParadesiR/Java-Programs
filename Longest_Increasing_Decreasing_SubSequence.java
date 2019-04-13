import java.util.ArrayList;

public class Longest_Increasing_Decreasing_SubSequence {

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 5, 6, 7, 1, 0, 5, 2, 6, 78, 89, 90, 91, 3, 4, 6, 7, -5, -8, -6, 12, 78, 79 };
		new Longest_Increasing_Decreasing_SubSequence().inc_seq(a);
	}

	public void inc_seq(int a[]) {
		int i, j;
		int max = 0;
		int si = 0;
		int ic = 0;
		int count = 0;
		int sum = 0;
		int sc = 0;

		ArrayList<Integer> al = new ArrayList<Integer>(); // to storing 0/1 - identifies sequence break
		ArrayList<Integer> as = new ArrayList<Integer>(); // to store sequence lengths
		ArrayList<Integer> aei = new ArrayList<Integer>(); // to store sequence end indexes
		ArrayList<Integer> a_max = new ArrayList<Integer>(); // to store sequences(with indexes) of equal lengths

		// for sequence identification
		for (i = 0; i < a.length - 1; i++) {
			if (a[i] < a[i + 1]) {	// for decreasing sequence change < to > in the condition
				ic++;
				al.add(1); 	// add 1 for continuous sequence
				if (max <= ic)	// To find out the max length of the sequence
					max = ic;
			} else {
				ic = 0;
				al.add(0); 	// add 0 if any break in sequence
			}
		}

		// counting sequence lengths and indexes Using above list(1's & 0's)
		for (i = 0; i < al.size(); i++) {
			for (j = i; j < al.size(); j++) {
				if (al.get(j) == 1) {
					sc++; 	// counting sequence length with 1's
				} else {
					break; 	// breaks if 0 encounters
				}
				i++;
			}
			if (sc != 0) {
				as.add(sc); 	// adding count of each sequence in odd indexes
				aei.add(i); 	// adding end index of each sequence in even indexes
			}
			sc = 0;
		}

		System.out.println("All sequences count " + as + "\n\nEnd indexes " + aei);

		// To add sequences with highest count and equal count
		for (i = 0; i < as.size(); i++) {
			if (as.get(i) == max) {
				count++;
				a_max.add(max);
				a_max.add(aei.get(i));
			}
		}

		// All sequences and elements
		System.out.println("\n<<<<<<< All Sequences >>>>>>>");
		for (i = 0; i < as.size(); i++) {
			si = aei.get(i) - as.get(i);
			for (j = si; j <= aei.get(i); j++) {
				System.out.print(a[j] + " ");
			}
			System.out.println();
		}

		// Longest and Equal length Sequences
		System.out.println("\n<<<<<<< Longest Sequences " + count + " >>>>>>>");
		for (i = 0; i < a_max.size(); i += 2) {
			sum = 0;
			si = a_max.get(i + 1) - a_max.get(i);
			for (j = si; j <= a_max.get(i + 1); j++) {
				System.out.print(a[j] + " ");
				sum = sum + a[j];
			}
			System.out.println("\nSum of elements in sequence : " + sum + "\n");
		}
	}
}
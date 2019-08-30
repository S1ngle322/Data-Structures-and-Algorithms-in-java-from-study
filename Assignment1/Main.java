/**
*https://codeforces.com/group/lk8Ud0ZeBu/contest/238197/submission/50062634
*/
package com.company;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Map<String,DoublyLinkedList<Competitive>> games = new LinkedHashMap<>();
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();/**Amount of games*/
		input.nextLine();
		for (int i = 0; i < x; i++){
			String s = input.nextLine();
			games.put(s,new DoublyLinkedList<Competitive>());
			int y = input.nextInt();/**Amount of teams*/
			input.nextLine();
			for (int j = 0; j < y; j++){
				games.get(s).addLast(new Competitive(input.nextLine()));
			}
			int z = input.nextInt();/**Amount of games in each tournament*/
			input.nextLine();
			for (int j = 0; j < z; j++){
				Competitive.setter(games.get(s),input.nextLine());
			}
		}
        /**
         * Foreach for printing a class.
         */
		for (String c:games.keySet()) {
			games.get(c).selectionSort();
			System.out.println(c);
			for (int q = 0; q < games.get(c).size(); q++){
				System.out.print(q + 1 + ") ");
				games.get(c).get(q).output();
			}
			System.out.println();

		}
	}

}

/**
 * Class with fields of data
 */
class Competitive implements Comparable<Competitive> {
	public String team_name;
	public int team_points = 0;
	public int team_num_g = 0;
	public int team_wins = 0;
	public int team_toes = 0;
	public int team_loss = 0;
	public int team_score = 0;
	public int team_against = 0;

    /**
     * Constructor
     * @param n
     */
	public Competitive(String n) {
		team_name = n;
	}

    /**
     * Setter for teams.
     * This function modify parameters.
     * @param comp
     * @param s
     */
	public static void setter(DoublyLinkedList<Competitive> comp, String s) {
		String[] uno = s.split(":");
		for (int i = 0; i < 2; i++) {
			Competitive r = find_index(comp, uno[i].split("#")[i]);
			if (r != null) {
				int ind = i != 0 ? 0 : 1;
				r.team_score += Integer.parseInt(uno[i].split("#")[ind]);
			}
		}
		for (int i = 1; i > -1; i--) {
			int ind = i != 0 ? 0 : 1;
			Competitive r = find_index(comp, uno[i].split("#")[i]);
			if (r != null) {
				r.team_against += Integer.parseInt(uno[ind].split("#")[i]);
			}
		}
		int er = Integer.parseInt(uno[0].split("#")[1]);
		int re = Integer.parseInt(uno[1].split("#")[0]);
		Competitive c0 = find_index(comp, uno[0].split("#")[0]);
		Competitive c1 = find_index(comp, uno[1].split("#")[1]);
		if (er > re) {
			c0.team_wins += 1;
			c0.team_points += 3;
			c1.team_loss += 1;
		} else if (er < re) {
			c1.team_wins += 1;
			c1.team_points += 3;
			c0.team_loss += 1;

		} else {
			c1.team_toes += 1;
			c0.team_toes += 1;
			c0.team_points += 1;
			c1.team_points += 1;
		}
		c0.team_num_g += 1;
		c1.team_num_g += 1;

	}

    /**
     * Searching a class with name of s
     * @param l
     * @param s
     * @return
     */
	private static Competitive find_index(DoublyLinkedList<Competitive> l, String s) {
		for (int y = 0; y < l.size(); y++) {
			if (l.get(y).team_name.equals(s)) {
				return l.get(y);
			}
		}
		return null;
	}

	public void output() {
		System.out.println(team_name + " " + team_points + "p, " + team_num_g + "g (" + team_wins + "-" + team_toes + "-" + team_loss + "), " + (team_score - team_against) + "gd (" + team_score + "-" + team_against + ")");

	}

    /**
     * Comparing all arguments
     * @param o object of Competitive
     * @return
     */
	@Override
	public int compareTo(Competitive o) {
		if (team_points > o.team_points){
			return - 1;

		}else if (team_points == o.team_points){
			if (team_wins > o.team_wins){
				return -1;
			}else if(team_wins == o.team_wins){
				if (team_score - team_against > o.team_score - o.team_against){
					return -1;
				}else if (team_score - team_against == o.team_score - o.team_against) {
					if (team_name.compareTo(o.team_name) > 0){
						return -1;
					} else {return 1;}
				}else{return 1;}
			}else{return 1;}
		}else{return 1;}
	}
}

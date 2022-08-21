import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private PriorityQueue flights_start;
    private PriorityQueue flights_end;


    private Comparator compare_start;
    private Comparator compare_end;


    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */

        Comparator compare_start = (Object a, Object b) -> {
            Flight c = (Flight) a;
            Flight d = (Flight) b;
            return Integer.compare(c.startTime(),d.startTime());
        };

        Comparator compare_end = (Object a, Object b) -> {
            Flight c = (Flight) a;
            Flight d = (Flight) b;
            return Integer.compare(c.endTime(),d.endTime());
        };

        flights_start = new PriorityQueue<Flight>(flights.size(), compare_start);
        flights_end = new PriorityQueue<Flight>(flights.size(), compare_end);

        flights_start.addAll(flights);
        flights_end.addAll(flights);
    }

    public int solve() {
        /* FIX ME */
        int people = 0;
        int max = 0;
        while(!flights_start.isEmpty() && !flights_end.isEmpty()) {
            Flight next_start = (Flight) flights_start.peek();
            Flight next_end = (Flight) flights_end.peek();

            if (next_start.startTime() <= next_end.endTime()) {
                people = people + next_start.passengers();
                flights_start.poll();
            } else {
                people = people - next_end.passengers();
                flights_end.poll();
            }

            max = Math.max(people,max);
        }

        return max;
    }
}

package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver{

    private double timeout;
    private AStarGraph<Vertex> graph;
    private Vertex start;
    private Vertex end;

    private int dequeue_num;
    private long starttime;

    private long endtime;

    private SolverOutcome result;

    private HashSet<Vertex> vertices;

    private DoubleMapPQ<Vertex> PQ;
    private HashMap<Vertex,Double> distTo;
    private HashMap<Vertex,Vertex> edgeTo;

    private double solution_weight;
    private LinkedList<Vertex> solution_list;



    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        starttime = System.currentTimeMillis();
        //System.out.println(starttime);
        this.timeout = timeout;
        this.start = start;
        this.end = end;
        graph = input;
        dequeue_num = 0;
        PQ = new DoubleMapPQ<>();
        distTo = new HashMap<>(30,2);
        edgeTo = new HashMap<>(30,2);
        vertices = new HashSet<>(30,2);
        solution_list = new LinkedList<>();
        solution_weight = 0;

        initialize();

        if (start.equals(end)) {
            result = SolverOutcome.SOLVED;
            solution_weight = 0;
            solution_list.addFirst(start);
        } else {
            problem_solution();
        }
        endtime = System.currentTimeMillis();
    }


    private void initialize() {
        /*initialize_vertices(start);
        initialize_dist();
        initialize_edge();
        initialize_estimate();

         */
        PQ.add(start, 0 + graph.estimatedDistanceToGoal(start,end));
        distTo.put(start, 0.0);
    }

    private void problem_solution(){
        while (PQ.size() > 0) {
            if (PQ.getSmallest().equals(end)) {
                result = SolverOutcome.SOLVED;
                solution_weight = distTo.get(end);
                Vertex routine = end;
                while (!routine.equals(start)) {
                    solution_list.addFirst(routine);
                    routine = edgeTo.get(routine);
                }
                solution_list.addFirst(start);
                break;
            }

            //System.out.println((System.currentTimeMillis() - starttime)/1000000);

            if ((System.currentTimeMillis() - starttime)/1000000 >=timeout) {
                result = SolverOutcome.TIMEOUT;
                solution_list = new LinkedList<>();
                solution_weight = 0;
                break;
            }



            Vertex v = PQ.removeSmallest();
            dequeue_num = dequeue_num + 1;

            double dist_v = distTo.get(v);
            for (WeightedEdge edge: graph.neighbors(v)) {
                double weight = edge.weight();
                Vertex next= (Vertex) edge.to();
                double new_dist = dist_v + weight;

                if (!distTo.containsKey(next)) {
                    distTo.put(next,new_dist);
                    edgeTo.put(next,v);
                    PQ.add(next, new_dist + graph.estimatedDistanceToGoal(next,end));
                } else {
                    if (new_dist < distTo.get(next)) {
                        distTo.replace(next, new_dist);
                        edgeTo.replace(next, v);
                        PQ.changePriority(next, new_dist + graph.estimatedDistanceToGoal(next,end));
                    }
                }
            }
        }

        if (!distTo.containsKey(end) && (result != SolverOutcome.TIMEOUT)) {
            result = SolverOutcome.UNSOLVABLE;
            solution_list = new LinkedList<>();
            solution_weight = 0;
        }
    }

/*
    private void initialize_vertices(Vertex v) {
        vertices.add(v);
        for (WeightedEdge edge: graph.neighbors(v)) {
            Vertex next = (Vertex) edge.to();
            if (vertices.contains(next)) {
                continue;
            } else {
                initialize_vertices(next);
            }
        }
    }

    private void initialize_dist() {
        for (Vertex v:vertices) {
            distTo.put(v,Double.MAX_VALUE);
        }
    }

    private void initialize_edge() {
        for (Vertex v:vertices) {
            edgeTo.put(v,null);
        }
    }

    private void initialize_estimate() {
        for (Vertex v:vertices) {
            estimate.put(v,graph.estimatedDistanceToGoal(v,end));
        }
    }

     */

    @Override
    public SolverOutcome outcome() {
        return result;
    }

    @Override
    public List solution() {
        return solution_list;
    }

    @Override
    public double solutionWeight() {
        return solution_weight;
    }

    @Override
    public int numStatesExplored() {
        return dequeue_num;
    }

    @Override
    public double explorationTime() {
        return (endtime-starttime)/1000;
    }
}

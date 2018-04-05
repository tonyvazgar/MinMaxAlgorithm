import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MinMax {

    public static State miniMaxEasy(State currentState) {
        LinkedList<State> children;
        LinkedList<State> grandChildren;
        State selectedState;    //Cual es el estado que voy a regresar
        State minGrandChild;    //Nieto con el valor minimo
        int i;
        int j;

        //Expansion
        currentState.expansionAgentMoves();     //Expander el estado actual
        children = currentState.children;       //Obtener los hijos

        //MIN
        i = 0;
        while (i < children.size()) {
            //Expansion de cada hijo
            children.get(i).expansionUserMoves();
            grandChildren = currentState.children.get(i).children;
            //Calcular el payoff de cada nieto
            j = 0;
            while (j < grandChildren.size()) {
                grandChildren.get(j).payOffFunction();
                j = j + 1;

        }
        Collections.sort(grandChildren);
        minGrandChild = grandChildren.getFirst();
        // Ingeritance of the minimal payoff value
        children.get(i).payoff = minGrandChild.payoff;
        System.out.println(children.get(i));
        i = i + 1;
    }

    selectedState = selectMaxChild(children);
        System.out.println("************ Selected State");
        System.out.println(selectedState);
        System.out.println("***************************");
        return selectedState;
    }

    private static State selectMaxChild(LinkedList<State> children){
        LinkedList<State> candidates;
        State maxChild;
        State stateSelected;
        int i;
        int n;
        int index;
        Random random = new Random(System.currentTimeMillis());

        Collections.sort(children);
        Collections.reverse(children);
        candidates = new LinkedList<State>();   //Lista vacia de candidatos
        maxChild = children.get(0);     //Hijo con mayor valor
        candidates.add(maxChild);       //Lo meto a la lista de candidatos
        i = 1;
        while (i < children.size()){
            if (maxChild.payoff == children.get(i).payoff){
                candidates.add(children.get(i));
            } else {
                break;
            }
            i = i + 1;
        }
        n = candidates.size();      //n es el numero de candidatos elegire aleatoriamente si hay mas de 1 candidato
        if (n > 1){
            index = random.nextInt(n);
        } else {
            index = 0;
        }
        stateSelected = candidates.get(index);  //El candidato seleccionado va a ser el que regrese index
        return stateSelected;
    }




}
